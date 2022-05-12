package com.yogovi.core.service.impl;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ConfirmCallbackService implements RabbitTemplate.ConfirmCallback {

	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		if (!ack) {
            log.error("消息发送异常, {}", cause);
        } else {
            log.info("发送者已经收到确认, correlationData={}, ack={}, cause={}", correlationData.getId(), ack, cause);
        }
	}

}
