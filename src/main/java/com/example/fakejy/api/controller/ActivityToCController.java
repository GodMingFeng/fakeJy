package com.example.fakejy.api.controller;

import com.example.fakejy.api.request.ActivityDetailRequest;
import com.example.fakejy.api.request.ActivityPageRequest;
import com.example.fakejy.api.response.ActivityDetailResponse;
import com.example.fakejy.api.response.ActivityPageResponse;
import com.example.fakejy.common.Page;
import com.example.fakejy.common.Response;
import com.example.fakejy.common.constants.AuthConstants;
import com.example.fakejy.common.utils.BeanCopiers;
import com.example.fakejy.core.service.activity.ActivityService;
import com.example.fakejy.core.service.activity.ao.QueryActivityAO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;

@RestController
@RequestMapping(path = "/fake_jy")
public class ActivityToCController {

    @Resource
    private ActivityService activityService;

    @ResponseBody
    @RequestMapping(path = "/getActivityDetail", method = RequestMethod.POST)
    public Response<ActivityDetailResponse> getActivityDetail(@RequestBody ActivityDetailRequest activityDetailRequest) {
        var result = activityService.queryActivityById(activityDetailRequest.getId());
        return Response.<ActivityDetailResponse>success().result(BeanCopiers.copy(result, new ActivityDetailResponse()));
    }

    @ResponseBody
    @RequestMapping(path = "/queryActivity", method = RequestMethod.POST)
    public Response<Page<ActivityPageResponse>> queryActivity(@RequestBody ActivityPageRequest activityPageRequest) {
        var result = activityService.queryActivities(BeanCopiers.copy(activityPageRequest, QueryActivityAO.class));
        return Response.<Page<ActivityPageResponse>>success().result(BeanCopiers.copyPage(result, ActivityPageResponse.class));
    }

    @ResponseBody
    @RequestMapping(path = "/sendMessage", method = RequestMethod.GET, consumes = {MediaType.TEXT_XML_VALUE}, produces = {MediaType.TEXT_XML_VALUE})
    public String sendMessageGet(@RequestParam String signature, @RequestParam String timestamp, @RequestParam String nonce, @RequestParam String echostr) {
        return echostr == null ? "success" : echostr;
    }

    @ResponseBody
    @RequestMapping(path = "/sendMessage", method = RequestMethod.POST, consumes = {MediaType.TEXT_XML_VALUE}, produces = {MediaType.TEXT_XML_VALUE})
    public String sendMessagePost() {
        return "success";
    }

    private boolean checkSignature(String signature, String timestamp, String nonce) {
        var token = AuthConstants.SEND_MESSAGE_TOKEN; // Replace TOKEN with your token
        var tmpArr = new String[]{token, timestamp, nonce};
        Arrays.sort(tmpArr);
        var tmpStr = String.join("", tmpArr);
        tmpStr = sha1(tmpStr);
        return tmpStr.equals(signature);
    }

    private String sha1(String input) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("SHA-1");
            byte[] digest = md.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
