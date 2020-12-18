package com.active4j.service.sw.impl;


import com.active4j.dao.sw.OrderDetailMapper;
import com.active4j.entity.sw.OrderDetail;
import com.active4j.service.sw.IOrderDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-11-27
 */
@Service("orderDetailService")
@Transactional
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements IOrderDetailService {

}
