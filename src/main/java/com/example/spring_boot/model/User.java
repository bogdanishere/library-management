package com.example.spring_boot.model;

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
public class User {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Boolean admin = false;

    @Column(nullable = false)
    private String password;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    @JsonManagedReference
    private List<BookRequest> books;

    @OneToMany
    @JoinColumn(name = "userId", referencedColumnName = "id")
    @JsonManagedReference
    private List<Log> logs;

    @OneToMany
    @JoinColumn(name = "userId", referencedColumnName = "id")
    @JsonManagedReference
    private List<Building> buildings;


}
