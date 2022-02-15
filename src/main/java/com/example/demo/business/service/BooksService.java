package com.example.demo.business.service;

import com.example.demo.business.model.Books;
import com.example.demo.business.model.NewBooks;

import java.util.List;
import java.util.Optional;

public interface BooksService {

    List<Books> getAllBooks();

    Optional<Books> getBooksById(Integer id);

    Books createBooks(NewBooks books);

    Books updateBooks(Integer id, Books books);

    void deleteBooks(Integer id);

}
