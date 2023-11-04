package com.example.fakejy.core.service.activity.ao;

import lombok.Data;

import java.io.Serializable;

@Data
public class QueryActivityAO implements Serializable {

    private static final long serialVersionUID = 615603654098277485L;

    /**
     * 页数
     */
    private Integer page;

    /**
     * 页面大小
     */
    private Integer pageSize;
}
