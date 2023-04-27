package com.bookhub.repository;

import com.bookhub.model.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookCategoryRepository extends JpaRepository<BookCategory, Long> {
}
