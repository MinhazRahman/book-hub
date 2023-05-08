package com.bookhub.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "book_category")
// @Data - there is a known bug when dealing with oneToMany relationships
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "category_name")
    private String categoryName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    @JsonBackReference
    private Set<Book> books;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookCategory that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getCategoryName(), that.getCategoryName()) && Objects.equals(getBooks(), that.getBooks());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCategoryName(), getBooks());
    }
}
