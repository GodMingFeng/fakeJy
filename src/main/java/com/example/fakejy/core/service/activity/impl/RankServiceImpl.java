package com.example.fakejy.core.service.activity.impl;

import com.example.fakejy.core.service.activity.RankService;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Tuple;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@Service
public class RankServiceImpl implements RankService {

    @Resource
    private JedisPool jedisPool;

    private static final String RANK_KEY = "RANK_KEY";

    private static final String RANK_ELE = "activity_rank_%d";

    private static final Integer TOP_NUM = 10;

    @Override
    public Boolean activityClick(Long id) {
        try (var jedis = jedisPool.getResource()) {
            jedis.zincrby(RANK_KEY, 1, String.format(RANK_ELE, id));
        }
        return true;
    }

    @Override
    public List<Long> getTopActivities() {
        Set<Tuple> result;
        try (var jedis = jedisPool.getResource()) {
            result = jedis.zrevrangeWithScores(RANK_KEY, 0, TOP_NUM - 1);
        }
        var top = Lists.<Long>newArrayList();
        for (var tuple : result) {
            top.add(Long.valueOf(tuple.getElement().split("_")[2]));
        }
        return top;
    }
}
