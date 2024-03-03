package com.xin.usercenter;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestMapping;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@SpringBootTest
class UserCenterApplicationTests {

    @Test
    @RequestMapping("/hello")
    public String contextLoads(){
        return "hello";
    }

}
