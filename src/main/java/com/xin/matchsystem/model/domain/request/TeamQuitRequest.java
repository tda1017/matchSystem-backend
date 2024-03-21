package com.xin.matchsystem.model.domain.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: TDA
 * @date: 2024/3/18 18:38
 * @description:
 */
@Data
public class TeamQuitRequest implements Serializable {

    private static final long serialVersionUID = -289718767309094870L;

    /**
     * id
     */
    private long teamId;

}
