package com.xk.product.action.aoptest;

import com.xk.product.service.mq.MqC;
import com.xk.product.service.mq.MqP;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @description: mq的action
 * @author: xk
 * @create: 2019-04-29 14:59
 **/
@Controller
@RequestMapping("testMq")
public class MqController {
    @Resource
    MqP mqP;
    @Resource
    MqC mqC;

    @RequestMapping("testDriect")
    public void testMqDriect(){
        mqP.sendDirectMessage("hello,world!");
    }
    @RequestMapping("testFanout")
    public void testMqFanout(){
        mqP.sendFanoutMessages("FanoutMessge");
    }
    @RequestMapping("testTopic")
    public void testMqTopic(){
        //mqP.sendTopicMessage("TopicMessage");
        mqP.sendTopicMessages("TopicMessages");
    }
}
