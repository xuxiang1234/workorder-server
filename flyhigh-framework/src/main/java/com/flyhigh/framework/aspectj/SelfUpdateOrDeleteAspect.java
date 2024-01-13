package com.flyhigh.framework.aspectj;


import com.alibaba.fastjson.JSON;
import com.flyhigh.common.annotation.SelfUpdateOrDelete;
import com.flyhigh.common.exception.ErrorCodeConstants;
import com.flyhigh.common.exception.ServiceExceptionUtil;
import com.flyhigh.common.threadLocal.SelfUpdateOrDeleteHolder;
import com.flyhigh.common.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
public class SelfUpdateOrDeleteAspect {

    /**
     * @param pjp 切点
     */
    @Around("@annotation(com.flyhigh.common.annotation.SelfUpdateOrDelete)")
    public Object doAfterReturning(ProceedingJoinPoint pjp) throws Throwable {
        // 获取方法签名
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        // 获取方法
        Method method = methodSignature.getMethod();
        // 获取方法上面的注解
        SelfUpdateOrDelete anno = method.getAnnotation(SelfUpdateOrDelete.class);
        log.info("valueEl = {}", anno.value());
        // 解析id的值和当前登录用户id
        EvaluationContext context = getContext(pjp.getArgs(), method);
        Object idObj = getValue(context, anno.value());
        log.info("idObj={}", idObj);
        // 存入ThreadLocal
        try {
            SelfUpdateOrDeleteHolder.put(new SelfUpdateOrDeleteHolder.Info(idObj, SecurityUtils.getUserId(), anno.entities()));
            return pjp.proceed();
        } finally {
            SelfUpdateOrDeleteHolder.remove();
        }
    }

    /**
     * 获取spel 定义的参数值
     *
     * @param context 参数容器
     * @param key     key
     * @return 参数值
     */
    private Object getValue(EvaluationContext context, String key) {
        SpelExpressionParser spelExpressionParser = new SpelExpressionParser();
        Expression expression = spelExpressionParser.parseExpression(key);
        return expression.getValue(context);
    }


    /**
     * 获取参数容器
     *
     * @param arguments       方法的参数列表
     * @param signatureMethod 被执行的方法体
     * @return 装载参数的容器
     */
    private EvaluationContext getContext(Object[] arguments, Method signatureMethod) {

        String[] parameterNames = new LocalVariableTableParameterNameDiscoverer().getParameterNames(signatureMethod);
        if (parameterNames == null) {
            log.info("selfUpdateOrDeleteAspect happen error!! method={}, args={}", signatureMethod.getName(), JSON.toJSONString(arguments));
            throw ServiceExceptionUtil.exception(ErrorCodeConstants.SYSTEM_ERROR);
        }

        EvaluationContext context = new StandardEvaluationContext();
        for (int i = 0; i < arguments.length; i++) {
            context.setVariable(parameterNames[i], arguments[i]);
        }
        return context;
    }
}
