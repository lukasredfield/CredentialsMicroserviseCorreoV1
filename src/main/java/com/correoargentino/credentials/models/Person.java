package com.correoargentino.credentials.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "person")
/**
 * Clase que representa un empleado.
 */
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "document")
    private Long document;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

}
