package com.example.fakejy.mapper.repository;

import com.example.fakejy.mapper.domain.Activity;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface ActivityMapper extends Mapper<Activity>, MySqlMapper<Activity> {
}
