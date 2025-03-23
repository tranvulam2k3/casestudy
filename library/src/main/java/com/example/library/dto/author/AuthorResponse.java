package com.example.library.dto.author;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class AuthorResponse {
    Integer id;
    String name;
    String image;
    String bio;
}
