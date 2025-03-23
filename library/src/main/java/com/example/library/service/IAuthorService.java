package com.example.library.service;

import com.example.library.dto.author.AuthorRequest;
import com.example.library.dto.author.AuthorResponse;
import com.example.library.model.Author;

import java.util.List;
import java.util.Optional;

public interface IAuthorService {
    List<Author> getAllAuthor();
    Optional<Author> getAuthorById(int id);
    Author save(Author author);
    void deleteAuthor(int id);

}
