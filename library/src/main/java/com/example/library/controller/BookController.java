package com.example.library.controller;

import com.example.library.dto.book.BookResponse;
import com.example.library.enums.ErrorCode;
import com.example.library.exception.ApiException;
import com.example.library.mapper.IBookMapper;
import com.example.library.model.Book;
import com.example.library.service.IBookService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/librarys")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE , makeFinal = true)
@CrossOrigin(origins = "http://localhost:5173")

public class BookController {

    IBookService bookService;

    @GetMapping
    public ResponseEntity<?> getBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id) {
        return bookService.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ApiException(ErrorCode.BOOK_NOT_EXIST));
    }

    @PostMapping
    public ResponseEntity<?> createBook(@RequestBody Book book) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.save(book));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable("id") int id, @RequestBody Book book) {
        bookService.findById(id).orElseThrow(() -> new ApiException(ErrorCode.BOOK_NOT_EXIST));
        book.setId(id);
        return ResponseEntity.ok(bookService.save(book));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable("id") int id) {
        bookService.findById(id).orElseThrow(() -> new ApiException(ErrorCode.BOOK_NOT_EXIST));
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
