package com.example.library.controller;

import com.example.library.dto.author.AuthorRequest;
import com.example.library.dto.author.AuthorResponse;
import com.example.library.enums.ErrorCode;
import com.example.library.exception.ApiException;
import com.example.library.mapper.IAuthorMapper;
import com.example.library.model.Author;
import com.example.library.service.IAuthorService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/authors")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@CrossOrigin(origins = "http://localhost:5173")
public class AuthorController {

    IAuthorService authorService;
    IAuthorMapper authorMapper;

    @GetMapping
    public ResponseEntity<?> getAuthors() {
        return ResponseEntity.ok(authorService.getAllAuthor());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getAuthorById(@PathVariable int id) {
        return authorService.getAuthorById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ApiException(ErrorCode.AUTHOR_NOT_EXIST));
    }

    @PostMapping
    public ResponseEntity<?> createAuthor(@RequestBody AuthorRequest authorRequest) {
        Author author = authorMapper.authorRequestToAuthor(authorRequest);
        authorService.save(author);
        AuthorResponse authorResponse = authorMapper.authorToAuthorResponse(author);
        return ResponseEntity.status(HttpStatus.CREATED).body(authorResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAuthor(@PathVariable int id, @RequestBody AuthorRequest authorRequest) {
        Author author = authorMapper.authorRequestToAuthor(authorRequest);
        author.setId(id);
        authorService.save(author);
        AuthorResponse authorResponse = authorMapper.authorToAuthorResponse(author);
        return ResponseEntity.ok(authorResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable int id) {
        authorService.getAuthorById(id).orElseThrow(() -> new ApiException(ErrorCode.AUTHOR_NOT_EXIST));
        authorService.deleteAuthor(id);
        return  ResponseEntity.noContent().build();
    }
}
