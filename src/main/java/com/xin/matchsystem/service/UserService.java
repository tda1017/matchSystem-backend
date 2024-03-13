package com.xin.matchsystem.service;

import com.xin.matchsystem.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
* @author TDawn
* @description 针对表【user】的数据库操作Service
* @createDate 2023-12-19 17:51:51
*/
public interface UserService extends IService<User> {


    /**
     *
     * @param userAccount 账户
     * @param password 密码
     * @return
     */
    User userLogin(String userAccount, String password, HttpServletRequest request)throws NoSuchAlgorithmException;

    Integer userLogout(HttpServletRequest request);


    /**
     *
     * @param userAccount 账户
     * @param password    密码
     * @param checkPassword 校验密码
     * @return  userId 新用户id
     */
    long userRegister(String userAccount, String password, String checkPassword) throws NoSuchAlgorithmException;


    //3.用户脱敏
    User getSafetyUser(User user);



    //密码加密
    StringBuilder getNewPassword(String password) throws NoSuchAlgorithmException;

    List<User> searchUsersByTags(List<String> tagNameList);

    List<User> searchUsersByTagsBySQL(List<String> tagNameList);

    /**
     * 是否为管理员
     * @param
     * @return
     */
    boolean isAdmin(HttpServletRequest request);

    boolean isAdmin(User loginUser);

    /**
     * 获取当前用户
     * @param request
     * @return
     */
    User getCurrentUser(HttpServletRequest request);

    /**
     * 更新用户信息
     * @param user
     * @param loginUser
     * @return
     */
    int updateUser(User user, User loginUser);
}
