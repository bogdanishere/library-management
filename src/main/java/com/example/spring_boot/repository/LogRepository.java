package com.example.spring_boot.repository;

import com.example.spring_boot.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, String> {
}
