package com.xin.matchsystem.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: TDA
 * @date: 2024/3/14 16:56
 * @description:
 */
@Data
public class PageRequest implements Serializable {

    private static final long serialVersionUID = 379567597462746699L;

    /**
     * 页面大小
     */
    protected int pageSize = 10;

    /**
     * 当前页面
     */
    protected int pageNum = 1;
}
