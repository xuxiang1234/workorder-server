package com.flyhigh.common.core.query;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.github.pagehelper.util.StringUtil;
import org.springframework.util.StringUtils;

import java.util.Collection;

/**
 * 拓展 MyBatis Plus QueryWrapper 类，主要增加如下功能：
 * <p>
 * 1. 拼接条件的方法，增加 xxxIfPresent 方法，用于判断值不存在的时候，不要拼接到条件中。
 *
 * @param <T> 数据类型
 */
public class LambdaQueryWrapperPlus<T> extends LambdaQueryWrapper<T> {

    public LambdaQueryWrapperPlus<T> likeIfPresent(SFunction<T, ?> column, String val) {
        if (StringUtils.hasText(val)) {
            return (LambdaQueryWrapperPlus<T>) super.like(column, val);
        }
        return this;
    }

    public LambdaQueryWrapperPlus<T> inIfPresent(SFunction<T, ?> column, Collection<?> values) {
        if (!CollectionUtils.isEmpty(values)) {
            return (LambdaQueryWrapperPlus<T>) super.in(column, values);
        }
        return this;
    }

    public LambdaQueryWrapperPlus<T> inIfPresent(SFunction<T, ?> column, Object... values) {
        if (!ArrayUtils.isEmpty(values)) {
            return (LambdaQueryWrapperPlus<T>) super.in(column, values);
        }
        return this;
    }

    public LambdaQueryWrapperPlus<T> eqIfPresent(SFunction<T, ?> column, Object val) {
        if (val != null) {
            if (whetherStringIsEmpty(val)) {
                return this;
            }
            return (LambdaQueryWrapperPlus<T>) super.eq(column, val);
        }
        return this;
    }

    public LambdaQueryWrapperPlus<T> neIfPresent(SFunction<T, ?> column, Object val) {
        if (val != null) {
            if (whetherStringIsEmpty(val)) {
                return this;
            }
            return (LambdaQueryWrapperPlus<T>) super.ne(column, val);
        }
        return this;
    }

    public boolean whetherStringIsEmpty(Object val) {
        if (val instanceof String) {
            if (StringUtil.isEmpty(String.valueOf(val))) {
                return true;
            }
        }
        return false;
    }

    public LambdaQueryWrapperPlus<T> gtIfPresent(SFunction<T, ?> column, Object val) {
        if (val != null) {
            return (LambdaQueryWrapperPlus<T>) super.gt(column, val);
        }
        return this;
    }

    public LambdaQueryWrapperPlus<T> geIfPresent(SFunction<T, ?> column, Object val) {
        if (val != null) {
            return (LambdaQueryWrapperPlus<T>) super.ge(column, val);
        }
        return this;
    }

    public LambdaQueryWrapperPlus<T> ltIfPresent(SFunction<T, ?> column, Object val) {
        if (val != null) {
            return (LambdaQueryWrapperPlus<T>) super.lt(column, val);
        }
        return this;
    }

    public LambdaQueryWrapperPlus<T> leIfPresent(SFunction<T, ?> column, Object val) {
        if (val != null) {
            return (LambdaQueryWrapperPlus<T>) super.le(column, val);
        }
        return this;
    }

    public LambdaQueryWrapperPlus<T> betweenIfPresent(SFunction<T, ?> column, Object val1, Object val2) {
        if (val1 != null && val2 != null) {
            return (LambdaQueryWrapperPlus<T>) super.between(column, val1, val2);
        }
        if (val1 != null) {
            return (LambdaQueryWrapperPlus<T>) ge(column, val1);
        }
        if (val2 != null) {
            return (LambdaQueryWrapperPlus<T>) le(column, val2);
        }
        return this;
    }

    // ========== 重写父类方法，方便链式调用 ==========

    @Override
    public LambdaQueryWrapperPlus<T> eq(boolean condition, SFunction<T, ?> column, Object val) {
        super.eq(condition, column, val);
        return this;
    }

    @Override
    public LambdaQueryWrapperPlus<T> eq(SFunction<T, ?> column, Object val) {
        super.eq(column, val);
        return this;
    }

    @Override
    public LambdaQueryWrapperPlus<T> orderByDesc(SFunction<T, ?> column) {
        super.orderByDesc(true, column);
        return this;
    }

    @Override
    public LambdaQueryWrapperPlus<T> last(String lastSql) {
        super.last(lastSql);
        return this;
    }

    @Override
    public LambdaQueryWrapperPlus<T> in(SFunction<T, ?> column, Collection<?> coll) {
        super.in(column, coll);
        return this;
    }

}
