package com.active4j.entity.func.export.entity;

import com.active4j.entity.base.BaseEntity;
import com.active4j.entity.base.annotation.QueryField;
import com.active4j.entity.base.model.QueryCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * <p>
 * Excel 实体
 * </p>
 *
 * @author jobob
 * @since 2022-03-22
 */
@TableName("sw_company_order_detail")
@Getter
@Setter
public class CompanyOrderDetailEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableField("SHOP_NAME")
    private String shopName;

    @QueryField(queryColumn = "ORDER_ID", condition = QueryCondition.eq)
    private String orderId;

    @QueryField(queryColumn = "RECEIVER_NAME", condition = QueryCondition.eq)
    private String receiverName;

    @QueryField(queryColumn = "RECEIVER_ID", condition = QueryCondition.eq)
    private String receiverId;

    @QueryField(queryColumn = "RECEIVER_PHONE", condition = QueryCondition.eq)
    private String receiverPhone;

    private String receiverAddress;

    private String memo;

    private String extendInfo;

    @QueryField(queryColumn = "ORDER_STATUS", condition = QueryCondition.eq)
    private String orderStatus;

    @QueryField(queryColumn = "ORDER_CREATE_DATE", condition = QueryCondition.range)
    private Date orderCreateDate;

    private String goodsDetail;

    private String orderChangeLog;

    private String companyName;

    @QueryField(queryColumn = "INNER_DELIVERY_NO", condition = QueryCondition.eq)
    private String innerDeliveryNo;

    @QueryField(queryColumn = "OUT_DELIVERY_NO", condition = QueryCondition.eq)
    private String outDeliveryNo;

    @QueryField(queryColumn = "BUSINESS_TYPE", condition = QueryCondition.eq)
    private String businessType;

    private String messageLog;

    @Override
    public String toString() {
        return "CompanyOrderDetailEntity{" +
                "shopName='" + shopName + '\'' +
                ", orderId='" + orderId + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", receiverId='" + receiverId + '\'' +
                ", receiverPhone='" + receiverPhone + '\'' +
                ", receiverAddress='" + receiverAddress + '\'' +
                ", memo='" + memo + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", orderCreateDate=" + orderCreateDate +
                ", goodsDetail='" + goodsDetail + '\'' +
                ", companyName='" + companyName + '\'' +
                ", innerDeliveryNo='" + innerDeliveryNo + '\'' +
                ", outDeliveryNo='" + outDeliveryNo + '\'' +
                ", businessType='" + businessType + '\'' +
                '}';
    }
}
