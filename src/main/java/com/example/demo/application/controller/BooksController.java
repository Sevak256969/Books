package com.example.demo.application.controller;

import com.example.demo.application.dto.BookDto;
import com.example.demo.business.model.Book;
import com.example.demo.business.service.BooksService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BooksController {
    @Autowired
    private BooksService booksService;
    @Autowired
    private ModelMapper modelMapper;
    @GetMapping
    public List<BookDto> getBooks() {

        List<BookDto> booksList = booksService.getAllBooks()
                .stream()
                .map(book -> modelMapper.map(book, BookDto.class))
                .collect(Collectors.toList());

        return booksList;
    }

    @GetMapping(value = "/{id}")
    public Optional<BookDto> getBookById(@PathVariable("id") Integer id) {
        return booksService.getBooksById(id).map(booksEntity -> modelMapper.map(booksEntity, BookDto.class));
    }

    @PostMapping
    public BookDto createBook(@RequestBody @Valid BookDto bookDto){
        Book book = modelMapper.map(bookDto, Book.class);
        Book book2 = booksService.createBooks(book);
        return modelMapper.map(book2, BookDto.class);
    }

    @PutMapping("/{id}")
    public BookDto updateBook(@RequestBody  BookDto bookDto, @PathVariable("id") Integer id){
        Book book = modelMapper.map(bookDto, Book.class);
        Book book2 = booksService.updateBooks(id, book);
        return modelMapper.map(book2, BookDto.class);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable("id") Integer id) {

        booksService.deleteBooks(id);
    }
    @ExceptionHandler
    public void wrapException(Exception exception){
        if(exception instanceof ResponseStatusException){
            throw (ResponseStatusException)exception;
        }else if(exception instanceof RuntimeException){
            throw (RuntimeException)exception;
        }
    }
}
