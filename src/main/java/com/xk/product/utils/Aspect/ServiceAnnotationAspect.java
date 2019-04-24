package com.xk.product.utils.Aspect;

import com.xk.product.utils.cusexception.InfoEamilException;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @description: 服务层注解切面
 * @author: xk
 * @create: 2019-04-23 11:28
 **/
@Aspect
public class ServiceAnnotationAspect {
    Logger logger=LoggerFactory.getLogger(ServiceAnnotationAspect.class);
    @Pointcut("@annotation(com.xk.product.utils.annos.ServiceInfoEamilNotice)")
    private void infoEamilPointcut() {
    }
    ;

    @AfterThrowing(pointcut = "infoEamilPointcut()",throwing = "e")
    public void infoEamilThrow(InfoEamilException e) {
        logger.info(e.getMessage());

    }
}
