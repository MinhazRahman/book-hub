package com.bookhub.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "country")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    /*
     * set up one-many relationship, one Country has many States
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "country")
    @JsonBackReference
    private List<State> states;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Country country)) return false;
        return getId() == country.getId() && Objects.equals(getCode(), country.getCode()) && Objects.equals(getName(), country.getName()) && Objects.equals(getStates(), country.getStates());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCode(), getName(), getStates());
    }
}
