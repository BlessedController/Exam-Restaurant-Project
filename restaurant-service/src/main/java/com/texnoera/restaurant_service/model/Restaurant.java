package com.texnoera.restaurant_service.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

@Entity
@FieldDefaults(level = PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "restaurants")
@Getter
@Setter
@Builder
public class Restaurant implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;

    String name;

    String address;
}
