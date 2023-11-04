package com.example.fakejy.core.service.activity.impl;

import com.example.fakejy.common.Page;
import com.example.fakejy.common.utils.BeanCopiers;
import com.example.fakejy.common.utils.PageUtils;
import com.example.fakejy.core.service.activity.ActivityService;
import com.example.fakejy.core.service.activity.ao.QueryActivityAO;
import com.example.fakejy.core.service.activity.bo.ActivityBO;
import com.example.fakejy.mapper.repository.ActivityMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Resource
    private ActivityMapper activityMapper;

    @Override
    public ActivityBO queryActivityById(Long id) {
        var activity = activityMapper.selectByPrimaryKey(id);
        return BeanCopiers.copy(activity, new ActivityBO());
    }

    @Override
    public Page<ActivityBO> queryActivities(QueryActivityAO queryActivityAO) {
        var result = PageUtils.page(activityMapper::selectAll,
                queryActivityAO.getPage(), queryActivityAO.getPageSize());
        return BeanCopiers.copyPage(result, ActivityBO.class);
    }
}
