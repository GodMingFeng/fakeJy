package com.example.fakejy.core.service.activity.impl;

import com.example.fakejy.common.Page;
import com.example.fakejy.common.utils.BeanCopiers;
import com.example.fakejy.common.utils.PageUtils;
import com.example.fakejy.core.service.activity.ActivityService;
import com.example.fakejy.core.service.activity.RankService;
import com.example.fakejy.core.service.activity.ao.QueryActivityAO;
import com.example.fakejy.core.service.activity.bo.ActivityBO;
import com.example.fakejy.mapper.domain.Activity;
import com.example.fakejy.mapper.repository.ActivityMapper;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Resource
    private ActivityMapper activityMapper;

    @Resource
    private RankService rankService;

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

    @Override
    public List<ActivityBO> rankActivities() {
        var idList = rankService.getTopActivities();
        if (CollectionUtils.isEmpty(idList)) {
            return Lists.newArrayList();
        }
        var example = new Example(Activity.class);
        example.createCriteria()
                .andIn("id", idList);
        var activityList = activityMapper.selectByExample(example);
        return BeanCopiers.copyList(activityList, ActivityBO.class);
    }
}
