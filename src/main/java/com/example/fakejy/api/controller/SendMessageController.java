package com.example.fakejy.api.controller;

import com.example.fakejy.api.convert.ActivityConverter;
import com.example.fakejy.api.request.ActivityDetailRequest;
import com.example.fakejy.api.request.ActivityPageRequest;
import com.example.fakejy.api.response.ActivityDetailResponse;
import com.example.fakejy.api.response.ActivityPageResponse;
import com.example.fakejy.common.Page;
import com.example.fakejy.common.Response;
import com.example.fakejy.common.utils.BeanCopiers;
import com.example.fakejy.common.utils.MessageUtils;
import com.example.fakejy.core.service.activity.ActivityService;
import com.example.fakejy.core.service.activity.RankService;
import com.example.fakejy.core.service.activity.ao.QueryActivityAO;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(path = "/fake_jy")
public class SendMessageController {

    @ResponseBody
    @RequestMapping(path = "/sendMessage", method = RequestMethod.GET)
    public String sendMessageGet(@RequestParam String signature, @RequestParam String timestamp, @RequestParam String nonce, @RequestParam String echostr) {
        return echostr == null ? "success" : echostr;
    }

    @ResponseBody
    @SneakyThrows
    @RequestMapping(path = "/sendMessage", method = RequestMethod.POST, consumes = {MediaType.TEXT_XML_VALUE}, produces = {MediaType.TEXT_XML_VALUE})
    public String sendMessagePost(@RequestParam String timestamp, @RequestParam String nonce) {
        return MessageUtils.WX.encryptMsg("success", timestamp, nonce);
    }
}
