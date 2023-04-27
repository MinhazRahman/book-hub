package com.bookhub.service;

import com.bookhub.model.Book;

import java.util.List;

public interface BookService {
    List<Book> findAll();
    Book findById(Long id);
    Book save(Book book);
    Book update(Long id, Book book);
    void deleteById(Long id);
}
