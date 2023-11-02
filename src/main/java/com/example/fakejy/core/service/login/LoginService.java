package com.example.fakejy.core.service.login;

import com.example.fakejy.core.service.login.ao.JsCode2sessionAO;
import com.example.fakejy.core.service.login.bo.JsCode2sessionBO;

public interface LoginService {

    /**
     * 用户登录
     *
     * @param request 请求
     * @return 结果
     */
    JsCode2sessionBO login(JsCode2sessionAO request);
}
