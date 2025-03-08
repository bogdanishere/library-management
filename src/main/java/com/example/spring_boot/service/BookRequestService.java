package com.example.spring_boot.service;

import com.example.spring_boot.model.Book;
import com.example.spring_boot.model.BookRequest;
import com.example.spring_boot.model.User;
import com.example.spring_boot.repository.BookRepository;
import com.example.spring_boot.repository.BookRequestRepository;
import com.example.spring_boot.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookRequestService {
    private final BookRequestRepository bookRequestRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public BookRequestService(BookRequestRepository bookRequestRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.bookRequestRepository = bookRequestRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @Transactional
    public List<BookRequest> getBookRequests(String adminId) {
        User existingUser = userRepository.findById(adminId).orElse(null);
        if (existingUser == null || !existingUser.getAdmin()) {
            throw new IllegalArgumentException("User not found");
        }
        return bookRequestRepository.findAll();
    }

    @Transactional
    public User getBookByUser(String userId, String bookId) {
        User existingUser = userRepository.findById(userId).orElse(null);
        if (existingUser == null) {
            throw new IllegalArgumentException("User not found");
        }

        // get book by id

        Book existingBook = bookRepository.findById(bookId).orElse(null);
        if (existingBook == null) {
            throw new IllegalArgumentException("Book not found");
        }


        // create book request

        BookRequest bookRequest = new BookRequest();
        bookRequest.setUser(existingUser);
        bookRequest.setBook(existingBook);
        bookRequestRepository.save(bookRequest);

        return existingUser;

    }

    // admin

    @Transactional
    public User approveBookByUser(String adminId, String bookRequestId) {
        User existingUser = userRepository.findById(adminId).orElse(null);
        if (existingUser == null || !existingUser.getAdmin()) {
            throw new IllegalArgumentException("User not found");
        }

        // is admin - todo - check if user is admin

        BookRequest existingBookRequest = bookRequestRepository.findById(bookRequestId).orElse(null);
        if (existingBookRequest == null) {
            throw new IllegalArgumentException("Book request not found");
        }

        existingBookRequest.setStatus(BookRequest.Status.ACCEPTED);
        bookRequestRepository.save(existingBookRequest);

        return existingUser;

    }

    @Transactional
    public User rejectBookByUser(String adminId, String bookRequestId) {
        User existingUser = userRepository.findById(adminId).orElse(null);
        if (existingUser == null) {
            throw new IllegalArgumentException("User not found");
        }

        // is admin - todo - check if user is admin

        if (!existingUser.getAdmin()) {
            throw new IllegalArgumentException("User is not admin");
        }

        BookRequest existingBookRequest = bookRequestRepository.findById(bookRequestId).orElse(null);
        if (existingBookRequest == null) {
            throw new IllegalArgumentException("Book request not found");
        }

        existingBookRequest.setStatus(BookRequest.Status.REJECTED);
        bookRequestRepository.save(existingBookRequest);

        return existingUser;

    }


}
