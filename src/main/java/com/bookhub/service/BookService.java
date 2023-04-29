package com.bookhub.service;

import com.bookhub.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {
    List<Book> findAll();
    Book findById(Long id);

    Page<Book> findByCategoryId(Long id, Pageable pageable);
    Book save(Book book);
    Book update(Long id, Book book);
    void deleteById(Long id);
}
