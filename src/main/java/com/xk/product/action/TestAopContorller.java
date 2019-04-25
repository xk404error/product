package com.xk.product.action;

import com.xk.product.service.trans.TransService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @description: aop测试连接
 * @author: xk
 * @create: 2019-04-24 18:18
 **/
@Controller
@RequestMapping("/test")
public class TestAopContorller {
    Integer threadNums=200;
    @Resource
    TransService transService;
    @RequestMapping("testaop")
    public void test(){
        try {
            System.out.println("testAop");
            LinkedBlockingDeque<Runnable> blockingDeque = new LinkedBlockingDeque<>(2);
            CountDownLatch countDownLatch = new CountDownLatch(threadNums);
            ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 20,60L,TimeUnit.SECONDS,blockingDeque);
            for (int i = 0; i <100 ; i++) {
                threadPoolExecutor.execute(new AopTestRunnable(String.valueOf(i),transService,countDownLatch));
            }
            countDownLatch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*for (int i = 0; i <100 ; i++) {
            transService.doWork(String.valueOf(i));
        }*/
    }

}
