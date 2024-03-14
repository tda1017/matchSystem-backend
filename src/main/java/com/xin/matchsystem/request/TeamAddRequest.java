package com.xin.matchsystem.request;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: TDA
 * @date: 2024/3/14 18:10
 * @description:
 */
@Data
public class TeamAddRequest implements Serializable {

    private static final long serialVersionUID = -4205611022681019397L;
    /**
     * 队伍名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 最大人数
     */
    private Integer maxNum;

    /**
     * 过期时间
     */
    private Date expireTime;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 0 - 公开，1 - 私有，2 - 加密
     */
    private Integer status;

    /**
     * 密码
     */
    private String password;
}
