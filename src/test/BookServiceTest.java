package com.example.demo;

import com.example.demo.Book;
import com.example.demo.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;

class BookServiceTest {

    private BookRepository bookRepository;
    private BookService bookService;

    @BeforeEach
    void setUp() {
        bookRepository = mock(BookRepository.class);
        bookService = new BookService(bookRepository);
    }

    @Test
    void testAddBook() {
        Book book = new Book(null, "Clean Code", "Robert C. Martin", "1234567890", 2008);

        // Use any(Book.class) so Mockito matches any Book object
        when(bookRepository.save(any(Book.class)))
                .thenReturn(new Book(1L, "Clean Code", "Robert C. Martin", "1234567890", 2008));

        Book saved = bookService.addBook(book);

        assertNotNull(saved, "Saved book should not be null");
        assertEquals(1L, saved.getId(), "Saved book ID should be 1");
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void testGetAllBooks() {
        List<Book> books = Arrays.asList(
                new Book(1L, "Book1", "Author1", "1111", 2000),
                new Book(2L, "Book2", "Author2", "2222", 2005)
        );

        when(bookRepository.findAll()).thenReturn(books);

        List<Book> result = bookService.getAllBooks();

        assertEquals(2, result.size(), "Should return 2 books");
        verify(bookRepository, times(1)).findAll();
    }
}
