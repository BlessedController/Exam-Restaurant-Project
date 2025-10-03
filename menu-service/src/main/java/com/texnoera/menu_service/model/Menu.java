package com.texnoera.menu_service.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

@Entity
@FieldDefaults(level = PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "menu")
@Getter
@Setter
@Builder
public class Menu {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;

    @ManyToMany(fetch = LAZY, cascade = PERSIST)
    @JoinTable(
            name = "menu_meals",
            joinColumns = @JoinColumn(name = "menu_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "meal_id", referencedColumnName = "id")
    )
    List<Meal> meals;

    Long restaurantId;

}
