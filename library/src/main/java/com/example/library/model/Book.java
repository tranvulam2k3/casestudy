package com.example.library.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @Column(name="title")
    String title;

    @Column(name="author")
    String author;

    @Column(name="genre")
    String genre;

    @Column(name="quantity")
    int quantity;

    @Column(name="price")
    BigDecimal price;

    @Column(name="status")
    String status;

}
