package com.example.demo.business.service.impl;

import com.example.demo.business.exception.BookRuntimeException;
import com.example.demo.business.model.Books;
import com.example.demo.business.model.NewBooks;
import com.example.demo.business.service.BooksService;
import com.example.demo.data.entity.BooksEntity;
import com.example.demo.data.repository.BooksRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class BooksServiceImpl implements BooksService {
    @Autowired
    private BooksRepository booksRepo;

    @Autowired
    private ModelMapper modelMapper;

//    public BooksServiceImpl(BooksRepository booksRepo, ModelMapper modelMapper) {
//        this.booksRepo = booksRepo;
//        this.modelMapper = modelMapper;
//    }

    @Override
    public List<Books> getAllBooks()  {
//throw new RuntimeException("you misspelled");
    try{
        List<Books> books  = booksRepo.findAll()
                .stream()
                .map(booksEntity -> modelMapper.map(booksEntity, Books.class))
                .collect(Collectors.toList());

        return  books;
    }
    catch (RuntimeException e){
        throw new BookRuntimeException("lalala", e);
    }

    }

    @Override
    public Optional<Books> getBooksById(Integer id) {

        return booksRepo.findById(id).map(booksEntity -> modelMapper.map(booksEntity, Books.class));
    }

    @Override
    public Books createBooks(NewBooks books) {
        try{
            BooksEntity booksEntity = modelMapper.map(books, BooksEntity.class);
            BooksEntity booksEntity2 = booksRepo.save(booksEntity);
            return modelMapper.map(booksEntity2, Books.class);
        }catch(Exception e){
            throw new BookRuntimeException("ttt", e);
        }

    }

    @Override
    public Books updateBooks(Integer id, Books books) {
        books.setId(id);
        BooksEntity booksEntity = modelMapper.map(books, BooksEntity.class);
        BooksEntity booksEntity2 = booksRepo.save(booksEntity);
        return modelMapper.map(booksEntity2, Books.class);
    }

    @Override
    public void deleteBooks(Integer id) {

       booksRepo.deleteById(id);
    }
}
