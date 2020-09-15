package com.luna.jpa.utils;

import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SimpleExpression implements CriterionConstant {
    /**
     * 属性名
     */
    private String   fieldName;
    /**
     * 对应值
     */
    private Object   value;
    /**
     * 计算符
     */
    private Operator operator;


    protected SimpleExpression(String fieldName, Object value, Operator operator) {
        this.fieldName = fieldName;
        this.value = value;
        this.operator = operator;
    }


    // 此处是表关联数据，注意仅限一层关联，如user.address，
    // 查询user的address集合中，address的name为某个值
    @Override
    @SuppressWarnings({"rawtypes", "unchecked"})
    public Predicate toPredicate(Root<?> root, CriteriaQuery<?> query,
        CriteriaBuilder builder) {
        Path expression;
        if (fieldName.contains(".")) {
            String[] names = StringUtils.split(fieldName, ".");
            // 获取该属性的类型，Set？List？Map？
            expression = root.get(names[0]);
            Class clazz = expression.getJavaType();
            if (clazz.equals(Set.class)) {
                SetJoin setJoin = root.joinSet(names[0]);
                expression = setJoin.get(names[1]);
            } else if (clazz.equals(List.class)) {
                ListJoin listJoin = root.joinList(names[0]);
                expression = listJoin.get(names[1]);
            } else if (clazz.equals(Map.class)) {
                MapJoin mapJoin = root.joinMap(names[0]);
                expression = mapJoin.get(names[1]);
            } else {
                // 是many to one时
                expression = expression.get(names[1]);
            }

        } else {
            // 单表查询
            expression = root.get(fieldName);
        }

        switch (operator) {
            case EQ:
                return builder.equal(expression, value);
            case NE:
                return builder.notEqual(expression, value);
            case LIKE:
                return builder.like((Expression<String>)expression, "%" + value + "%");
            case LT:
                return builder.lessThan(expression, (Comparable)value);
            case GT:
                return builder.greaterThan(expression, (Comparable)value);
            case LTE:
                return builder.lessThanOrEqualTo(expression, (Comparable)value);
            case GTE:
                return builder.greaterThanOrEqualTo(expression, (Comparable)value);
            case IS_MEMBER:
                return builder.isMember(value, expression);
            case IS_NOT_MEMBER:
                return builder.isNotMember(value, expression);
            default:
                return null;
        }
    }

}
