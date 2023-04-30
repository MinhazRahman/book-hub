package com.bookhub.service;

import com.bookhub.model.Book;
import com.bookhub.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{
    // inject BookRepository using constructor injection
    private final BookRepository bookRepository;

    @Override
    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public Book findById(Long id) {

        return bookRepository.findById(id).orElseThrow();
    }

    @Override
    public Page<Book> findByCategoryId(Long id, Pageable pageable){
        return bookRepository.findByCategoryId(id, pageable);
    }

    @Override
    public Page<Book> findByNameContaining(String name, Pageable pageable){
        return bookRepository.findByNameContaining(name, pageable);
    }
    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book update(Long id, Book book) {
        Book existingBook =bookRepository.findById(id).orElseThrow();

        existingBook.setName(book.getName());
        existingBook.setAuthor(book.getAuthor());
        // save the Book, if id == 0, then save/insert otherwise update
        return bookRepository.save(existingBook);
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}
