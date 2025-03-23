package com.example.library.dto.book;

import com.example.library.model.Book;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class BookResponse {
    Integer bookId;
    String title;
    String genre;
    Integer quantity;
    BigDecimal price;
    String status;
    String image;
    String authorName;
    String authorImage;
    String authorBio;
}
