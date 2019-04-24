package com.xk.product.action;

import com.xk.product.service.trans.TransService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;

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
            CountDownLatch countDownLatch = new CountDownLatch(threadNums);

            for (int i = 0; i <100 ; i++) {
                new Thread(new AopTestRunnable(String.valueOf(i),transService,countDownLatch)).start();
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
