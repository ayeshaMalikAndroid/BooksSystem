package com.example.books.repositories;

import com.example.books.entities.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BookRepository extends MongoRepository<Book, String> {
    // Query to find books by title
    List<Book> findByTitle(String title);

    // Query to find books by author
    List<Book> findByAuthor(String author);

    // Query to find books by category name
    List<Book> findByCategoriesName(String categoryName);
}
