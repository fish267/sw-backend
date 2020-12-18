package com.active4j.web.system.wrapper;

import com.active4j.entity.base.wrapper.BaseTableWrapper;
import com.active4j.entity.sw.OrderDetail;
import com.active4j.entity.system.entity.SysUserEntity;
import com.active4j.service.system.util.SystemUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * @author fish
 * Copyright (C) 2004-2020 Alibaba All Rights Reserved.
 * @version SellerOrderDetailWrapper.java, v 0.1 2020-11-30 19:37 fish Exp
 * Coding to change the world for better!
 */
public class SellerOrderDetailWrapper extends BaseTableWrapper<OrderDetail> {

    public SellerOrderDetailWrapper(IPage<OrderDetail> pageResult) {
        //父类中的方法初始化数据
        super(pageResult);

    }

}
