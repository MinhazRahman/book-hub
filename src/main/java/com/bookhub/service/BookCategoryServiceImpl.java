package com.bookhub.service;

import com.bookhub.model.BookCategory;
import com.bookhub.repository.BookCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookCategoryServiceImpl implements BookCategoryService {
    private final BookCategoryRepository bookCategoryRepository;

    @Override
    public List<BookCategory> findAll() {
        return bookCategoryRepository.findAll();
    }

    @Override
    public BookCategory findById(Long id) {
        return bookCategoryRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public BookCategory save(BookCategory bookCategory) {
        return bookCategoryRepository.save(bookCategory);
    }

    @Override
    @Transactional
    public BookCategory update(Long id, BookCategory bookCategory) {
        BookCategory existingBookCategory =bookCategoryRepository.findById(id).orElse(null);

        if (existingBookCategory != null) {
            existingBookCategory.setCategoryName(bookCategory.getCategoryName());
            // save the Book, if id == 0, then save/insert otherwise update
            return bookCategoryRepository.save(existingBookCategory);
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        bookCategoryRepository.deleteById(id);
    }
}
