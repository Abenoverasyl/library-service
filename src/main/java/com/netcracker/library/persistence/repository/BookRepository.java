package com.netcracker.library.persistence.repository;

import com.netcracker.library.persistence.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
