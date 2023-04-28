package com.bookhub.controller;

import com.bookhub.model.BookCategory;
import com.bookhub.service.BookCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
// Accept calls from web browser scripts this origin
@CrossOrigin("http://localhost:4200/")
@RestController
@RequestMapping("/bookhub")
@RequiredArgsConstructor
public class BookCategoryController {
    private final BookCategoryService bookCategoryService;

    /**
     * define endpoint for GET "/bookCategories"
     * Returns a list of books
     * */
    @GetMapping("/bookCategories")
    public List<BookCategory> getBookCategories(){
        // retrieve all the books
        return bookCategoryService.findAll();
    }

    /**
     * Define endpoint for GET "/bookCategories/{categoryId}"
     * Returns the book
     * */
    @GetMapping("/bookCategories/{categoryId}")
    public BookCategory getBookById(@PathVariable(name = "categoryId") Long categoryId){
        BookCategory bookCategory = bookCategoryService.findById(categoryId);

        if (bookCategory == null){
            throw new RuntimeException("Book not found with id - " + categoryId);
        }
        return bookCategory;
    }

    /**
     * Define endpoint for POST "/books"
     * Create the book entity into the database
     * */
    @PostMapping("/bookCategories")
    public BookCategory addBook(@RequestBody BookCategory bookCategory){
        // just in case we pass an id with Json - set the id to 0
        // this is to force a save of the item - instead of update
        bookCategory.setId(0L);
        // save the Customer, if id == 0, then save/insert otherwise update
        // return the saved customer
        return bookCategoryService.save(bookCategory);
    }


    /**
     * Define endpoint for PUT "/bookCategories"
     * Update the book entity into the database
     * */
    @PutMapping("/bookCategories/{categoryId}")
    public BookCategory updateBook(@PathVariable(name = "categoryId") Long categoryId, @RequestBody BookCategory bookCategory){
        // return the updated book
        return bookCategoryService.update(categoryId, bookCategory);
    }

    /**
     * Define endpoint for "/bookCategories/{categoryId}"
     * Deletes the Book Category entity from the database
     * */
    @DeleteMapping("/bookCategories/{categoryId}")
    public String deleteBookById(@PathVariable(name = "categoryId") Long categoryId){
        BookCategory bookCategory = bookCategoryService.findById(categoryId);
        if (bookCategory == null){
            throw new RuntimeException("Book id not found - " + categoryId);
        }
        bookCategoryService.deleteById(categoryId);
        return "Deleted book with id -" + categoryId;
    }
}
