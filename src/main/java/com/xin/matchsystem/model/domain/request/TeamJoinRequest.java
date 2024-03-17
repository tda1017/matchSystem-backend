package com.xin.matchsystem.model.domain.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: TDA
 * @date: 2024/3/17 17:00
 * @description:
 */
@Data
public class TeamJoinRequest implements Serializable {

    private static final long serialVersionUID = -6873749928993420190L;

    /**
     * id
     */
    private long teamId;

    /**
     * 密码
     */
    private String password;
}
