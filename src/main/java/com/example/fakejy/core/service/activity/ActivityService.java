package com.example.fakejy.core.service.activity;

import com.example.fakejy.common.Page;
import com.example.fakejy.core.service.activity.ao.QueryActivityAO;
import com.example.fakejy.core.service.activity.bo.ActivityBO;

public interface ActivityService {

    /**
     * 通过id查询活动
     *
     * @param id id
     * @return 活动
     */
    ActivityBO queryActivityById(Long id);


    /**
     * 查询活动列表
     *
     * @return 活动列表
     */
    Page<ActivityBO> queryActivities(QueryActivityAO queryActivityAO);
}
