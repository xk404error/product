package com.xk.product.action.aoptest;

import com.xk.product.service.trans.TransService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;
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
    Logger logger=LoggerFactory.getLogger(TestAopContorller.class);
    Integer threadNums=200;
    @Resource
    TransService transService;
    @RequestMapping("testaop")
    public void test(){
        //23:25:06.710
        //23:25:07.626
        long start = System.currentTimeMillis();
        try {
            System.out.println("testAop");
            LinkedBlockingDeque<Runnable> blockingDeque = new LinkedBlockingDeque<>(500);
            CountDownLatch countDownLatch = new CountDownLatch(threadNums);
            ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 20,60L,TimeUnit.SECONDS,blockingDeque);
            for (int i = 0; i <300 ; i++) {
                threadPoolExecutor.execute(new AopTestRunnable(String.valueOf(i),transService,countDownLatch));
            }
            //countDownLatch.await();
            countDownLatch.await(10L,TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();

        System.out.println("线程池用时"+(start-end));
        /*for (int i = 0; i <100 ; i++) {
            transService.doWork(String.valueOf(i));
        }*/

    }
    @RequestMapping("testaopwithoutthread")
    public void testwithoutthread(){
        //用时1407
        long start = System.currentTimeMillis();
        System.out.println("testAopwithoutthread");
        for (int i = 0; i <100 ; i++) {
            transService.doWork(String.valueOf(i));
        }
        long end = System.currentTimeMillis();
        logger.info("用时"+(start-end));
    }

}
