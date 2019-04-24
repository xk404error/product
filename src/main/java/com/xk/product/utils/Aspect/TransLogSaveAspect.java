package com.xk.product.utils.Aspect;

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

import javax.annotation.Resource;

/**
 * @description: 日志保存切点
 * @author: xk
 * @create: 2019-04-24 15:15
 **/
@Aspect
public class TransLogSaveAspect {
    Logger logger=LoggerFactory.getLogger(ServiceAnnotationAspect.class);


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
    }
    @AfterThrowing(pointcut = "doworkPointCut()",throwing = "e")
    public void doworkAfter(JoinPoint joinPoint,InfoEamilException e){
        String threadName = Thread.currentThread().getName();
        logger.info("threadName="+threadName+",Exception="+e.getMessage());
    }
    @AfterReturning(pointcut="doworkPointCut()",returning = "res")
    public void doworkAfterRet(JoinPoint joinPoint,Object res){
        String threadName = Thread.currentThread().getName();
        logger.info("threadName="+threadName+",req="+res.toString());
    }
}
