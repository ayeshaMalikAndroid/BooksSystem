package com.example.books.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "books") //collection

public class Book {
    @Id // primary key
    private String id;

    @Indexed // Indexing the title field to optimize queries
    private String title;

    @Indexed // Indexing the author field to optimize queries
    private String author;

    private Date publishedDate;
    private List<Category> categories;
    private int availableCopies;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
