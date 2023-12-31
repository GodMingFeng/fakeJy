package com.example.fakejy.api.controller;


import com.example.fakejy.api.annotations.IgnoreAuth;
import com.example.fakejy.api.request.JsCode2sessionRequest;
import com.example.fakejy.api.response.JsCode2sessionResponse;
import com.example.fakejy.common.Response;
import com.example.fakejy.common.constants.AuthConstants;
import com.example.fakejy.common.utils.BeanCopiers;
import com.example.fakejy.core.service.login.LoginService;
import com.example.fakejy.core.service.login.ao.JsCode2sessionAO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping(path = {"/fake_jy"})
public class LoginController {

    @Resource
    private LoginService loginService;

    @ResponseBody
    @IgnoreAuth
    @RequestMapping(path = {"/login"}, method = {RequestMethod.POST})
    public Response<JsCode2sessionResponse> login(@RequestBody JsCode2sessionRequest request) {
        if (request.getJsCode() == null) {
            return Response.<JsCode2sessionResponse>error().errorMsg("前端code为空");
        }
        var ao = BeanCopiers.copy(request, new JsCode2sessionAO());
        ao.setSecret(AuthConstants.APP_SECRET);
        ao.setAppid(AuthConstants.APP_ID);
        ao.setGrantType("authorization_code");
        var loginToken = loginService.login(ao);
        var response = new JsCode2sessionResponse();
        response.setLoginToken(loginToken);
        return Response.<JsCode2sessionResponse>success().result(response);
    }
}
