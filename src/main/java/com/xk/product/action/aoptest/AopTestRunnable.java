package com.xk.product.action.aoptest;

import com.xk.product.service.trans.TransService;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;

public class AopTestRunnable implements Runnable{

    private String req;
    private TransService transService;
    private CountDownLatch countDownLatch;

    public AopTestRunnable(String req, TransService transService, CountDownLatch countDownLatch) {
        this.req = req;
        this.transService = transService;
        this.countDownLatch = countDownLatch;
    }

    public String getReq() {
        return req;
    }

    public void setReq(String req) {
        this.req = req;
    }

    public TransService getTransService() {
        return transService;
    }

    public void setTransService(TransService transService) {
        this.transService = transService;
    }

    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }

    public void setCountDownLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        transService.doWork(req);
        try {
            countDownLatch.countDown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
