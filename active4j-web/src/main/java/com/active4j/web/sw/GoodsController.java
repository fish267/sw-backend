package com.active4j.web.sw;


import com.active4j.common.web.controller.BaseController;
import com.active4j.entity.base.PageInfo;
import com.active4j.entity.base.annotation.Log;
import com.active4j.entity.base.model.LogType;
import com.active4j.entity.base.model.ResultJson;
import com.active4j.entity.base.model.ValueLabelModel;
import com.active4j.entity.base.wrapper.BaseTableWrapper;
import com.active4j.entity.base.wrapper.BaseWrapper;
import com.active4j.entity.sw.Goods;
import com.active4j.entity.sw.OrderDetail;
import com.active4j.service.sw.IGoodsService;
import com.active4j.service.sw.IOrderDetailService;
import com.active4j.service.system.util.SystemUtils;
import com.active4j.web.core.web.util.QueryUtils;
import com.active4j.web.core.web.util.ResponseUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Goods 列表控制器
 * </p>
 *
 * @author shiheng.fsh
 * @since 2020-11-27
 */
@Slf4j
@RestController
@RequestMapping("/commerce/goods")
public class GoodsController extends BaseController {

    @Autowired
    private IGoodsService iGoodsService;

    @Autowired
    private IOrderDetailService iOrderDetailService;

    @RequestMapping(value = "datagrid")
    @ApiOperation(value = "获取所有goods订单", notes = "获取所有goods订单", response = BaseWrapper.class)
    @ResponseBody
    public void datagrid(Goods goods, PageInfo<Goods> page,
                         HttpServletRequest request, HttpServletResponse response) {
        //拼接查询条件
        QueryWrapper<Goods> queryWrapper = QueryUtils.installQueryWrapper(goods, request.getParameterMap());

        //执行查询
        IPage<Goods> goodsIPage = iGoodsService.page(page.getPageEntity(), queryWrapper);

        BaseTableWrapper<Goods> ret = new BaseTableWrapper<>(goodsIPage);


        ret.getData().stream().forEach(item -> {
            if (StringUtils.isNotEmpty(item.getOrderId())) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrderId(item.getOrderId());
                QueryWrapper<OrderDetail> orderDetailQueryWrapper = new QueryWrapper<>(orderDetail);
                item.setOrderStatus(iOrderDetailService.getOne(orderDetailQueryWrapper).getOrderStatus());
            }
        });
        //结果处理,直接写到客户端
        ResponseUtil.write(response, ret.wrap());
    }

    /**
     * 保存方法
     *
     * @param goods
     * @return ResultJson
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    @Log(type = LogType.save, name = "保存信息", memo = "新增或编辑保存了goods信息")
    @ApiOperation(value = "保存goods订单", notes = "保存goods订单")
    @ApiImplicitParam(name = "goods", value = "传入用户实体对象", required = true, dataType = "Goods")
    public ResultJson save(@Validated Goods goods) {
        ResultJson resultJson = new ResultJson();
        String orderId = goods.getOrderId();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId(orderId);
        // 校验订单号是否存在
        OrderDetail rawOrder = iOrderDetailService.getOne(new QueryWrapper<>(orderDetail));
        if (StringUtils.isEmpty(orderId) || null == rawOrder) {
            resultJson.setSuccess(false);
            resultJson.setMsg("必须输入已经存在的订单号");
            return resultJson;
        }
        goods.setOrderStatus(rawOrder.getOrderStatus());
        // 更新订单状态
        try {
            if (StringUtils.isEmpty(goods.getId())) {
                iGoodsService.save(goods);
            } else {
                iGoodsService.updateById(goods);
            }
        } catch (Exception e) {
            resultJson.setSuccess(false);
            resultJson.setMsg("保存失败");
            log.error("保存用户信息报错，错误信息：{}", e.getMessage());
            e.printStackTrace();
        }

        return resultJson;
    }

    /**
     * 获取单条明细
     *
     * @return void
     * @description
     * @params
     * @author guyp
     * @time 2020年1月6日 下午5:53:46
     */
    @RequestMapping(value = "/queryDetailById")
    @ApiOperation(value = "获取单条goods Order Detail", notes = "获取单条goods Order Detail", response = BaseWrapper.class)
    public void info(@ApiParam(name = "id", value = "goods order id") String id, HttpServletResponse response) {

        Goods goods = iGoodsService.getById(id);
        //结果处理,直接写到客户端
        ResponseUtil.write(response, new BaseWrapper<Goods>(goods).wrap());
    }

    /**
     * 删除操作
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    @Log(type = LogType.del, name = "删除goods ", memo = "删除 goods 信息")
    @ApiOperation(value = "删除部门", notes = "根据订单 ID 删除")
    public ResultJson delete(@ApiParam(name = "id", value = "id") String id) {
        ResultJson j = new ResultJson();
        try {
            iGoodsService.removeById(id);
        } catch (Exception e) {
            j.setSuccess(false);
            j.setMsg("删除失败");
            log.error("删除用户信息出错，错误信息：{}", e.getMessage());
            e.printStackTrace();
        }

        return j;
    }


    /**
     * @return void
     * @description 页面获取日志类型
     * @params
     * @author guyp
     * @time 2019年12月31日 上午10:06:01
     */
    @RequestMapping(value = "/deliveryStyleDict")
    @ApiOperation(value = "获取日志类型", notes = "页面获取日志类型", response = BaseWrapper.class)
    public void logType(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> map = SystemUtils.getDictionaryMap("sw_delivery_style");
        List<ValueLabelModel> lstModels = new ArrayList<ValueLabelModel>();
        if (null != map && map.keySet().size() > 0) {
            for (String key : map.keySet()) {
                ValueLabelModel model = new ValueLabelModel();
                model.setLabel(map.get(key));
                model.setValue(key);
                lstModels.add(model);
            }
        }
        lstModels.sort(new Comparator<ValueLabelModel>() {
            @Override
            public int compare(ValueLabelModel valueLabelModel, ValueLabelModel t1) {
                return valueLabelModel.getValue().compareTo(t1.getValue());
            }
        });
        //结果处理,直接写到客户端
        ResponseUtil.write(response, new BaseWrapper<List<ValueLabelModel>>(lstModels).wrap());
    }


    /**
     * @return void
     * @description 页面获取日志类型
     * @params
     * @author guyp
     * @time 2019年12月31日 上午10:06:01
     */
    @RequestMapping(value = "/deliveryStatusDict")
    @ApiOperation(value = "获取日志类型", notes = "页面获取日志类型", response = BaseWrapper.class)
    public void deliveryStatusType(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> map = SystemUtils.getDictionaryMap("sw_delivery_status");
        List<ValueLabelModel> lstModels = new ArrayList<ValueLabelModel>();
        if (null != map && map.keySet().size() > 0) {
            for (String key : map.keySet()) {
                ValueLabelModel model = new ValueLabelModel();
                model.setLabel(map.get(key));
                model.setValue(key);
                lstModels.add(model);
            }
        }

        lstModels.sort(new Comparator<ValueLabelModel>() {
            @Override
            public int compare(ValueLabelModel valueLabelModel, ValueLabelModel t1) {
                return valueLabelModel.getValue().compareTo(t1.getValue());
            }
        });
        //结果处理,直接写到客户端
        ResponseUtil.write(response, new BaseWrapper<List<ValueLabelModel>>(lstModels).wrap());
    }


    /**
     * @return void
     * @description 页面获取日志类型
     * @params
     * @author guyp
     * @time 2019年12月31日 上午10:06:01
     */
    @RequestMapping(value = "/goodsStatusDict")
    @ApiOperation(value = "获取日志类型", notes = "页面获取日志类型", response = BaseWrapper.class)
    public void goodsStatusType(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> map = SystemUtils.getDictionaryMap("sw_goods_status");
        List<ValueLabelModel> lstModels = new ArrayList<ValueLabelModel>();
        if (null != map && map.keySet().size() > 0) {
            for (String key : map.keySet()) {
                ValueLabelModel model = new ValueLabelModel();
                model.setLabel(map.get(key));
                model.setValue(key);
                lstModels.add(model);
            }
        }

        lstModels.sort(new Comparator<ValueLabelModel>() {
            @Override
            public int compare(ValueLabelModel valueLabelModel, ValueLabelModel t1) {
                return valueLabelModel.getValue().compareTo(t1.getValue());
            }
        });
        //结果处理,直接写到客户端
        ResponseUtil.write(response, new BaseWrapper<List<ValueLabelModel>>(lstModels).wrap());
    }
}
