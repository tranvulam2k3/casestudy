package com.example.library.service.impl;

import com.example.library.dto.author.AuthorRequest;
import com.example.library.dto.author.AuthorResponse;
import com.example.library.model.Author;
import com.example.library.repository.IAuthorRepository;
import com.example.library.service.IAuthorService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthorService implements IAuthorService {

    IAuthorRepository authorRepository;

    @Override
    public List<Author> getAllAuthor() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> getAuthorById(int id) {
        return authorRepository.findById(id);
    }

    @Override
    public Author save(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public void deleteAuthor(int id) {
        authorRepository.deleteById(id);
    }
}
