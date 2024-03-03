package com.xin.usercenter.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.xin.usercenter.common.BaseResponse;
import com.xin.usercenter.common.ErrorCode;
import com.xin.usercenter.common.ResultUtils;
import com.xin.usercenter.exception.BusinessException;
import com.xin.usercenter.model.domain.User;
import com.xin.usercenter.model.domain.request.UserLoginRequest;
import com.xin.usercenter.model.domain.request.UserRegisterRequest;
import com.xin.usercenter.service.UserService;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.xin.usercenter.constant.UserConstant.USER_LOGIN_STATE;

/**
 * @author: TDawn
 * @date: 2023/12/20 16:08
 * @description:
 */
@Api("user")
@RestController
@RequestMapping("user")
@Slf4j
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class UserController {

    @Resource
    private UserService userService;


    @PostMapping("login")
    public BaseResponse<User> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) throws NoSuchAlgorithmException {
        if (userLoginRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        String userAccount = userLoginRequest.getUserAccount();
        String password = userLoginRequest.getUserPassword();

        if (StringUtils.isAnyBlank(userAccount, password)) {
            System.out.println("return null");
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        return ResultUtils.success(userService.userLogin(userAccount, password, request));
    }

    @PostMapping("register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) throws NoSuchAlgorithmException {
        if (userRegisterRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        System.out.println("---------------controller------------");
        String userAccount = userRegisterRequest.getUserAccount();
        String password = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        System.out.println("---------------controller------------");

        if (StringUtils.isAnyBlank(userAccount, password, checkPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        long userId = userService.userRegister(userAccount, password, checkPassword);
        System.out.println("userId:" + userId);
        return ResultUtils.success(userId);
    }

    @GetMapping("current")
    public BaseResponse<User> getCurrentUser(HttpServletRequest request) {
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User currentUser = (User) userObj;
        //先判空
        if (currentUser == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN);
        }
        //todo 检验用户是否合法
        Long userId = currentUser.getId();
        User user = userService.getById(userId);
        return ResultUtils.success(userService.getSafetyUser(user));
    }

    @GetMapping("search")
    public BaseResponse<List<User>> searchUser(String username, HttpServletRequest request) {
        //管理员可查询
        if (!userService.isAdmin(request)) {
            return ResultUtils.success(new ArrayList<>());
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isAnyBlank(username)) {
            queryWrapper.like("username", username);
        }
        List<User> userList = userService.list(queryWrapper);
        System.out.println("userList = " + userList.toString());

        List<User> collect = userList.stream().map(user -> userService.getSafetyUser(user)).collect(Collectors.toList());
        return ResultUtils.success(collect);
    }

    @PostMapping("delete")
    public BaseResponse<Boolean> deleteUser(@RequestBody long id, HttpServletRequest request) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if (!userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH);
        }
        boolean b =  userService.removeById(id);
        return ResultUtils.success(b);
    }

    /**
     * 用户注销
     */
    @PostMapping("logout")
    public BaseResponse<Integer> userLogout(HttpServletRequest request){
        if(request == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        System.out.println("-----------------------");
        log.info("user logout");
        System.out.println("-----------------------");
        return ResultUtils.success(userService.userLogout(request));
    }


    //根据标签查询用户
    @GetMapping("/search/tags")
    public BaseResponse<List<User>> searchUsersByTags(@RequestParam(required = false) List<String> tagNameList){
        System.out.println("tagNameList = " + tagNameList);
        if (CollectionUtils.isEmpty(tagNameList)){
            return ResultUtils.error(ErrorCode.PARAMS_ERROR);
        }
        List<User> userList = userService.searchUsersByTags(tagNameList);
        return ResultUtils.success(userList);
    }

    /**
     * 更新用户信息
     * @param user
     * @param request
     * @return
     */
    @PostMapping("update")
    public BaseResponse<Integer> updateUser(@RequestBody User user, HttpServletRequest request) {
        // 1. 校验参数是否为空
        if (user == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User loginUser = userService.getCurrentUser(request);
        int result = userService.updateUser(user, loginUser);
        return ResultUtils.success(result);
    }



}
