package com.xk.product.service.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MqC {
    Logger log=LoggerFactory.getLogger(MqC.class);
    @RabbitListener(queuesToDeclare  = @Queue("hello"))
    public void getDirectMessage(String message){
        log.info("queueHello,接受信息是"+message);
    }

    @RabbitListener(queuesToDeclare  = @Queue("topic.Message") )
    public void getTopicMessage(String message){
        log.info("topic.Message,接受信息是"+message);
    }
    @RabbitListener(queuesToDeclare  = @Queue("topic.Messages") )
    public void getTopicMessages(String message){
        log.info("topic.Messages,接受信息是"+message);
    }

    @RabbitListener(queuesToDeclare  = @Queue("fanout.A"))
    public void getfanoutMessagesA(String message){
        log.info("fanout.A,接受信息是"+message);
    }

    @RabbitListener(queuesToDeclare  = @Queue("fanout.B"))
    public void getfanoutMessagesB(String message){
        log.info("fanout.B,接受信息是"+message);
    }

    @RabbitListener(queuesToDeclare = @Queue("fanout.C"))
    public void getfanoutMessagesC(String message){
        log.info("fanout.C,接受信息是"+message);
    }
}
