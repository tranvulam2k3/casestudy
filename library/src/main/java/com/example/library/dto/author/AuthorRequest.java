package com.example.library.dto.author;

import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class AuthorRequest {
    String name;
    String image;
    String bio;
}
