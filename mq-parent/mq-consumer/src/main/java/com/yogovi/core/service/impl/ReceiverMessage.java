package com.yogovi.core.service.impl;

import java.io.IOException;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RabbitListener(queues = "TEST_TOPIC_QUEUE_A")
public class ReceiverMessage {
    
    @RabbitHandler
    public void processHandler(String msg, Channel channel, Message message) throws IOException {
        try {
            log.info("收到消息：{}", msg);
            //TODO 具体业务                       
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }  catch (Exception e) {           
            if (message.getMessageProperties().getRedelivered()) {               
                log.error("消息已重复处理失败,拒绝再次接收...");
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), false); // 拒绝消息
            } else {            
                log.error("消息即将再次返回队列处理...");
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true); 
            }
        }
    }
}
