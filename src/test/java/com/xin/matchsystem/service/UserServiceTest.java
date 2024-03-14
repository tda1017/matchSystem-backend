package com.xin.matchsystem.service;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

import com.xin.matchsystem.model.domain.User;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {
    @Resource
    private UserService userService;

    @Test
    public void testAddUser(){
        User user = new User();
        user.setUsername("xin");
        user.setUserAccount("xinxin");
        user.setAvatarUrl("https://images.zsxq.com/Fn-012BXZwcPeBWvJ2vKBWfK2teD?e=1706716799&token=kIxbL07-8jAj8w1n4s9zv64FuZZNEATmlU_Vm6zD:C3bx4f_cWYMmlxiXRPlTr8XtLxc=");
        user.setGender(0);
        user.setUserPassword("123123123");
        user.setPhone("123123123");
        user.setEmail("123");
        user.setUserStatus(0);
        user.setIsDelete(0);

        boolean res = userService.save(user);
        System.out.println(user.getId());
        assertTrue(res);
    }

    @Test
    void userRegister() throws NoSuchAlgorithmException {
        String userAccount = "xinxin";
        String userPassword = "123123123";
        String checkPassword = "123123123";
        long res = userService.userRegister(userAccount, userPassword, checkPassword);
        System.out.println(res);
//        Assertions.assertEquals(-1, res);
    }

    @Test
    void getNewPassword() throws NoSuchAlgorithmException {
        String userPassword = "123123123";
        StringBuilder newPassword = userService.getNewPassword(userPassword);
        System.out.println(newPassword.toString());
//        Assertions.assertEquals(-1, res);
    }

    @Test
    public void searchUserByTags(){
        List<String> tagNameList = Arrays.asList("java","python");
        List<User> userList = userService.searchUsersByTags(tagNameList);
        Assert.assertNotNull(userList);
    }
}