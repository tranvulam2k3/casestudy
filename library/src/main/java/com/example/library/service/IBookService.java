package com.example.library.service;

import com.example.library.dto.book.BookResponse;
import com.example.library.model.Book;

import java.util.List;
import java.util.Optional;

public interface IBookService {
    List<BookResponse> getAllBooks();

//    List<BookResponse> getAll();

    Optional<Book> findById(int id);

    Book save(Book book);

    void delete(int id);
}
