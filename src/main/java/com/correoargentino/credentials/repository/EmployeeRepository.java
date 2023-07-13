package com.correoargentino.credentials.repository;

import com.correoargentino.credentials.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para acceder a los datos de los empleados.
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

}
