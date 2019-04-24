package com.xk.product.service.trans;

import com.xk.product.utils.annos.TransLogSave;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @description: 日志保存测试
 * @author: xk
 * @create: 2019-04-24 15:57
 **/
@Component
public class TransServiceImpl implements TransService{
    Logger logger=LoggerFactory.getLogger(TransServiceImpl.class);
    @Override
    @TransLogSave(taskType = 2,interfaceName = "测试")
    public String doWork(String req) {
        logger.info(req);
        Integer reqint=Integer.valueOf(req);
        reqint++;
        logger.info(reqint.toString());
        return String.valueOf(reqint);
    }
}
