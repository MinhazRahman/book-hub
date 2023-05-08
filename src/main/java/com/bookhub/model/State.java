package com.bookhub.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Table(name = "state")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class State {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    /*
    * Set up many-one relationship, many States belong to one Country
    * */
    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    @JsonManagedReference
    private Country country;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof State state)) return false;
        return getId() == state.getId() && Objects.equals(getName(), state.getName()) && Objects.equals(getCountry(), state.getCountry());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getCountry());
    }
}
