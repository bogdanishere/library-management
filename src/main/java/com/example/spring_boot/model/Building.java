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
public class Building {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private String name;

    @OneToMany
    @JoinColumn(name = "buildingId", referencedColumnName = "id")
    @JsonManagedReference
    private List<Book> books;

    @OneToMany
    @JoinColumn(name = "buildingId", referencedColumnName = "id")
    @JsonManagedReference
    private List<Log> logs;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    @JsonBackReference
    private User user;
}
