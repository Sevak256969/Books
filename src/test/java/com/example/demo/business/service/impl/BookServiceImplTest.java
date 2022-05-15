package com.example.demo.business.service.impl;

import com.example.demo.business.exception.BookRuntimeException;
import com.example.demo.business.model.Book;
import com.example.demo.business.service.BooksService;
import com.example.demo.data.entity.BooksEntity;
import com.example.demo.data.repository.BooksRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@SpringBootTest
public class BookServiceImplTest {
    @Mock
    private BooksRepository booksRepo;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private BooksService booksService = new BooksServiceImpl();

    @Test
    public void get_all_books_ok() {
        //mocked test data
        List<BooksEntity> books = new ArrayList<>();
        BooksEntity books1 = new BooksEntity(1,"Ashot","Shun",1922,123,"Poem");
        BooksEntity books2 = new BooksEntity(2,"Karen","Cat",1234,789,"Poem");
        BooksEntity books3 = new BooksEntity(3,"Sevak","gdghdh",1899,67,"Poem");
        BooksEntity books4 = new BooksEntity(4,"Aren","dgdg",1345,56,"Poem");

        books.add(books1);
        books.add(books2);
        books.add(books3);
        books.add(books4);

        Book books5 = new Book(1,"Ashot","Shun",1922,123,"Poem");
        Book books6 = new Book(2,"Karen","Cat",1234,789,"Poem");
        Book books7 = new Book(3,"Sevak","gdghdh",1899,67,"Poem");
        Book books8 = new Book(4,"Aren","dgdg",1345,56,"Poem");

        when(booksRepo.findAll()).thenReturn(books);
        when(modelMapper.map(books1, Book.class)).thenReturn(books5);
        when(modelMapper.map(books2, Book.class)).thenReturn(books6);
        when(modelMapper.map(books3, Book.class)).thenReturn(books7);
        when(modelMapper.map(books4, Book.class)).thenReturn(books8);
        
        // test- call the method that u test
        List<Book> booksList = booksService.getAllBooks();

        //assertion - validate the results
        assertEquals(4, booksList.size());

        Book book = booksList.get(0);
        assertEquals(books1.getId(), book.getId());
        assertEquals("Ashot", book.getAuthor());
        assertEquals("Shun", book.getName());
        assertEquals(1922, book.getYearWhenWritten());
        assertEquals(123, book.getNumberOfPages());
        assertEquals("Poem", book.getGenre());

        verify(booksRepo, times(1)).findAll();
    }

    @Test
    public void get_books_by_id_ok() {
        BooksEntity booksEntity = new BooksEntity(1,"Ashot","Shun",1922,123,"Poem");
        Book books = new Book(1, "Ashot","Shun",1922,123,"Poem");

        when(booksRepo.findById(1)).thenReturn(Optional.of(booksEntity));
        when(modelMapper.map(any(), any())).thenReturn(books, Book.class);

        Optional<Book> book = booksService.getBooksById(1);

        assertTrue(book.isPresent());
        assertEquals(1, book.get().getId());

        verify(booksRepo, times(1)).findById(1);
    }

    @Test
    public void test_create_books_exception() {

        Book books = new Book(1,"Ashot","Shun",1922,123,"Poem");
        BooksEntity b = new BooksEntity(1, "Ashot","Shun",1922,123,"Poem");
        when(modelMapper.map(any(), any())).thenReturn(b);
        when(booksRepo.save(any())).thenThrow(RuntimeException.class);

        Assertions.assertThrows(BookRuntimeException.class, () ->{
            booksService.createBooks(books);
        }, "Was expending to save BookRuntimeException but didn't save");
    }

    @Test
    public void create_books_ok() {

        Book books = new Book(1,"Ashot","Shun",1922,123,"Poem");
        BooksEntity booksEntity = new ModelMapper().map(books, BooksEntity.class);
        Book books2 = new Book(1,"Ashot","Shun",1922,123,"Poem");

        when(booksRepo.save(booksEntity)).thenReturn(booksEntity);
        when(modelMapper.map(books, BooksEntity.class)).thenReturn(booksEntity);
        when(modelMapper.map(booksEntity, Book.class)).thenReturn(books2);

        Book book = booksService.createBooks(books);

        assertEquals(1, book.getId());
        assertEquals("Ashot", book.getAuthor());
        assertEquals("Shun", book.getName());
        assertEquals(1922, book.getYearWhenWritten());
        assertEquals(123, book.getNumberOfPages());
        assertEquals("Poem", book.getGenre());

        verify(booksRepo, times(1)).save(booksEntity);
    }

    @Test
    public void update_books_ok() {

        Book books = new Book(1,"Ashot","Shun",1922,123,"Poem");
        BooksEntity booksEntity = new ModelMapper().map(books, BooksEntity.class);
        Book books2 = new Book(1,"Ashot","Shun",1922,123,"Poem");

        when(booksRepo.save(booksEntity)).thenReturn(booksEntity);
        when(modelMapper.map(books, BooksEntity.class)).thenReturn(booksEntity);
        when(modelMapper.map(booksEntity, Book.class)).thenReturn(books2);

        Book book = booksService.updateBooks(1,books);

        assertEquals(1, book.getId());
        assertEquals("Ashot", book.getAuthor());
        assertEquals("Shun", book.getName());
        assertEquals(1922, book.getYearWhenWritten());
        assertEquals(123, book.getNumberOfPages());

        verify(booksRepo, times(1)).save(booksEntity);
    }

    @Test
    public void delete_books_ok() {
        final Integer id = 1;
        booksService.deleteBooks(id);
    }
}
