package com.xin.matchsystem.service;

import com.xin.matchsystem.model.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;

/**
 * @author: TDA
 * @date: 2024/3/12 19:17
 * @description:
 */
@SpringBootTest
public class RedisTest {
    @Resource
    private RedisTemplate redisTemplate;

    @Test
    void test() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        // 增
        valueOperations.set("tdString", "td");
        valueOperations.set("tdInt", 1);
        valueOperations.set("tdDouble", 2.0);
        User user = new User();
        user.setId(1L);
        user.setUsername("td");
        valueOperations.set("tdUser", user);

        // 查
        Object td = valueOperations.get("tdString");
        Assertions.assertTrue("td".equals((String) td));
        td = valueOperations.get("tdInt");
        Assertions.assertTrue(1 == (Integer) td);
        td = valueOperations.get("tdDouble");
        Assertions.assertTrue(2.0 == (Double) td);
        System.out.println(valueOperations.get("tdUser"));
        valueOperations.set("tdString", "td");

        //删
//        redisTemplate.delete("shayuString");
    }
}
