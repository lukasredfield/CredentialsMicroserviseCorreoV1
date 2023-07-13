package com.correoargentino.credentials.services;

import com.correoargentino.credentials.repository.EmployeeRepository;
import com.correoargentino.credentials.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Clase de servicio para gestionar empleados.
 */
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * Recupera un empleado por su DNI (Documento Nacional de Identidad).
     *
     * @param dni El DNI del empleado a recuperar.
     * @return Un Optional que contiene al empleado si se encuentra, o un Optional vacío si no se encuentra.
     */
    public Optional<Employee> getEmployeeByDni(String dni) {
        return employeeRepository.findById(dni);
    }

    /**
     * Recupera una lista de todos los empleados.
     *
     * @return Una lista de todos los empleados.
     */
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    /**
     * Guarda un empleado.
     *
     * @param employee El empleado a guardar.
     * @return El empleado guardado.
     */
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

}
