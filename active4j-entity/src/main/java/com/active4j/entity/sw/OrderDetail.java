package com.active4j.entity.sw;

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
 *
 * </p>
 *
 * @author jobob
 * @since 2020-11-27
 */
@TableName("sw_order_detail")
@Getter
@Setter
@ApiModel
public class OrderDetail extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableField("SHOP_NAME")
    private String shopName;

    @QueryField(queryColumn="ORDER_ID", condition= QueryCondition.eq)
    private String orderId;

    @QueryField(queryColumn="RECEIVER_NAME", condition= QueryCondition.eq)
    private String receiverName;

    @QueryField(queryColumn="RECEIVER_ID", condition= QueryCondition.eq)
    private String receiverId;

    @QueryField(queryColumn="RECEIVER_PHONE", condition= QueryCondition.eq)
    private String receiverPhone;

    private String receiverAddress;

    private String memo;

    private String extendInfo;

    @QueryField(queryColumn="ORDER_STATUS", condition= QueryCondition.eq)
    private String orderStatus;

    private String afterSaleReason;

    private String afterSaleOwner;

    private String afterSaleStatus;

    @QueryField(queryColumn="ORDER_CREATE_DATE", condition= QueryCondition.range)
    private Date orderCreateDate;

    @QueryField(queryColumn="DELIVERY_STYLE", condition= QueryCondition.eq)
    private String deliveryStyle;

    @QueryField(queryColumn="DELIVERY_FLIGHT", condition= QueryCondition.eq)
    private String deliveryFlight;

    private String clearanceNo;

    private String dataTransfer;

    private String cangStatus;

    private String ganStatus;

    private String paiStatus;

    @QueryField(queryColumn="SECOND_DELIVERY_NO", condition= QueryCondition.eq)
    private String secondDeliveryNo;

    private String zhuanStatus;

    private String customerEdit;

    private String latestInfo;

    private String deliveryStatus;

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
