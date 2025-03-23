package com.example.library.service.impl;

import com.example.library.dto.book.BookResponse;
import com.example.library.mapper.IBookMapper;
import com.example.library.model.Book;
import com.example.library.repository.IBookRepository;
import com.example.library.service.IBookService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class BookService implements IBookService {

    IBookRepository bookRepository;

    @Override
    public List<BookResponse> getAllBooks() {
        return bookRepository.findAllBookAndAuthor();
    }

    @Override
    public Optional<Book> findById(int id) {
        return bookRepository.findById(id);
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void delete(int id) {
        bookRepository.deleteById(id);
    }


}
