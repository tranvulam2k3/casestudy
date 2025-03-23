package com.example.library.dto.book;

import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class BookRequest {

    String title;
    String genre;
    int quantity;
    BigDecimal price;
    String status;
    String image;
    Integer author_id;
}
