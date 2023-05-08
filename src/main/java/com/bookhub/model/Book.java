package com.bookhub.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "book")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "stock_keeping_unit")
    private String stockKeepingUnit;

    @Column(name = "name")
    private String name;

    @Column(name = "author")
    private String author;

    @Column(name = "description")
    private String description;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "active")
    private boolean active;

    @Column(name = "units_in_stock")
    private int unitsInStock;

    @Column(name = "date_created")
    @CreationTimestamp
    private Date dateCreated;

    @Column(name = "last_updated")
    @UpdateTimestamp
    private Date lastUpdated;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    @JsonManagedReference
    private BookCategory category;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;
        return isActive() == book.isActive() && getUnitsInStock() == book.getUnitsInStock() && Objects.equals(getId(), book.getId()) && Objects.equals(getStockKeepingUnit(), book.getStockKeepingUnit()) && Objects.equals(getName(), book.getName()) && Objects.equals(getAuthor(), book.getAuthor()) && Objects.equals(getDescription(), book.getDescription()) && Objects.equals(getUnitPrice(), book.getUnitPrice()) && Objects.equals(getImageUrl(), book.getImageUrl()) && Objects.equals(getDateCreated(), book.getDateCreated()) && Objects.equals(getLastUpdated(), book.getLastUpdated()) && Objects.equals(getCategory(), book.getCategory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getStockKeepingUnit(), getName(), getAuthor(), getDescription(), getUnitPrice(), getImageUrl(), isActive(), getUnitsInStock(), getDateCreated(), getLastUpdated(), getCategory());
    }
}
