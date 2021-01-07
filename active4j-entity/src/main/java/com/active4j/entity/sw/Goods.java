package com.active4j.entity.sw;

import com.active4j.entity.base.BaseEntity;
import com.active4j.entity.base.annotation.QueryField;
import com.active4j.entity.base.model.QueryCondition;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author jobob
 * @since 2020-12-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sw_goods")
public class Goods extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @QueryField(queryColumn="ORDER_ID", condition= QueryCondition.eq)
    private String orderId;

    private String memo;

    private String extendInfo;

    @QueryField(queryColumn="ORDER_STATUS", condition= QueryCondition.eq)
    private String orderStatus;

    @TableId(value="GOODS_ID", type=IdType.AUTO)
    private String goodsId;

    @QueryField(queryColumn="GOODS_NAME", condition= QueryCondition.like)
    private String goodsName;

    private String goodsCount;

    private String goodsAmount;

    private String purchaseStatus;

    private String purchaseAddress;

    @QueryField(queryColumn = "PURCHASE_ORDER_NO", condition = QueryCondition.eq)
    private String purchaseOrderNo;

    @QueryField(queryColumn = "GOODS_STATUS", condition = QueryCondition.eq)
    private String goodsStatus;

    private String purchaseAmount;

    @QueryField(queryColumn = "PURCHASE_TRANS_STYLE", condition = QueryCondition.eq)
    private String purchaseTransStyle;

    @QueryField(queryColumn = "FIRST_COMMERCE_ORDER", condition = QueryCondition.like)
    private String firstCommerceOrder;

    private String depotStatus;

    private String goodsWeight;

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}