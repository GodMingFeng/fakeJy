package com.example.fakejy.core.service.activity.impl;

import com.example.fakejy.common.Page;
import com.example.fakejy.common.enums.FavoritesType;
import com.example.fakejy.common.utils.BeanCopiers;
import com.example.fakejy.common.utils.PageUtils;
import com.example.fakejy.core.service.activity.ActivityService;
import com.example.fakejy.core.service.activity.RankService;
import com.example.fakejy.core.service.activity.ao.QueryActivityAO;
import com.example.fakejy.core.service.activity.bo.ActivityBO;
import com.example.fakejy.core.service.favorites.FavoritesService;
import com.example.fakejy.mapper.domain.Activity;
import com.example.fakejy.mapper.repository.ActivityMapper;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Resource
    private ActivityMapper activityMapper;

    @Resource
    private RankService rankService;

    @Resource
    private FavoritesService favoritesService;

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
        return getActivitiesListByIdListOrder(idList);
    }

    @Override
    public Page<ActivityBO> favoritesActivities(String openId, Integer page, Integer pageSize) {
        var idPage = favoritesService.getFavoritesItems(openId, FavoritesType.ACTIVITIES, page, pageSize);
        var activitiesList = getActivitiesListByIdListOrder(idPage.getList());
        return new Page<>(idPage.getTotal(), activitiesList);
    }

    @Override
    public List<ActivityBO> getActivitiesListByIdListOrder(List<Long> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            return Lists.newArrayList();
        }
        var example = new Example(Activity.class);
        example.createCriteria()
                .andIn("id", idList);
        var activityList = activityMapper.selectByExample(example);
        var map = activityList.stream().collect(Collectors.toMap(Activity::getId, Function.identity()));
        var result = idList.stream().map(map::get).filter(Objects::nonNull).collect(Collectors.toList());
        return BeanCopiers.copyList(result, ActivityBO.class);
    }
}
