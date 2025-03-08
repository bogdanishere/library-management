package com.example.spring_boot.dto.Book;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateBookDTO {
    private String title;
    private String author;
    private String adminId;
}
