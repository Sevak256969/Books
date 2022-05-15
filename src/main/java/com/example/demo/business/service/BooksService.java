package com.example.demo.business.service;

import com.example.demo.business.model.Book;

import java.util.List;
import java.util.Optional;

public interface BooksService {

    List<Book> getAllBooks();
    Optional<Book> getBooksById(Integer id);
    Book createBooks(Book books);
    Book updateBooks(Integer id, Book books);
    void deleteBooks(Integer id);

}
