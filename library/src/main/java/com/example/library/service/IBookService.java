package com.example.library.service;

import com.example.library.model.Book;

import java.util.List;
import java.util.Optional;

public interface IBookService {
    List<Book> getAllBooks();

    Optional<Book> findById(int id);

    Book save(Book book);

    void delete(int id);
}
