package com.bookhub.controller;

import com.bookhub.model.Book;
import com.bookhub.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/bookhub")
@RequiredArgsConstructor

public class BookController {
    // Define bookService field
    private final  BookService bookService;

    /**
     * define endpoint for GET "/books"
     * Returns a list of books
     * */
    @GetMapping("/books")
    public Page<Book> getBooks(Pageable pageable){
        // retrieve all the books
        return bookService.findAll(pageable);
    }

    /**
     * Define endpoint for GET "/books/{bookId}"
     * Returns the book
     * */
    @GetMapping("/books/{bookId}")
    public Book getBookById(@PathVariable Long bookId){
        Book book = bookService.findById(bookId);

        if (book == null){
            throw new RuntimeException("Book not found with id - " + bookId);
        }
        return book;
    }

    /**
     * Define endpoint for GET "/books/findByCategoryId/{categoryId}"
     * Returns the book
     * */
    @GetMapping("/books/findByCategoryId/{categoryId}")
    public Page<Book> getBooksByCategoryId(@PathVariable(name = "categoryId") Long categoryId, Pageable pageable){
        Page<Book> page = bookService.findByCategoryId(categoryId, pageable);

        if (page == null){
            throw new RuntimeException("Can't find Books with category id - " + categoryId);
        }
        return page;
    }

    /**
     * Define endpoint for GET "/books/findByNameContaining/{bookName}"
     * Returns the book
     * */
    @GetMapping("/books/findByNameContaining/{bookName}")
    public Page<Book> getBooksByName(@PathVariable(name = "bookName") String bookName, Pageable pageable){
        Page<Book> bookPage = bookService.findByNameContaining(bookName, pageable);

        if (bookPage == null){
            throw new RuntimeException("Can't find Books with name - " + bookName);
        }
        return bookPage;
    }

    /**
     * Define endpoint for POST "/books"
     * Create the book entity into the database
     * */
    @PostMapping("/books")
    public Book addBook(@RequestBody Book book){
        // just in case we pass an id with Json - set the id to 0
        // this is to force a save of the item - instead of update
        book.setId(0L);
        // save the Customer, if id == 0, then save/insert otherwise update
        // return the saved customer
        return bookService.save(book);
    }


    /**
     * Define endpoint for PUT "/books"
     * Update the book entity into the database
     * */
    @PutMapping("/books/{bookId}")
    public Book updateBook(@PathVariable Long bookId, @RequestBody Book book){
        // return the updated book
        return bookService.update(bookId, book);
    }

    /**
     * Define endpoint for "/books/{bookId}"
     * Deletes the book entity from the database
     * */
    @DeleteMapping("/books/{bookId}")
    public String deleteBookById(@PathVariable Long bookId){
        Book customer = bookService.findById(bookId);
        if (customer == null){
            throw new RuntimeException("Book id not found - " + bookId);
        }
        bookService.deleteById(bookId);
        return "Deleted book with id -" + bookId;
    }
}
