package com.netcracker.library.kafka;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class LibraryKafkaConsumer {

    private static final Logger logger = LoggerFactory.getLogger(LibraryKafkaConsumer.class);

    @KafkaListener(topics = "author-changes", groupId = "library-group")
    public void listenAuthorChanges(ConsumerRecord<String, String> record) {
        logger.info("Received message on 'author-changes' topic: key={}, value={}", record.key(), record.value());
    }

    @KafkaListener(topics = "book-changes", groupId = "library-group")
    public void listenBookChanges(ConsumerRecord<String, String> record) {
        logger.info("Received message on 'book-changes' topic: key={}, value={}", record.key(), record.value());
    }
}

