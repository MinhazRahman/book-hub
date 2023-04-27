package com.bookhub.service;

import com.bookhub.model.Book;
import com.bookhub.repository.BookRepository;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Data
@Service
public class BookServiceImpl implements BookService{
    // inject BookRepository using constructor injection
    private BookRepository bookRepository;

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public Book update(Long id, Book book) {
        Book existingBook =bookRepository.findById(id).orElse(null);

        if (existingBook != null) {
            existingBook.setName(book.getName());
            existingBook.setAuthor(book.getAuthor());
            // save the Book, if id == 0, then save/insert otherwise update
            return bookRepository.save(existingBook);
        }
        return null;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}
