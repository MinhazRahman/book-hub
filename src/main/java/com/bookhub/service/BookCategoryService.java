package com.bookhub.service;

import com.bookhub.model.BookCategory;

import java.util.List;

public interface BookCategoryService {
    List<BookCategory> findAll();
    BookCategory findById(Long id);
    BookCategory save(BookCategory bookCategory);
    BookCategory update(Long id, BookCategory bookCategory);
    void deleteById(Long id);
}
