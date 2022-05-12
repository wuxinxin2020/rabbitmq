package com.yogovi.core.service.impl;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ReturnCallbackService implements RabbitTemplate.ReturnCallback{

	public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
		log.info("returnedMessage ===> message={} ,replyCode={} ,replyText={} ,exchange={} ,routingKey={}", new String(message.getBody()), 
				replyCode, replyText, exchange, routingKey);
		
	}

}
