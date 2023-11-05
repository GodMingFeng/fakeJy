package com.example.fakejy.mapper.repository;

import com.example.fakejy.mapper.domain.Favorites;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface FavoritesMapper extends Mapper<Favorites>, MySqlMapper<Favorites> {
}
