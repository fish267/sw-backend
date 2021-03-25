package com.active4j.service.func.export.service;

import com.active4j.entity.func.export.entity.CompanyOrderDetailEntity;
import com.active4j.entity.func.export.entity.ExportExampleEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.List;

/**
 * @author guyp
 * @version 1.0
 * @title ExportCompanyOrderService.java
 * @description 导入导出
 * @time 2019年12月17日 上午10:47:07
 */
public interface ExportCompanyOrderService extends IService<CompanyOrderDetailEntity> {

    /**
     * @return void
     * @description 保存xlsx格式的excel文件
     * @params
     * @author guyp
     * @time 2019年12月17日 下午1:33:40
     */
    public void saveXlsx(InputStream in) throws Exception;

    /**
     * @return HSSFWorkbook
     * @description 根据查询条件导出xlsx
     * @params name 姓名
     * @author guyp
     * @time 2019年12月18日 上午9:54:50
     */
    public void exportXlsx(HttpServletRequest request, HttpServletResponse response,
                           List<CompanyOrderDetailEntity> companyOrderDetailEntityList) throws Exception;

}
