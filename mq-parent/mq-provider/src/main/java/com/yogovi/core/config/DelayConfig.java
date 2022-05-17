package com.yogovi.core.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DelayConfig {
	
	public final static String UNPAY_ORDER_QUEUE_NAME ="unpay_order";
	public final static String UNPAY_ORDER_EXCHANGE_NAME ="unpay_order_exchange";
	public final static String UNPAY_ORDER_ROUTING_KEY ="unpay_order.*";
	
	public final static String UNPAY_ORDER_DEAD_QUEUE_NAME ="unpay_order_dead";
	public final static String UNPAY_ORDER_DEAD_EXCHANGE_NAME ="unpay_order_dead_exchange";
	public final static String UNPAY_ORDER_DEAD_ROUTING_KEY ="unpay-order-dead-routing-key";
	
	
	@Bean
    public Queue unpayOrderQueue() {
        return QueueBuilder
                .durable(UNPAY_ORDER_QUEUE_NAME)
                //声明该队列的死信消息发送到的 交换机 （队列添加了这个参数之后会自动与该交换机绑定，并设置路由键，不需要开发者手动设置)
                .deadLetterExchange(UNPAY_ORDER_DEAD_EXCHANGE_NAME)
                //声明该队列死信消息在交换机的 路由键
                .deadLetterRoutingKey(UNPAY_ORDER_DEAD_ROUTING_KEY)
                //设置超时时间10S
                .ttl(5000)
                .build();
    }
	
	@Bean
    public Exchange unpayOrderExchange() {
        return ExchangeBuilder
                .topicExchange(UNPAY_ORDER_EXCHANGE_NAME)
                .durable(true)
                .build();
    }
	
	@Bean
    public Binding unpayOrderBinding(Queue unpayOrderQueue, Exchange unpayOrderExchange) {
        return BindingBuilder
                .bind(unpayOrderQueue)
                .to(unpayOrderExchange)
                .with("unpay_order.*")
                .noargs();
    }
	
	
	@Bean
    public Queue unpayOrderDeadQueue() {
        return QueueBuilder
                .durable(UNPAY_ORDER_DEAD_QUEUE_NAME)
                .build();
    }
	
	@Bean
    public Exchange unpayOrderDeadExchange() {
        return ExchangeBuilder
                .topicExchange(UNPAY_ORDER_DEAD_EXCHANGE_NAME)
                .durable(true)
                .build();
    }
	
	@Bean
    public Binding unpayOrderDeadBinding(Queue unpayOrderDeadQueue, Exchange unpayOrderDeadExchange) {
        return BindingBuilder
                .bind(unpayOrderDeadQueue)
                .to(unpayOrderDeadExchange)
                .with(UNPAY_ORDER_DEAD_ROUTING_KEY)
                .noargs();
    }


}
