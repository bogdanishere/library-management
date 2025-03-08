package com.example.spring_boot.controller;


import com.example.spring_boot.dto.Book.CreateBookDTO;
import com.example.spring_boot.dto.MessageResponseDTO;
import com.example.spring_boot.dto.MessageWithDataResponseDTO;
import com.example.spring_boot.model.Book;
import com.example.spring_boot.model.User;
import com.example.spring_boot.service.BookRequestService;
import com.example.spring_boot.service.BookService;
import com.example.spring_boot.service.LogService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {

    private final BookService bookService;
    private final LogService logService;

    public BookController(BookService bookService, LogService logService) {
        this.bookService = bookService;
        this.logService = logService;
    }

    @GetMapping("/all")
    public ResponseEntity<MessageWithDataResponseDTO> getBooks() {
        List<Book> allBooks = bookService.getBooks();
        return ResponseEntity.ok(new MessageWithDataResponseDTO("Books retrieved successfully", allBooks));
    }


    @GetMapping("/{id}")
    public ResponseEntity<MessageWithDataResponseDTO> getBookById(@Valid @PathVariable String id) {
        Book book = bookService.getBookById(id);
        return ResponseEntity.ok(new MessageWithDataResponseDTO("Book retrieved successfully", book));
    }

    // admin

    @PostMapping("/create")
    public ResponseEntity<MessageResponseDTO> createBook(@Valid @RequestBody CreateBookDTO book) {
        User existingUser = bookService.createBook(book);
        logService.CreateBookByAdminAction(existingUser);

        return ResponseEntity.ok(new MessageResponseDTO("Book created successfully!"));
    }

    @PutMapping("/update/{bookId}")
    public ResponseEntity<MessageResponseDTO> updateBook(@Valid @PathVariable String bookId, @RequestBody CreateBookDTO book) {
        User existingUser = bookService.updateBook(bookId, book);
        logService.UpdateBookByAdminAction(existingUser);

        return ResponseEntity.ok(new MessageResponseDTO("Book updated successfully!"));
    }

    @DeleteMapping("/delete/{bookId}")
    public ResponseEntity<MessageResponseDTO> deleteBook(@Valid @PathVariable String bookId) {
        User existingUser = bookService.deleteBook(bookId);
        logService.DeleteBookByAdminAction(existingUser);

        return ResponseEntity.ok(new MessageResponseDTO("Book deleted successfully!"));
    }

}
