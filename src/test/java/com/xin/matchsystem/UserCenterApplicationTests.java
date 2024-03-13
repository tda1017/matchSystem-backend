package com.xin.matchsystem;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootTest
class UserCenterApplicationTests {

    @Test
    @RequestMapping("/hello")
    public String contextLoads(){
        return "hello";
    }

}
