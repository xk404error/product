package com.xk.product.service.mq;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MqC {
    Logger log=LoggerFactory.getLogger(MqC.class);
    /*@RabbitListener(queuesToDeclare  = @Queue("hello"))
    public void getDirectMessage(String message){
        log.info("queueHello,接受信息是"+message);
    }*/

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

    @RabbitListener(queuesToDeclare  = @Queue("hello"))
    public void mqCAckMessage(String messageStr, Channel channel, Message message) throws IOException {
        try {
            log.info("接收到的消息是"+messageStr);
            log.info("接收到的Message.body是"+String.valueOf(message.getBody()));
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);//false只确认当前一个消息收到，true确认所有consumer获得的消息
        } catch (Exception e) {
            if(message.getMessageProperties().getRedelivered()) {
                log.info("消息已重复处理失败,拒绝再次接收...");
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
            }else{
                log.info("消息即将再次返回队列处理...");
                channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,true);//requeue为是否重新回到队列
            }
        }
    }
}
