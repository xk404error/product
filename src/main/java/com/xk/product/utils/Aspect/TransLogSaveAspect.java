package com.xk.product.utils.Aspect;

import com.xk.product.dao.mapper.TransLogMapper;
import com.xk.product.dao.model.TransLogWithBLOBs;
import com.xk.product.utils.cusexception.InfoEamilException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @description: 日志保存切点
 * @author: xk
 * @create: 2019-04-24 15:15
 **/
@Aspect
@Component
public class TransLogSaveAspect {
    Logger logger=LoggerFactory.getLogger(ServiceAnnotationAspect.class);
    @Resource
    TransLogMapper transLogMapper;
    @Pointcut("@annotation(com.xk.product.utils.annos.TransLogSave)")
    private void doworkPointCut(){};


    @Before("doworkPointCut()")
    public void doworkBefore(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        StringBuffer reqBf=new StringBuffer();
        for (Object arg : args) {
            reqBf.append(arg);
        }
        String threadName = Thread.currentThread().getName();
        Signature signature = joinPoint.getSignature();
        logger.info("threadName="+threadName+",req="+reqBf.toString());
        TransLogWithBLOBs transLogWithBLOBs = new TransLogWithBLOBs();
        transLogWithBLOBs.setReq(reqBf.toString());
        //transLogWithBLOBs.setRes(res.toString());
        transLogWithBLOBs.setThreadName(threadName);
        transLogWithBLOBs.setTaskName("");
        transLogMapper.insert(transLogWithBLOBs);
    }
    @AfterThrowing(pointcut = "doworkPointCut()",throwing = "e")
    public void doworkAfter(JoinPoint joinPoint,InfoEamilException e){
        String threadName = Thread.currentThread().getName();
        logger.info("threadName="+threadName+",Exception="+e.getMessage());
    }
    @AfterReturning(pointcut="doworkPointCut()",returning = "res")
    public void doworkAfterRet(JoinPoint joinPoint,Object res){
        Object[] args = joinPoint.getArgs();
        StringBuffer reqBf=new StringBuffer();
        for (Object arg : args) {
            reqBf.append(arg);
        }
        String threadName = Thread.currentThread().getName();
        TransLogWithBLOBs transLogWithBLOBs = new TransLogWithBLOBs();
        transLogWithBLOBs.setReq(reqBf.toString());
        transLogWithBLOBs.setRes(res.toString());
        transLogWithBLOBs.setThreadName(threadName);
        transLogWithBLOBs.setTaskName("");
        transLogMapper.insert(transLogWithBLOBs);
    }
}
