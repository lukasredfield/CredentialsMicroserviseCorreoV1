package com.correoargentino.credentials.controllers;

import com.correoargentino.credentials.models.Employee;
import com.correoargentino.credentials.services.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

/**
 * Controlador para gestionar las solicitudes relacionadas con los empleados.
 */
@Controller
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    /**
     * Maneja la solicitud GET para la ruta raíz ("/").
     *
     * @return El nombre de la vista "index".
     */
    @GetMapping("/")
    public String index() {
        return "index";
    }

    /**
     * Maneja la solicitud POST para la ruta "/search".
     * Realiza una búsqueda de empleado por DNI.
     *
     * @param dni   El DNI del empleado a buscar.
     * @param model El modelo utilizado para agregar atributos a la vista.
     * @return El nombre de la vista "result" si se encuentra el empleado, o el nombre de la vista "not-found" si no se encuentra.
     */
    @PostMapping("/search")
    public String search(@RequestParam("dni") String dni, Model model) {

        logger.info("DNI recibido: {}", dni);

        Optional<Employee> employee = employeeService.getEmployeeByDni(dni);

        if (employee.isPresent()) {
            model.addAttribute("employee", employee.get());
            return "result";
        } else {
            return "not-found";
        }
    }

    /**
     * Maneja la solicitud GET para la ruta "/createEmployee".
     *
     * @return El nombre de la vista "createEmployee".
     */
    @GetMapping("/createEmployee")
    public String createEmployee() {
        return "createEmployee";
    }

    /**
     * Maneja la solicitud POST para la ruta "/saveEmployee".
     * Guarda un nuevo empleado.
     *
     * @param dni      El DNI del nuevo empleado.
     * @param name     El nombre del nuevo empleado.
     * @param lastname El apellido del nuevo empleado.
     * @return El nombre de la vista "index".
     */
    @PostMapping("/saveEmployee")
    public String saveEmployee(@RequestParam("dni") String dni,
                               @RequestParam("name") String name,
                               @RequestParam("lastname") String lastname) {
        Employee newEmployee = new Employee();
        newEmployee.setDni(dni);
        newEmployee.setName(name);
        newEmployee.setLastName(lastname);

        employeeService.saveEmployee(newEmployee);
        return "index";
    }

    /**
     * Maneja la solicitud GET para la ruta "/employees".
     * Retorna una lista de todos los empleados.
     *
     * @return Una lista de todos los empleados.
     */
    @GetMapping("/employees")
    @ResponseBody
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
}
