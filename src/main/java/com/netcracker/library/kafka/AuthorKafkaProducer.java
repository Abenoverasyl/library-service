package com.netcracker.library.kafka;

import com.netcracker.library.entity.Author;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class AuthorKafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String authorTopic;

    public AuthorKafkaProducer(KafkaTemplate<String, String> kafkaTemplate,
                               @Value("${spring.kafka.topic.author:author-changes}") String authorTopic) {
        this.kafkaTemplate = kafkaTemplate;
        this.authorTopic = authorTopic;
    }

    public void sendAuthorChangeEvent(String operation, Author author) {
        String message = String.format("%s: Author [id=%d, name=%s]", operation, author.getId(), author.getName());
        kafkaTemplate.send(authorTopic, message);
    }
}