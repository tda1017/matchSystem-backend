package com.xin.usercenter.model.domain.request;

import lombok.Data;

/**
 * @author: TDawn
 * @date: 2023/12/20 16:43
 * @description:
 */
@Data
public class UserLoginRequest {
    public String userAccount;
    public String userPassword;
    public String checkPassword;
}
