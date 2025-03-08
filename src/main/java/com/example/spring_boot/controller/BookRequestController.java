package com.example.spring_boot.controller;

import com.example.spring_boot.dto.MessageResponseDTO;
import com.example.spring_boot.dto.MessageWithDataResponseDTO;
import com.example.spring_boot.model.BookRequest;
import com.example.spring_boot.model.User;
import com.example.spring_boot.service.BookRequestService;
import com.example.spring_boot.service.LogService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book-request")
public class BookRequestController {
    private final BookRequestService bookRequestService;
    private final LogService logService;

    public BookRequestController(BookRequestService bookRequestService, LogService logService) {
        this.bookRequestService = bookRequestService;
        this.logService = logService;
    }

    @GetMapping("/all/{adminId}")
    public ResponseEntity<MessageWithDataResponseDTO> getBookRequests(@Valid @PathVariable String adminId) {
        List<BookRequest> allBookRequests = bookRequestService.getBookRequests(adminId);
        return ResponseEntity.ok(new MessageWithDataResponseDTO("Book requests retrieved successfully", allBookRequests));
    }

    @PostMapping("/request/{userId}/{bookId}")
    public ResponseEntity<MessageResponseDTO> requestBook(@Valid @PathVariable String userId, @PathVariable String bookId) {
        User existingUser = bookRequestService.getBookByUser(userId, bookId);
        logService.BookRequestByUserAction(existingUser);

        return ResponseEntity.ok(new MessageResponseDTO("Book requested successfully!"));
    }

    @PutMapping("/approve/{adminId}/{bookRequestId}")
    public ResponseEntity<MessageResponseDTO> approveBook(@Valid @PathVariable String adminId, @PathVariable String bookRequestId) {
        User existingUser = bookRequestService.approveBookByUser(adminId, bookRequestId);
        logService.BookRequestAcceptedByAdminAction(existingUser);

        return ResponseEntity.ok(new MessageResponseDTO("Book approved successfully!"));
    }

    @PutMapping("/reject/{adminId}/{bookRequestId}")
    public ResponseEntity<MessageResponseDTO> rejectBook(@Valid @PathVariable String adminId, @PathVariable String bookRequestId) {
        User existingUser = bookRequestService.rejectBookByUser(adminId, bookRequestId);
        logService.BookRequestRejectedByAdminAction(existingUser);

        return ResponseEntity.ok(new MessageResponseDTO("Book rejected successfully!"));
    }

}
