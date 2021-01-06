package com.active4j.web.sw;


import com.active4j.common.web.controller.BaseController;
import com.active4j.entity.base.PageInfo;
import com.active4j.entity.base.annotation.Log;
import com.active4j.entity.base.model.LogType;
import com.active4j.entity.base.model.ResultJson;
import com.active4j.entity.base.model.ValueLabelModel;
import com.active4j.entity.base.wrapper.BaseWrapper;
import com.active4j.entity.sw.OrderDetail;
import com.active4j.service.sw.IOrderDetailService;
import com.active4j.service.system.util.SystemUtils;
import com.active4j.web.core.web.util.QueryUtils;
import com.active4j.web.core.web.util.ResponseUtil;
import com.active4j.web.system.wrapper.SellerOrderDetailWrapper;
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
 * 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-11-27
 */
@Slf4j
@RestController
@RequestMapping("/commerce/order")
public class OrderDetailController extends BaseController {

    @Autowired
    private IOrderDetailService iOrderDetailService;

    @RequestMapping(value = "datagrid")
    @ApiOperation(value = "获取所有seller订单", notes = "获取所有seller订单", response = BaseWrapper.class)
    @ResponseBody
    public void datagrid(OrderDetail orderDetail, PageInfo<OrderDetail> page,
                         HttpServletRequest request, HttpServletResponse response) {
        //拼接查询条件
        QueryWrapper<OrderDetail> queryWrapper = QueryUtils.installQueryWrapper(orderDetail, request.getParameterMap());

        //执行查询
        IPage<OrderDetail> orderDetailIPage = iOrderDetailService.page(page.getPageEntity(), queryWrapper);

        //结果处理,直接写到客户端
        ResponseUtil.write(response, new SellerOrderDetailWrapper(orderDetailIPage).wrap());
    }

    /**
     * 保存方法
     *
     * @param orderDetail
     * @return ResultJson
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    @Log(type = LogType.save, name = "保存用户信息", memo = "新增或编辑保存了用户信息")
    @ApiOperation(value = "保存seller订单", notes = "保存seller订单")
    @ApiImplicitParam(name = "orderDetail", value = "传入用户实体对象", required = true, dataType = "OrderDetail")
    public ResultJson save(@Validated OrderDetail orderDetail) {
        ResultJson resultJson = new ResultJson();
        try {
            if (StringUtils.isEmpty(orderDetail.getId())) {
                iOrderDetailService.save(orderDetail);
            } else {
                iOrderDetailService.updateById(orderDetail);
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
    @ApiOperation(value = "获取单条seller Order Detail", notes = "获取单条seller Order Detail", response = BaseWrapper.class)
    public void info(@ApiParam(name = "id", value = "seller order id") String id, HttpServletResponse response) {

        OrderDetail orderDetail = iOrderDetailService.getById(id);
        //结果处理,直接写到客户端
        ResponseUtil.write(response, new BaseWrapper<OrderDetail>(orderDetail).wrap());
    }

    /**
     * 删除操作
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    @Log(type = LogType.del, name = "删除seller order信息", memo = "删除 seller order信息")
    @ApiOperation(value = "删除部门", notes = "根据订单 ID 删除")
    public ResultJson delete(@ApiParam(name = "id", value = "id") String id) {
        ResultJson j = new ResultJson();
        try {
            iOrderDetailService.removeById(id);
        } catch (Exception e) {
            j.setSuccess(false);
            j.setMsg("删除失败");
            log.error("删除用户信息出错，错误信息：{}", e.getMessage());
            e.printStackTrace();
        }

        return j;
    }

    /**
     *
     * @description
     *  	页面获取日志类型
     * @params
     * @return void
     * @author guyp
     * @time 2019年12月31日 上午10:06:01
     */
    @RequestMapping(value="/orderStatusDict")
    @ApiOperation(value="获取日志类型", notes="页面获取日志类型", response=BaseWrapper.class)
    public void logType(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> map = SystemUtils.getDictionaryMap("order_status");
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
     *
     * @description
     *  	页面获取日志类型
     * @params
     * @return void
     * @author guyp
     * @time 2019年12月31日 上午10:06:01
     */
    @RequestMapping(value="/shopNameDict")
    @ApiOperation(value="获取日志类型", notes="页面获取日志类型", response=BaseWrapper.class)
    public void shopName(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> map = SystemUtils.getDictionaryMap("sw_shop_name");
        List<ValueLabelModel> lstModels = new ArrayList<ValueLabelModel>();
        if(null != map && map.keySet().size() > 0) {
            for(String key : map.keySet()) {
                ValueLabelModel model = new ValueLabelModel();
                model.setLabel(map.get(key));
                model.setValue(key);
                lstModels.add(model);
            }
        }

        //结果处理,直接写到客户端
        ResponseUtil.write(response, new BaseWrapper<List<ValueLabelModel>>(lstModels).wrap());
    }

}
