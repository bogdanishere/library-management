package com.example.spring_boot.controller;


import com.example.spring_boot.dto.MessageWithDataResponseDTO;
import com.example.spring_boot.model.Log;
import com.example.spring_boot.service.LogService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/log")
public class LogController {
    private final LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }

    @GetMapping("/all/{adminId}")
    public ResponseEntity<MessageWithDataResponseDTO> getLogs(@Valid @PathVariable String adminId) {
        List<Log> log = logService.getLogs(adminId);

        return ResponseEntity.ok(new MessageWithDataResponseDTO("Logs retrieved successfully", log));
    }
}
