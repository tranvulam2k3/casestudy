package com.example.library.repository;

import com.example.library.dto.book.BookResponse;
import com.example.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBookRepository extends JpaRepository<Book, Integer> {

    @Query(value = """
                SELECT b.book_id,
                       b.title,
                       b.genre,
                       b.quantity,
                       b.price,
                       b.status,
                       b.image,
                       a.name,
                       a.image,
                       a.bio
                FROM book b
                JOIN author a ON b.author_id = a.author_id
            """, nativeQuery = true)
    List<BookResponse> findAllBookAndAuthor();
}
