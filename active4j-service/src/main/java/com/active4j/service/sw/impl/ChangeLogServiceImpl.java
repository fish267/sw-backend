package com.active4j.service.sw.impl;

import com.active4j.dao.sw.ChangeLogMapper;
import com.active4j.entity.sw.ChangeLog;
import com.active4j.service.sw.IChangeLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-12-01
 */
@Service
@Transactional
public class ChangeLogServiceImpl extends ServiceImpl<ChangeLogMapper, ChangeLog> implements IChangeLogService {

}
