package com.xin.matchsystem.service;

import com.xin.matchsystem.model.domain.User;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author: TDA
 * @date: 2024/3/12 18:41
 * @description:
 */
@SpringBootTest
public class InsertUserTest {
    @Resource
    private UserService userService;

    //线程设置
    private ExecutorService executorService = new ThreadPoolExecutor(16, 1000,
            10000, TimeUnit.MINUTES, new ArrayBlockingQueue<>(10000));
    /**
     * 循环插入用户  耗时：2661ms
     * 批量插入用户   1000  耗时： 4751ms
     */
    @Test
    public void doInsertUser() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        final int INSERT_NUM = 1000;
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < INSERT_NUM; i++) {
            User user = new User();
            user.setUsername("jiaren");
            user.setUserAccount("jiaren");
            user.setAvatarUrl("https://cbu01.alicdn.com/img/ibank/O1CN01c90vj12FzbyF0qNNA_!!2214919038951-0-cib.310x310.jpg");
            user.setProfile("jiarenjiaren");
            user.setGender(0);
            user.setUserPassword("123123123");
            user.setPhone("123456789108");
            user.setEmail("ttttttddddd@qq.com");
            user.setUserStatus(0);
            user.setUserRole(0);
            user.setTags("[]");
            userList.add(user);
        }
        userService.saveBatch(userList,100);
        stopWatch.stop();
        System.out.println(stopWatch.getLastTaskTimeMillis());
    }

    /**
     * 并发批量插入用户   100000  耗时： 10909ms
     */
    @Test
    public void doConcurrencyInsertUser() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        final int INSERT_NUM = 100000;
        // 分十组
        int j = 0;
        //批量插入数据的大小
        int batchSize = 5000;
        List<CompletableFuture<Void>> futureList = new ArrayList<>();
        // i 要根据数据量和插入批量来计算需要循环的次数。（鱼皮这里直接取了个值，会有问题,我这里随便写的）
        for (int i = 0; i < INSERT_NUM/batchSize; i++) {
            List<User> userList = new ArrayList<>();
            while (true){
                j++;
                User user = new User();
                user.setUsername("假人");
                user.setUserAccount("jiaren");
                user.setAvatarUrl("https://cbu01.alicdn.com/img/ibank/O1CN01c90vj12FzbyF0qNNA_!!2214919038951-0-cib.310x310.jpg");
                user.setProfile("人人人人人人");
                user.setGender(0);
                user.setUserPassword("123123123");
                user.setPhone("123456789108");
                user.setEmail("ttttttddddd@qq.com");
                user.setUserStatus(0);
                user.setUserRole(0);
                user.setTags("[]");
                userList.add(user);
                if (j % batchSize == 0 ){
                    break;
                }
            }
            //异步执行
            CompletableFuture<Void> future = CompletableFuture.runAsync(() ->{
                System.out.println("ThreadName：" + Thread.currentThread().getName());
                userService.saveBatch(userList,batchSize);
            },executorService);
            futureList.add(future);
        }
        CompletableFuture.allOf(futureList.toArray(new CompletableFuture[]{})).join();

        stopWatch.stop();
        System.out.println( stopWatch.getLastTaskTimeMillis());

    }
}
