package com.example.fakejy.core.service.favorites;

import com.example.fakejy.common.Page;
import com.example.fakejy.common.enums.FavoritesStatus;
import com.example.fakejy.common.enums.FavoritesType;

public interface FavoritesService {

    /**
     * 编辑收藏
     *
     * @param type            收藏类型
     * @param openId          用户id
     * @param itemId          收藏id
     * @param favoritesStatus 操作状态
     */
    void editFavorites(FavoritesType type, String openId, String itemId, FavoritesStatus favoritesStatus);

    /**
     * 获取收藏
     *
     * @param openId
     * @return
     */
    Page<Long> getFavoritesItems(String openId, FavoritesType type, Integer page, Integer pageSize);
}
