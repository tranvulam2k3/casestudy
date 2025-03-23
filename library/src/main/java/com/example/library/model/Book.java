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
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    Integer id;

    @Column(name="title")
    String title;

    @Column(name="genre")
    String genre;

    @Column(name="quantity")
    int quantity;

    @Column(name="price")
    BigDecimal price;

    @Column(name="status")
    String status;

    @Column(name = "image")
    String image;

    @ManyToOne
    @JoinColumn(name = "author_id")
    Author author;

}
