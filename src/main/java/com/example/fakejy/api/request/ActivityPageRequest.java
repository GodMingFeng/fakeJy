package com.example.fakejy.api.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class ActivityPageRequest implements Serializable {

    private static final long serialVersionUID = -2132821546076980728L;

    /**
     * 页码
     */
    private Integer page;

    /**
     * 页面大小
     */
    private Integer pageSize;
}
