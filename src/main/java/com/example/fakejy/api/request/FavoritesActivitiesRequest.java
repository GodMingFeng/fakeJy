package com.example.fakejy.api.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class FavoritesActivitiesRequest implements Serializable {

    private static final long serialVersionUID = -2132821546076980728L;

    /**
     * 活动id
     */
    private Long id;

    /**
     * 状态
     */
    private Integer status;
}
