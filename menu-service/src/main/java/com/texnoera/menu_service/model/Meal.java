package com.texnoera.menu_service.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

@Entity
@FieldDefaults(level = PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "meals")
@Getter
@Setter
@Builder
public class Meal {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;

    String name;

    BigDecimal price;

    @ManyToMany(mappedBy = "meals", fetch = LAZY)
    List<Menu> menu;

}
