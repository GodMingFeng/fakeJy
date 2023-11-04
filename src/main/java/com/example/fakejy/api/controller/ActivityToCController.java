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
public class ActivityToCController {

    @Resource
    private ActivityService activityService;

    @Resource
    private RankService rankService;

    @ResponseBody
    @RequestMapping(path = "/getActivityDetail", method = RequestMethod.POST)
    public Response<ActivityDetailResponse> getActivityDetail(@RequestBody ActivityDetailRequest activityDetailRequest) {
        var result = activityService.queryActivityById(activityDetailRequest.getId());
        if (result != null) {
            rankService.activityClick(activityDetailRequest.getId());
        }
        return Response.<ActivityDetailResponse>success().result(ActivityConverter.convertDetail(result));
    }

    @ResponseBody
    @RequestMapping(path = "/getActivities", method = RequestMethod.POST)
    public Response<Page<ActivityPageResponse>> queryActivity(@RequestBody ActivityPageRequest activityPageRequest) {
        var result = activityService.queryActivities(BeanCopiers.copy(activityPageRequest, QueryActivityAO.class));
        return Response.<Page<ActivityPageResponse>>success().result(BeanCopiers.copyPage(result, ActivityConverter::convertPage));
    }

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

    @ResponseBody
    @RequestMapping(path = "/getRankingList", method = RequestMethod.POST)
    public Response<List<ActivityPageResponse>> getRankingList() {
        var result = activityService.rankActivities();
        return Response.<List<ActivityPageResponse>>success().result(BeanCopiers.copyList(result, ActivityConverter::convertPage));
    }
}
