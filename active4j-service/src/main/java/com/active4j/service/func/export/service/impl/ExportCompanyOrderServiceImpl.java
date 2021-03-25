package com.active4j.service.func.export.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.active4j.common.func.export.util.ExcelUtil;
import com.active4j.common.func.export.util.ExportUtil;
import com.active4j.common.util.DateUtils;
import com.active4j.dao.func.export.dao.ExportExampleDao;
import com.active4j.dao.sw.CompanyOrderDetailMapper;
import com.active4j.entity.func.export.entity.CompanyOrderDetailEntity;
import com.active4j.entity.func.export.entity.ExportExampleEntity;
import com.active4j.entity.sw.CompanyOrderDetail;
import com.active4j.service.func.export.service.ExportCompanyOrderService;
import com.active4j.service.func.export.service.ExportExampleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.*;

import static com.active4j.common.util.MyBeanUtils.copyBean2Map;

/**
 * @author guyp
 * @version 1.0
 * @title ExportCompanyOrderServiceImpl.java
 * @description 导入导出
 * @time 2019年12月17日 上午10:48:51
 */
@Service("exportCompanyOrderService")
@Transactional
@Slf4j
public class ExportCompanyOrderServiceImpl extends ServiceImpl<CompanyOrderDetailMapper, CompanyOrderDetailEntity> implements
        ExportCompanyOrderService {

    /**
     * @return void
     * @throws Exception
     * @description 保存xlsx格式的excel文件
     * @params
     * @author guyp
     * @time 2019年12月17日 下午1:33:40
     */
    public void saveXlsx(InputStream in) throws Exception {
        List<CompanyOrderDetailEntity> lstExports = new ArrayList<>();
        //解析excel的内容
        List<List<Object>> lstA = ExcelUtil.readBigFile2(in, -1);
        //遍历行
        for (int i = 1; i < lstA.size(); i++) {
            List<Object> lstObj = lstA.get(i);
            //创建示例实体
            CompanyOrderDetailEntity export = new CompanyOrderDetailEntity();
            // 遍历 Excel，依次存入到实体中
            for (int j = 0; j < lstObj.size(); j++) {
                Object obj = lstObj.get(j);
                //属性赋值
                if (j == 0 && null != obj) {
                    export.setOrderId(obj.toString());
                    export.setId(obj.toString());
                } else if (j == 1 && null != obj) {
                    export.setCompanyName(obj.toString());
                } else if (j == 2 && null != obj) {
                    export.setOrderCreateDate(DateUtils.parseDateObj(obj));
                } else if (j == 3 && null != obj) {
                    export.setOrderStatus(obj.toString());
                } else if (j == 4 && null != obj) {
                    export.setInnerDeliveryNo(obj.toString());
                } else if (j == 5 && null != obj) {
                    export.setOutDeliveryNo(obj.toString());
                } else if (j == 6 && null != obj) {
                    export.setGoodsDetail(obj.toString());
                } else if (j == 7 && null != obj) {
                    export.setReceiverName(obj.toString());
                } else if (j == 8 && null != obj) {
                    export.setReceiverPhone(obj.toString());
                } else if (j == 9 && null != obj) {
                    export.setReceiverAddress(obj.toString());
                } else if (j == 10 && null != obj) {
                    export.setBusinessType(obj.toString());
                } else if (j == 11 && null != obj) {
                    export.setMemo(obj.toString());
                } else if (j == 12 && null != obj) {
                    export.setMessageLog(obj.toString());
                } else if (j == 13 && null != obj) {
                    export.setOrderChangeLog(obj.toString());
                }
            }
            // 判断是插入还是更新
            CompanyOrderDetailEntity queryObject = this.getById(export.getId());
            if (queryObject == null) {
                export.setOrderChangeLog(DateUtils.getNow().toString() + "  订单录入:\n" + export.toString());
            } else {
                //
                List<String> compareContent = diffTwoObject(export, queryObject);
                if (compareContent.size() != 0) {
                    export.setOrderChangeLog("\n" + export.getOrderChangeLog() + "\n" +
                            DateUtils.getNow().toString() + "订单更新");
                    for (String content : compareContent) {
                        export.setOrderChangeLog(export.getOrderChangeLog() + content);
                    }
                }
            }
            //实体不为空就假如集合
            if (null != export) {
                lstExports.add(export);
            }
        }

        //批量保存
        this.saveOrUpdateBatch(lstExports);
    }

    /**
     * 对比两个对象
     *
     * @param queryObject
     * @param newObject
     * @return
     */
    private List<String> diffTwoObject(CompanyOrderDetailEntity queryObject, CompanyOrderDetailEntity newObject) {
        List<String> compareContent = new ArrayList<>();
        HashMap<String, Object> originHashMap = new HashMap<>();
        HashMap<String, Object> newHashMap = new HashMap<>();
        copyBean2Map(originHashMap, queryObject);
        copyBean2Map(newHashMap, newObject);
        // 排除相似点检查
        List<String> skipAttribute = new ArrayList<String>() {{
            add("orderChangeLog");
            add("messageLog");
            add("createDate");
            add("updateDate");
            add("updateName");
            add("versions");
            add("createName");
            add("orderCreateDate");
        }};
        for (String key : originHashMap.keySet()) {
            // 去除后面的NPE问题
            if (null == originHashMap.get(key)) {
                originHashMap.put(key, "");
            }
            if (null == newHashMap.get(key)) {
                newHashMap.put(key, "");
            }
            if (!skipAttribute.contains(key) &&
                    !originHashMap.get(key).toString().equals(newHashMap.get(key).toString())) {
                compareContent.add(String.format("\n %s 变更，原值: %s, 更新后: %s",
                        key, originHashMap.get(key).toString(), newHashMap.get(key).toString()));
            }
        }
        return compareContent;
    }

    /**
     * @return HSSFWorkbook
     * @throws UnsupportedEncodingException
     * @description 根据查询条件导出xlsx
     * @params
     * @author guyp
     * @time 2019年12月18日 上午9:54:50
     */
    public void exportXlsx(HttpServletRequest request, HttpServletResponse response,
                           List<CompanyOrderDetailEntity> companyOrderDetailEntityList)
            throws Exception {

        String fileName = getFileName(request, DateUtil.today() + "_order_data.xlsx");
        //写出数据定义
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM.toString());
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");

        List<LinkedHashMap<String, Object>> datas = new ArrayList<>();
        LinkedHashMap<String, Object> data = new LinkedHashMap<>();

        //定义列数据

        data.put("0", "交易单号");
        data.put("1", "企业名称");
        data.put("2", "订单创建时间");
        data.put("3", "订单状态");
        data.put("4", "国内运单号");
        data.put("5", "国际运单号");
        data.put("6", "商品详情");
        data.put("7", "客户姓名");
        data.put("8", "客户电话");
        data.put("9", "客户地址");
        data.put("10", "业务类型");
        data.put("11", "备注信息");
        data.put("12", "操作日志");
        data.put("13", "报文日志");
        datas.add(data);


        // 依次插入
        for (CompanyOrderDetailEntity companyOrderDetailEntity : companyOrderDetailEntityList) {
            data = new LinkedHashMap<>();
            data.put("0", getStringValue(companyOrderDetailEntity.getId()));
            data.put("1", getStringValue(companyOrderDetailEntity.getCompanyName()));
            data.put("2", getStringValue(companyOrderDetailEntity.getOrderCreateDate()));
            data.put("3", getStringValue(companyOrderDetailEntity.getOrderStatus()));
            data.put("4", getStringValue(companyOrderDetailEntity.getInnerDeliveryNo()));
            data.put("5", getStringValue(companyOrderDetailEntity.getOutDeliveryNo()));
            data.put("6", getStringValue(companyOrderDetailEntity.getGoodsDetail()));
            data.put("7", getStringValue(companyOrderDetailEntity.getReceiverName()));
            data.put("8", getStringValue(companyOrderDetailEntity.getReceiverPhone()));
            data.put("9", getStringValue(companyOrderDetailEntity.getReceiverAddress()));
            data.put("10", getStringValue(companyOrderDetailEntity.getBusinessType()));
            data.put("11", getStringValue(companyOrderDetailEntity.getMemo()));
            data.put("12", getStringValue(companyOrderDetailEntity.getOrderChangeLog()));
            data.put("13", getStringValue(companyOrderDetailEntity.getMessageLog()));
            datas.add(data);
        }
        Map<String, List<LinkedHashMap<String, Object>>> tableData = new HashMap<>();
        tableData.put("TAB1", datas);

        //拷贝输出
        FileCopyUtils.copy(ExportUtil.exportXlsx(tableData), response.getOutputStream());
    }

    /**
     * String 转换工具
     *
     * @param obj
     * @return
     */
    private String getStringValue(Object obj) {
        if (obj instanceof Date) {
            return DateUtils.getDate2Str((Date) obj);
        } else {
            return null == obj ? "" : obj.toString();
        }
    }


    /**
     * @return String
     * @description 不同浏览器使用的默认的编码不同，有可能出现乱码
     * @params
     * @author guyp
     * @time 2019年12月18日 上午10:56:31
     */
    private String getFileName(HttpServletRequest request, String name) throws UnsupportedEncodingException {
        String userAgent = request.getHeader("USER-AGENT");
        return userAgent.contains("Mozilla") ? new String(name.getBytes(), "ISO8859-1") : name;
    }


    /**
     * @return void
     * @description 根据查询条件导出xls
     * @params
     * @author guyp
     * @time 2019年12月18日 上午11:22:57
     */
    public void exportXls(HttpServletRequest request, HttpServletResponse response, String name) throws Exception {
    }

}
