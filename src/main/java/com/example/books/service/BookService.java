package com.example.books.service;

import com.example.books.entities.Book;
import com.example.books.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }


    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }


    public Book getBookById(String id) {
        return bookRepository.findById(id).orElse(null);
    }


    public Book updateBook(String id, Book book) {
        book.setId(id);
        return bookRepository.save(book);
    }


    public void deleteBook(String id) {
        bookRepository.deleteById(id);
    }


    public List<Book> getBooksByCategory(String category) {
        return bookRepository.findByCategoriesName(category);
    }

    public List<Book> getBooksByAuthor(String authorName) {
        return bookRepository.findByAuthor(authorName);
    }

    public List<Book> getBooksByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    // Method to get books by category using an aggregation pipeline
    public List<Book> getBooksByCategoryAggregation(String category) {
        // Define the aggregation pipeline
        Aggregation aggregation = Aggregation.newAggregation(
                // Match stage to filter books by the given category
                Aggregation.match(Criteria.where("categories").is(category)),
                // Group stage to count books by author
                Aggregation.group("author").count().as("totalBooks"),
                // Project stage to shape the output
                Aggregation.project("totalBooks").and("author").previousOperation()
        );
        // Execute the aggregation pipeline
        AggregationResults<Book> results = mongoTemplate.aggregate(aggregation, "books", Book.class);
        return results.getMappedResults();
    }
}
