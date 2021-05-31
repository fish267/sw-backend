package com.active4j.entity.sw;

import com.active4j.entity.base.BaseEntity;
import com.active4j.entity.base.annotation.QueryField;
import com.active4j.entity.base.model.QueryCondition;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 变更记录表
 * </p>
 *
 * @author jobob
 * @since 2022-03-22
 */
@TableName("sw_change_log")
@Getter
@Setter
@ApiModel
public class ChangeLog extends BaseEntity {

    private static final long serialVersionUID = 1L;


    @QueryField(queryColumn = "type", condition = QueryCondition.eq)
    private String type;

    @QueryField(queryColumn = "value", condition = QueryCondition.eq)
    private String value;

    @QueryField(queryColumn = "dict_key", condition = QueryCondition.eq)
    private String dictKey;

    private String memo;

    private String extendInfo;


}
