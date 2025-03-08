package com.example.spring_boot.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Log {

    public enum LogType {
        LOGIN,
        LOGOUT,
        REGISTER,
        CREATE_USER_BY_ADMIN,
        UPDATE_USER_BY_ADMIN,
        DELETE,
        ADD_BOOK,
        UPDATE_BOOK,
        REMOVE_BOOK,
        BOOK_REQUEST_BY_USER,
        ACCEPT_REQUEST,
        REJECT_REQUEST,
        ADD_BUILDING,
        UPDATE_BUILDING,
        DELETE_BUILDING
    }

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(nullable = false)
    private LogType type;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "buildingId", referencedColumnName = "id")
    @JsonBackReference
    private Building building;

    @ManyToOne
    @JoinColumn(name = "bookId", referencedColumnName = "id")
    @JsonBackReference
    private Book book;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

}
