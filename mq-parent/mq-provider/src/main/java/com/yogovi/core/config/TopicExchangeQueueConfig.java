package com.yogovi.core.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicExchangeQueueConfig {

	public final static String TEST_TOPIC_QUEUE_A ="TEST_TOPIC_QUEUE_A";
    public final static String TEST_TOPIC_QUEUE_B ="TEST_TOPIC_QUEUE_B";
    public final static String TEST_TOPIC_QUEUE_C ="TEST_TOPIC_QUEUE_C";
    public final static String TEST_TOPIC_EXCHANGE="TEST_TOPIC_EXCHANGE";

    @Bean
    public Queue topicQueueA(){
        return new Queue(TEST_TOPIC_QUEUE_A);
    }
    @Bean
    public Queue topicQueueB(){
        return new Queue(TEST_TOPIC_QUEUE_B);
    }
    @Bean
    public Queue topicQueueC(){
        return new Queue(TEST_TOPIC_QUEUE_C);
    }
    @Bean("topicExchange")
    public TopicExchange topicExchange(){
        return new TopicExchange(TEST_TOPIC_EXCHANGE);
    }
    @Bean
    public Binding topicExchangeBindingA(Queue topicQueueA,@Qualifier("topicExchange") TopicExchange topicExchange){
        return BindingBuilder.bind(topicQueueA).to(topicExchange).with("one");
    }
    @Bean
    public Binding topicExchangeBindingB(Queue topicQueueB,@Qualifier("topicExchange") TopicExchange topicExchange){
        return BindingBuilder.bind(topicQueueB).to(topicExchange).with("two");
    }
    @Bean
    public Binding topicExchangeBindingC(Queue topicQueueC,@Qualifier("topicExchange") TopicExchange topicExchange){
        return BindingBuilder.bind(topicQueueC).to(topicExchange).with("three");
    }
    
}
