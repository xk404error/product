package com.xk.product.service.mq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MqP {
    @Autowired
    AmqpTemplate  amqpTemplate;

    public void sendDirectMessage(String message){
        amqpTemplate.convertAndSend(MqConf.queueHello,message);
    }

    public void sendTopicMessage(String message){
        amqpTemplate.convertAndSend(MqConf.exchangeTopic,MqConf.queueTopicMessage,message);
    }
    public void sendTopicMessages(String message){
        amqpTemplate.convertAndSend(MqConf.exchangeTopic,MqConf.queueTopicMessages,message);
    }

    public void sendFanoutMessages(String message){
        amqpTemplate.convertAndSend(MqConf.exchangeFanout,"testFanout",message);
    }


}
