package com.example.spring_boot.repository;

import com.example.spring_boot.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> {
}
