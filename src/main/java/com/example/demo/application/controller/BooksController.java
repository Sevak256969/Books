package com.example.demo.application.controller;

import com.example.demo.application.dto.BooksDto;
import com.example.demo.application.dto.NewBookDto;
import com.example.demo.business.model.Books;
import com.example.demo.business.model.NewBooks;
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
    public List<BooksDto> getBooks() {

        List<BooksDto> booksList = booksService.getAllBooks()
                .stream()
                .map(book -> modelMapper.map(book, BooksDto.class))
                .collect(Collectors.toList());

        return booksList;
    }

    @GetMapping(value = "/{id}")
    public Optional<BooksDto> getBookById(@PathVariable("id") Integer id) {
        return booksService.getBooksById(id).map(booksEntity -> modelMapper.map(booksEntity, BooksDto.class));
    }

    @PostMapping
    public BooksDto creatBook(@RequestBody @Valid NewBookDto bookDto){
        NewBooks book = modelMapper.map(bookDto, NewBooks.class);
        Books book2 = booksService.createBooks(book);
        return modelMapper.map(book2, BooksDto.class);
    }

    @PutMapping("/{id}")
    public BooksDto updateBook(@RequestBody  NewBookDto bookDto, @PathVariable("id") Integer id){
        Books book = modelMapper.map(bookDto, Books.class);
        Books book2 = booksService.updateBooks(id, book);
        return modelMapper.map(book2, BooksDto.class);
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
