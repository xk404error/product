package com.xk.product.service.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MqP implements RabbitTemplate.ConfirmCallback,RabbitTemplate.ReturnCallback{
    Logger log=LoggerFactory.getLogger(MqP.class);
    /*@Autowired
    AmqpTemplate  amqpTemplate;*/
    @Autowired
    RabbitTemplate  rabbitTemplate;
    public void sendDirectMessage(String message){
        log.info("发送的消息是"+message);
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
        rabbitTemplate.convertAndSend(MqConf.queueHello,message);
    }

    public void sendTopicMessage(String message){
        rabbitTemplate.convertAndSend(MqConf.exchangeTopic,MqConf.queueTopicMessage,message);
    }
    public void sendTopicMessages(String message){
        rabbitTemplate.convertAndSend(MqConf.exchangeTopic,MqConf.queueTopicMessages,message);
    }

    public void sendFanoutMessages(String message){
        rabbitTemplate.convertAndSend(MqConf.exchangeFanout,"testFanout",message);
    }

    @Override
    //确认是否到达exchange
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (!ack) {
            log.info("HelloSender消息发送失败" + cause + correlationData.toString());
        } else {
            log.info("HelloSender 消息发送成功 ");
        }
    }

    @Override
    //确认是否到达queue
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
        log.info("sender return success");
    }
}
