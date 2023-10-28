package com.example.fakejy.common.utils;

import org.springframework.beans.BeanUtils;

public class BeanCopiers {

    public static <T> T copy(Object source, T target) {
        BeanUtils.copyProperties(source, target);
        return target;
    }
}
