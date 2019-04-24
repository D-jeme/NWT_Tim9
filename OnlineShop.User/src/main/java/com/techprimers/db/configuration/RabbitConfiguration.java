package com.techprimers.db.configuration;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {
    @Bean
    Queue articleCreatedQueue() {
        return new Queue("article_created_queue");
    }
}
