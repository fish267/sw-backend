package com.active4j.web.sw;

import com.active4j.common.func.upload.util.FileUploadUtils;
import com.active4j.common.web.controller.BaseController;
import com.active4j.entity.base.PageInfo;
import com.active4j.entity.base.annotation.Log;
import com.active4j.entity.base.model.LogType;
import com.active4j.entity.base.model.ResultJson;
import com.active4j.entity.func.export.entity.CompanyOrderDetailEntity;
import com.active4j.entity.func.export.entity.ExportExampleEntity;
import com.active4j.service.func.export.service.ExportCompanyOrderService;
import com.active4j.service.func.export.service.ExportExampleService;
import com.active4j.web.core.web.util.QueryUtils;
import com.active4j.web.core.web.util.ResponseUtil;
import com.active4j.web.func.export.wrapper.CompanyOrderDetailWrapper;
import com.active4j.web.func.export.wrapper.ExportExampleWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * Excel 文件上传下载DEMO
 *
 * @author fish
 * @version 1.0
 * @title ToCompanyOrderController.java
 * @description 导入导出管理
 */
@Controller
@RequestMapping("/sw/export")
@Slf4j
@Api(value = "常用功能-导入导出", tags = {"导入导出操作接口"})
public class ToCompanyOrderController extends BaseController {


    @Autowired
    private ExportCompanyOrderService exportCompanyOrderService;

    /**
     * @return void
     * @description 获取表格数据
     * @params
     * @author guyp
     * @time 2019年12月17日 上午10:52:46
     */
    @RequestMapping(value = "/datagrid")
    @ResponseBody
    @ApiOperation(value = "获取导入导出列表", notes = "获取导入导出列表", response = ExportExampleWrapper.class)
    public void datagrid(CompanyOrderDetailEntity companyOrderDetailEntity, PageInfo<CompanyOrderDetailEntity> page,
                         HttpServletRequest request, HttpServletResponse response) {
        //拼接查询条件
        QueryWrapper<CompanyOrderDetailEntity> queryWrapper = QueryUtils.installQueryWrapper(companyOrderDetailEntity,
                request.getParameterMap());
        //执行查询
        IPage<CompanyOrderDetailEntity> lstResult = exportCompanyOrderService.page(page.getPageEntity(), queryWrapper);
        //结果处理，直接写到客户端
        ResponseUtil.write(response, new CompanyOrderDetailWrapper(lstResult).wrap());
    }

    /**
     * @return AjaxJson
     * @description 导入
     * @params
     * @author guyp
     * @time 2019年12月17日 下午4:20:37
     */
    @RequestMapping(value = "/upload")
    @ResponseBody
    @ApiOperation(value = "导入文件", notes = "导入文件")
    @Log(type = LogType.update, name = "上传文件", memo = "上传物流文件")
    public ResultJson upload(MultipartHttpServletRequest request, HttpServletResponse response) {
        ResultJson j = new ResultJson();
        try {
            log.info("进入导入接口");
            Map<String, MultipartFile> fileMap = request.getFileMap();

            for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
                // 获取上传文件对象
                MultipartFile mf = entity.getValue();
                //获取文件后缀名
                String extName = FileUploadUtils.getExtension(mf);
                //获得文件输入流
                InputStream inputStream = mf.getInputStream();
				/*根据excel后缀判断
				xlsx可以有1048576行、16384列
				xls只有65536行、256列*/
                if (StringUtils.equals("xlsx", extName)) {
                    //保存xlsx的内容
                    exportCompanyOrderService.saveXlsx(inputStream);
                } else if (StringUtils.equals("xls", extName)) {
                    //保存xls的内容
                    exportCompanyOrderService.saveXlsx(inputStream);
                } else {
                    j.setSuccess(false);
                    j.setMsg("您上传的文件包含不支持的格式，请重新上传");
                    return j;
                }

                j.setData(mf.getOriginalFilename());
                //关闭流
                inputStream.close();
                //单次上传，不需要再次进入循环
                break;
            }
        } catch (Exception e) {
            log.error("导入报错，错误信息：{}", e.getMessage());
            j.setSuccess(false);
            j.setMsg("导入文件失败");
            e.printStackTrace();
        }

        return j;
    }

    /**
     * @return void
     * @description 导出xls文件
     * @params
     * @author guyp
     * @time 2019年12月18日 上午10:03:09
     */
    @RequestMapping("/xlsx")
    @ApiOperation(value = "导出xls文件", notes = "导出xls文件")
    @Log(type = LogType.normal, name = "导出文件", memo = "文件导出")
    public void xls(CompanyOrderDetailEntity companyOrderDetailEntity, HttpServletRequest request, HttpServletResponse response) {
        try {
            //拼接查询条件
            QueryWrapper<CompanyOrderDetailEntity> queryWrapper = QueryUtils.installQueryWrapper(companyOrderDetailEntity,
                    request.getParameterMap());
            //执行查询
            List<CompanyOrderDetailEntity> lstResult = exportCompanyOrderService.list(queryWrapper);
            //结果处理，直接写到客户端
            //导出xls文件
            exportCompanyOrderService.exportXlsx(request, response, lstResult);
        } catch (Exception e) {
            log.error("导出xls报错，错误信息：{}", e.getMessage());
            e.printStackTrace();
        }
    }

}
