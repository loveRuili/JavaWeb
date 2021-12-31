package com.czxy.listener;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class SpringRabbitListener {
    //basic
    @RabbitListener(queues = "itcast.code")
    public void listenSimpleQueueMessage1(String msg) throws InterruptedException {
        System.out.println("消费者接收到消息：【" + msg + "】");
        System.out.println("消费者发送消息：【" + msg + "】");
    }
}