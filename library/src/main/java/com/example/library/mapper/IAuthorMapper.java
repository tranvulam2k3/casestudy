package com.example.library.mapper;

import com.example.library.dto.author.AuthorRequest;
import com.example.library.dto.author.AuthorResponse;
import com.example.library.model.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IAuthorMapper {
    Author authorRequestToAuthor (AuthorRequest authorRequest);
    AuthorResponse authorToAuthorResponse (Author author);
}
