package com.netcracker.library.kafka;

import com.netcracker.library.entity.Book;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class BookKafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String bookTopic;

    public BookKafkaProducer(KafkaTemplate<String, String> kafkaTemplate,
                             @Value("${spring.kafka.topic.book:book-changes}") String bookTopic) {
        this.kafkaTemplate = kafkaTemplate;
        this.bookTopic = bookTopic;
    }

    public void sendBookChangeEvent(String operation, Book book) {
        String message = String.format("%s: Book [id=%d, title=%s, authorId=%d]",
                operation, book.getId(), book.getTitle(),
                book.getAuthor().getId());
        kafkaTemplate.send(bookTopic, message);
    }
}
