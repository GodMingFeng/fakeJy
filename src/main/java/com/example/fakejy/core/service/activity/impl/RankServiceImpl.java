package com.example.fakejy.core.service.activity.impl;

import com.example.fakejy.core.service.activity.RankService;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RankServiceImpl implements RankService {

    @Resource
    private JedisPool jedisPool;

    private static final String RANK_KEY = "RANK_KEY";

    private static final String RANK_ELE = "activity_rank_%d";

    private static final Integer TOP_NUM = 10;

    @Override
    public Boolean activityClick(Long id) {
        jedisPool.getResource().zincrby(RANK_KEY, 1, String.format(RANK_ELE, id));
        return true;
    }

    @Override
    public List<Long> getTopActivities() {
        var result = jedisPool.getResource().zrevrangeWithScores(RANK_KEY, 0, TOP_NUM - 1);
        return result.stream().map(tuple -> Long.valueOf(tuple.getElement().split("_")[2])).collect(Collectors.toList());
    }
}
