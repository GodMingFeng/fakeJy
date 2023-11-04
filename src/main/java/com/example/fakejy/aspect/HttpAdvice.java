package com.example.fakejy.aspect;

import com.example.fakejy.common.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class HttpAdvice {

    @ExceptionHandler(Throwable.class)
    public Response<String> handleException(Throwable ex) {
        log.error("[http请求异常][e={}]", ex.getMessage());
        return Response.error();
    }
}
