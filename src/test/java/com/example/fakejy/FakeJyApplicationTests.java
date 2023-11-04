package com.example.fakejy;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.fakejy.core.service.activity.RankService;
import com.example.fakejy.mapper.repository.ActivityMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = FakeJyApplication.class)
public class FakeJyApplicationTests {

    @Resource
    private ActivityMapper activityMapper;

    @Resource
    private RankService rankService;

    @Test
    public void contextLoads() {
        System.out.println(JSONObject.toJSONString(activityMapper.selectAll()));
        System.out.println(333333);
    }

    @Test
    public void getRank() {
        rankService.activityClick(1L);
        var result = rankService.getTopActivities();
        System.out.println(JSON.toJSONString(result));
    }
}
