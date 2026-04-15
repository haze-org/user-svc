package com.apricity.user_svc.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    static final String QUEUE_NAME = "profile-creation";
    static final String EXCHANGE = "user-registration-events";
    static final String ROUTING_KEY = "user.created";

    @Bean
    Queue queue(){
        return new Queue(QUEUE_NAME, true);
    }

    @Bean
    DirectExchange directExchange(){
        return new DirectExchange(EXCHANGE);
    }

    @Bean
    Binding binding(Queue queue, DirectExchange directExchange){
        return BindingBuilder.bind(queue).to(directExchange).with(ROUTING_KEY);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new JacksonJsonMessageConverter();
    }
}
