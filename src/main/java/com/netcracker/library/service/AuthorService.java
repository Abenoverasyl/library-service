package com.netcracker.library.service;

import com.netcracker.library.persistence.entity.Author;
import com.netcracker.library.kafka.AuthorKafkaProducer;
import com.netcracker.library.persistence.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorKafkaProducer authorKafkaProducer;

    public AuthorService(AuthorRepository authorRepository, AuthorKafkaProducer authorKafkaProducer) {
        this.authorRepository = authorRepository;
        this.authorKafkaProducer = authorKafkaProducer;
    }

    public Author createAuthor(Author author) {
        Author saved = authorRepository.save(author);
        authorKafkaProducer.sendAuthorChangeEvent("CREATE", saved);
        return saved;
    }

    public Author updateAuthor(Long id, Author updatedAuthor) {
        return authorRepository.findById(id)
                .map(existing -> {
                    existing.setName(updatedAuthor.getName());
                    Author saved = authorRepository.save(existing);
                    authorKafkaProducer.sendAuthorChangeEvent("UPDATE", saved);
                    return saved;
                })
                .orElseThrow(() -> new RuntimeException("Author not found with ID: " + id));
    }

    public void deleteAuthor(Long id) {
        authorRepository.findById(id)
                .ifPresent(author -> {
                    authorRepository.deleteById(id);
                    authorKafkaProducer.sendAuthorChangeEvent("DELETE", author);
                });
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found with ID: " + id));
    }
}
