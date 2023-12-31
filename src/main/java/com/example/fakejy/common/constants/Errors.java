package com.example.fakejy.common.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Errors {

    AUTH_ERROR(1001, "未登录"),
    SYSTEM_ERROR(9999, "系统异常"),
    ;

    private final Integer errorCode;

    private final String errorMsg;
}
