package com.netcracker.library.persistence.repository;


import com.netcracker.library.persistence.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
