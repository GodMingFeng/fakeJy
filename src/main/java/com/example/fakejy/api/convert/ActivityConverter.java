package com.example.fakejy.api.convert;

import com.alibaba.fastjson.JSON;
import com.example.fakejy.api.response.ActivityDetailResponse;
import com.example.fakejy.api.response.ActivityPageResponse;
import com.example.fakejy.common.utils.BeanCopiers;
import com.example.fakejy.core.service.activity.bo.ActivityBO;

public class ActivityConverter {

    public static ActivityDetailResponse convertDetail(ActivityBO activityBO, Integer clickCount) {
        var result = BeanCopiers.copy(activityBO, ActivityDetailResponse.class);
        result.setStoreInfoObj(JSON.parseObject(activityBO.getStoreInfo()));
        result.setClickCount(clickCount);
        return result;
    }

    public static ActivityPageResponse convertPage(ActivityBO activityBO) {
        var result = BeanCopiers.copy(activityBO, ActivityPageResponse.class);
        result.setStoreInfoObj(JSON.parseObject(activityBO.getStoreInfo()));
        return result;
    }
}
