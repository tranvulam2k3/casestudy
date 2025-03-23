package com.example.library.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ErrorCode {
    BOOK_NOT_EXIST(40401, "Book is not exist", HttpStatus.NOT_FOUND),
    AUTHOR_NOT_EXIST(40402, "Author is not exist", HttpStatus.NOT_FOUND);
    int code;
    String message;
    HttpStatus status;
}
