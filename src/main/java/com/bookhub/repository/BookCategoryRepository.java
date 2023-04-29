package com.bookhub.repository;

import com.bookhub.model.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200/")
@Repository
public interface BookCategoryRepository extends JpaRepository<BookCategory, Long> {
}
