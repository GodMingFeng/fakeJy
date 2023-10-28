package com.example.fakejy.api.controller;

import com.example.fakejy.api.request.ActivityDetailRequest;
import com.example.fakejy.api.response.ActivityDetailResponse;
import com.example.fakejy.common.Response;
import com.example.fakejy.common.utils.BeanCopiers;
import com.example.fakejy.core.service.activity.ActivityService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
}
