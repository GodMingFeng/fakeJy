package com.example.fakejy.core.service.login.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.fakejy.core.service.login.ao.JsCode2sessionAO;
import com.example.fakejy.common.constants.AuthConstants;
import com.example.fakejy.common.utils.HttpUtils;
import com.example.fakejy.common.utils.UrlUtils;
import com.example.fakejy.core.service.login.LoginService;
import com.example.fakejy.core.service.login.bo.JsCode2sessionBO;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class LoginServiceImpl implements LoginService {
    @Override
    public JsCode2sessionBO login(JsCode2sessionAO request) {
        var result = HttpUtils.get(new HashMap<>(), UrlUtils.objectToMap(request), AuthConstants.JS_CODE_2_SESSION_URL);
        var response = JSONObject.parseObject(result, JsCode2sessionBO.class);
        if (response.getErrmsg() != null) {
            throw new RuntimeException(response.getErrmsg());
        }
        return response;
    }
}
