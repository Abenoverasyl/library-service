package com.netcracker.library.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic authorTopic() {
        return new NewTopic("author-changes", 1, (short) 1);
    }

    @Bean
    public NewTopic bookTopic() {
        return new NewTopic("book-changes", 1, (short) 1);
    }
}
