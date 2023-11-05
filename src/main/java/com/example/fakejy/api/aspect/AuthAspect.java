package com.example.fakejy.api.aspect;

import com.example.fakejy.api.annotations.IgnoreAuth;
import com.example.fakejy.common.Response;
import com.example.fakejy.common.constants.Errors;
import com.example.fakejy.common.utils.HttpUtils;
import com.example.fakejy.common.utils.ThreadLocalHolder;
import com.example.fakejy.common.utils.UserInfoHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

@Slf4j
@Aspect
@Component
public class AuthAspect {

    @Resource
    private JedisPool jedisPool;

    @Around("execution(* com.example.fakejy.api.controller..*.*(..))")
    public Object aroundAnotherMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            var httpRequest = HttpUtils.getHttpRequest();
            var method = ((MethodSignature) joinPoint.getSignature()).getMethod();
            var ignoreAuth = AnnotationUtils.findAnnotation(method, IgnoreAuth.class);
            if (ignoreAuth == null) {
                var loginToken = httpRequest.getHeader("login-token");
                if (StringUtils.isEmpty(loginToken)) {
                    return Response.error(Errors.AUTH_ERROR);
                }
                try (var jedis = jedisPool.getResource()) {
                    var result = jedis.get(loginToken);
                    if (StringUtils.isEmpty(result)) {
                        return Response.error(Errors.AUTH_ERROR);
                    }
                    UserInfoHolder.setOpenId(result);
                }
            }
            return joinPoint.proceed();
        } finally {
            ThreadLocalHolder.clear();
        }

    }
}
