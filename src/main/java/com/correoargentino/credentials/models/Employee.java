package com.correoargentino.credentials.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "employee")
/**
 * Clase que representa un empleado.
 */
public class Employee {

    @Id
    @Column(name = "dni")
    private String dni;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

}
