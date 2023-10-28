package com.example.fakejy.core.service.activity;

import com.example.fakejy.core.service.activity.bo.ActivityBO;

public interface ActivityService {

    /**
     * 通过id查询活动
     *
     * @param id id
     * @return 活动
     */
    ActivityBO queryActivityById(Long id);
}
