package com.example.spring_boot.service;

import com.example.spring_boot.dto.Book.CreateBookDTO;
import com.example.spring_boot.model.Book;
import com.example.spring_boot.model.User;
import com.example.spring_boot.repository.BookRepository;
import com.example.spring_boot.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public BookService(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @Transactional
    public Book getBookById(String id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Transactional
    public User createBook(CreateBookDTO book) {



        User existingUser = userRepository.findById(book.getAdminId()).orElse(null);

        if(existingUser == null || !existingUser.getAdmin()) {
            throw new RuntimeException("Admin not found!");
        }

        Book newBook = new Book();

        newBook.setTitle(book.getTitle());
        newBook.setAuthor(book.getAuthor());

        bookRepository.save(newBook);

        return existingUser;
    }

    @Transactional
    public User updateBook(String bookId, CreateBookDTO book) {
        User existingUser = userRepository.findById(book.getAdminId()).orElse(null);

        if(existingUser == null || !existingUser.getAdmin()) {
            throw new RuntimeException("Admin not found!");
        }

        Book existingBook = bookRepository.findById(bookId).orElse(null);

        if(existingBook == null) {
            throw new RuntimeException("Book not found!");
        }

        existingBook.setTitle(book.getTitle());
        existingBook.setAuthor(book.getAuthor());

        bookRepository.save(existingBook);

        return existingUser;
    }

    @Transactional
    public User deleteBook(String bookId) {
        User existingUser = userRepository.findById(bookId).orElse(null);

        if(existingUser == null || !existingUser.getAdmin()) {
            throw new RuntimeException("Admin not found!");
        }

        Book existingBook = bookRepository.findById(bookId).orElse(null);

        if(existingBook == null) {
            throw new RuntimeException("Book not found!");
        }

        bookRepository.deleteById(bookId);

        return existingUser;
    }

    // admin


}
