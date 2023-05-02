package com.bookhub.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

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
}
