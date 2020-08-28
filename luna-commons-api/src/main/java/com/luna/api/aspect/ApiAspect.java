package com.luna.api.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.luna.common.anno.MyValid;
import com.luna.common.dto.ResultDTO;
import com.luna.common.dto.constant.ResultCode;
import com.luna.common.exception.base.BaseException;
import com.luna.common.utils.md5.HashUtils;
import com.luna.elasticsearch.exception.ElasticsearchException;
import org.apache.commons.collections4.CollectionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.lang.reflect.Parameter;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * @author Tony
 */
@Order(1)
@Aspect
@Component
public class ApiAspect {
    private final static Logger                                 logger               =
        LoggerFactory.getLogger(ApiAspect.class);

    /** 忽略日志的REST */
    private final static Map<String, List<String>>              IGNORE_LOG_METHOD    =
        ImmutableMap.<String, List<String>>builder()
            // .put("SweeneyRest", ImmutableList.of("hasJob"))
            .build();

    private final static List<Map<Locale, Map<String, String>>> TRANSLATION_MAP_LIST =
        ImmutableList.of(ResultCode.TRANSLATION_MAP);

    @Autowired
    private Validator                                           validator;

    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    public void rest() {}

    @Around("rest()")
    public Object doRestAround(ProceedingJoinPoint pjp) {
        boolean needLog = needLog(pjp);

        Object[] args = fliterParam(pjp.getArgs());

        if (needLog) {
            HttpServletRequest request =
                ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
            MDC.put("uri", request.getRequestURI());

            MDC.put("traceId", HashUtils.randomHex32());

            logger.info("rest request, param={}", JSON.toJSONString(args, SerializerFeature.IgnoreNonFieldGetter));
        }
        try {
            checkParam(pjp);
            Object proceed = pjp.proceed();
            if (needLog) {
                // logger.info("rest respond success, param={}, respond={}",
                // JSON.toJSONString(args, SerializerFeature.IgnoreNonFieldGetter),
                // JSON.toJSONString(proceed, SerializerFeature.IgnoreNonFieldGetter));
            }
            return proceed;
        } catch (Throwable t) {
            ResultDTO<Object> failResult = processException(t);
            if (needLog) {
                logger.error("rest respond failed, param={}, respond={}",
                    JSON.toJSONString(args, SerializerFeature.IgnoreNonFieldGetter),
                    JSON.toJSONString(failResult, SerializerFeature.IgnoreNonFieldGetter));
            }
            return failResult;
        }
    }

    private Object[] fliterParam(Object[] args) {
        List<Object> objectList = Lists.newArrayList();
        for (Object object : args) {
            if (object instanceof HttpServletRequest == false
                && object instanceof MultipartFile == false) {
                objectList.add(object);
            }
        }

        return objectList.toArray();
    }

    private boolean needLog(ProceedingJoinPoint pjp) {
        MethodSignature methodSignature = (MethodSignature)pjp.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String method = methodSignature.getMethod().getName();

        if (IGNORE_LOG_METHOD.containsKey(className) == false) {
            return true;
        }

        if (IGNORE_LOG_METHOD.get(className).contains(method)) {
            return false;
        }

        return true;
    }

    private ResultDTO<Object> processException(Throwable t) {
        if (BaseException.isBaseException(t)) {
            return processBaseException(t);
        } else {
            return processOtherException(t);
        }
    }

    private ResultDTO<Object> processBaseException(Throwable t) {
        logger.error(t.getMessage(), t);

        int code = ((BaseException)t).getCode();
        String message = translateMessage(t.getMessage());
        return new ResultDTO<>(false, code, message);
    }

    private String translateMessage(String message) {
        return ResultCode.translateMessage(message, TRANSLATION_MAP_LIST);
    }

    private ResultDTO<Object> processOtherException(Throwable t) {
        logger.error(t.getMessage(), t);
        return new ResultDTO<>(false, ResultCode.ERROR_SYSTEM_EXCEPTION, ResultCode.MSG_ERROR_SYSTEM_EXCEPTION);
    }

    private void checkParam(ProceedingJoinPoint pjp) {
        Parameter[] parameters = ((MethodSignature)pjp.getSignature()).getMethod().getParameters();
        Object[] args = pjp.getArgs();
        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            Object arg = args[i];
            MyValid annotation = parameter.getAnnotation(MyValid.class);
            if (annotation == null) {
                continue;
            }
            Set<ConstraintViolation<Object>> validResult = validator.validate(arg, annotation.value());
            if (CollectionUtils.isEmpty(validResult)) {
                continue;
            }
            logger.info("checkParam not pass, parameter={}, resultMessage={}", parameter, validResult);
            throw new ElasticsearchException(ResultCode.PARAMETER_INVALID,
                translateMessage(ResultCode.MSG_PARAMETER_INVALID));
        }
    }

}
