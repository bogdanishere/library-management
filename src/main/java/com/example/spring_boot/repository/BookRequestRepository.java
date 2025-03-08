package com.example.spring_boot.repository;

import com.example.spring_boot.model.BookRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRequestRepository extends JpaRepository<BookRequest, String> {
}
