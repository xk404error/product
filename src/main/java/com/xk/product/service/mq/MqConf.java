package com.xk.product.service.mq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqConf {
    public final static String queueHello="hello";
    public final static String queueTopicMessage="topic.Message";
    public final static String queueTopicMessages="topic.Messages";
    public final static String queuefanoutA="fanout.A";
    public final static String queuefanoutB="fanout.B";
    public final static String queuefanoutC="fanout.C";
    public final static String exchangeTopic="topicExchange";
    public final static String exchangeFanout="fanoutExchange";
    @Bean
    public Queue queueHello(){
        return  new Queue(queueHello);
    }
    //————————验证topic队列开始————————
    @Bean
    public Queue queueMessage(){
        return  new Queue(queueTopicMessage);
    }
    @Bean
    public Queue queueMessages(){
        return  new Queue(queueTopicMessages);
    }
    //————————验证topic队列结束————————

    //===============以下是验证Fanout Exchange的队列==========
    @Bean
    public Queue AMessage() {
        return new Queue(queuefanoutA);
    }

    @Bean
    public Queue BMessage() {
        return new Queue(queuefanoutB);
    }

    @Bean
    public Queue CMessage() {
        return new Queue(queuefanoutC);
    }
    //===============以上是验证Fanout Exchange的队列==========
    @Bean
    TopicExchange topicExchange(){
       return new TopicExchange(exchangeTopic);
    }
    @Bean
    FanoutExchange fanoutExchange(){
        return new FanoutExchange(exchangeFanout);
    }

    @Bean
    Binding bindingExchangeMessage(Queue queueMessage, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueMessage).to(topicExchange).with("topic.message");
    }

    @Bean
    Binding bindingExchangeMessages(Queue queueHello, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueHello).to(topicExchange).with("#.messages");
    }

    @Bean
    Binding bindingExchangeA(Queue AMessage,FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(AMessage).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeB(Queue BMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(BMessage).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeC(Queue CMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(CMessage).to(fanoutExchange);
    }

}
