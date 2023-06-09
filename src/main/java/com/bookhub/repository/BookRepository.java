package com.bookhub.repository;

import com.bookhub.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>, PagingAndSortingRepository<Book, Long> {
    Page<Book> findByCategoryId(@Param("categoryId") Long categoryId, Pageable pageable);

    Page<Book> findByNameContaining(@Param("name") String name, Pageable pageable);

     //Page<Book> findAll(Pageable pageable);

}
