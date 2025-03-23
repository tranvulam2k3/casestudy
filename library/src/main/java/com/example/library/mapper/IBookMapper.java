package com.example.library.mapper;

import com.example.library.dto.book.BookRequest;
import com.example.library.dto.book.BookResponse;
import com.example.library.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IBookMapper {
    Book bookRequestToBook(BookRequest bookRequest);

    @Mapping(source = "author.name", target = "authorName")
    @Mapping(source = "author.image", target = "authorImage")
    @Mapping(source = "author.bio", target = "authorBio")
    BookResponse bookToBookResponse(Book book);
}
