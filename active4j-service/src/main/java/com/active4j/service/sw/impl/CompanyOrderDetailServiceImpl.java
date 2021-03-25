package com.active4j.service.sw.impl;


import com.active4j.dao.sw.CompanyOrderDetailDoMapper;
import com.active4j.dao.sw.CompanyOrderDetailMapper;
import com.active4j.dao.sw.OrderDetailMapper;
import com.active4j.entity.sw.CompanyOrderDetail;
import com.active4j.entity.sw.OrderDetail;
import com.active4j.service.sw.ICompanyOrderDetailService;
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
@Service("companyOrderDetailService")
@Transactional
public class CompanyOrderDetailServiceImpl extends ServiceImpl<CompanyOrderDetailDoMapper, CompanyOrderDetail> implements ICompanyOrderDetailService {

}
