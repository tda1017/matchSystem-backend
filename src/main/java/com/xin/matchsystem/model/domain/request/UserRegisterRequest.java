package com.xin.matchsystem.model.domain.request;

import lombok.Data;


import java.io.Serializable;

/**
 * @author: TDawn
 * @date: 2023/12/20 16:13
 * @description:
 */
@Data
public class UserRegisterRequest implements Serializable {

    private static final long serialVersionUID = 8108278441375482681L;

    public String userAccount;
    public String userPassword;
    public String checkPassword;
}
