package com.example.spring_boot.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @OneToMany
    @JoinColumn(name = "bookId", referencedColumnName = "id")
    @JsonManagedReference
    private List<BookRequest> bookRequest;

    @ManyToOne
    @JoinColumn(name = "buildingId", referencedColumnName = "id")
    @JsonBackReference
    private Building building;

    @OneToMany
    @JoinColumn(name = "bookId", referencedColumnName = "id")
    @JsonManagedReference
    private List<Log> logs;
}
