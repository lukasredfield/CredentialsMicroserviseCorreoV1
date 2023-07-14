package com.correoargentino.credentials.controllers;

import com.correoargentino.credentials.exceptions.BadRequestException;
import com.correoargentino.credentials.exceptions.SearchErrorException;
import com.correoargentino.credentials.models.Person;
import com.correoargentino.credentials.services.PersonService;
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
 * Controlador para gestionar las solicitudes relacionadas con las personas.
 */
@Controller
public class PersonController {

    private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private PersonService personService;

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
     * @param document   El DNI de la persona a buscar.
     * @param model El modelo utilizado para agregar atributos a la vista.
     * @return El nombre de la vista "result" si se encuentra la persona, o el nombre de la vista "not-found" si no se encuentra.
     */
    @PostMapping("/search")
    public String search(@RequestParam("document") Long document, Model model) {
        try {
            logger.info("DNI recibido: {}", document);
            Optional<Person> person = personService.getPersonByDocument(document);

            if (person.isPresent()) {
                model.addAttribute("person", person.get());
                return "result";
            } else {
                throw new SearchErrorException("Person not found with document: " + document);
            }
        } catch (Exception e) {
            // Manejar la excepción
            // Puedes mostrar un mensaje de error o redirigir a una página de error
            return "error";
        }
    }


    /**
     * Maneja la solicitud GET para la ruta "/createPerson".
     *
     * @return El nombre de la vista "createPerson".
     */
    @GetMapping("/createPerson")
    public String createPerson() {
        return "createPerson";
    }

    /**
     * Maneja la solicitud POST para la ruta "/savePerson".
     * Guarda una nueva persona.
     *
     * @param document      El DNI del nuevo empleado.
     * @param firstname     El nombre del nuevo empleado.
     * @param lastname El apellido del nuevo empleado.
     * @return El nombre de la vista "index".
     */
    @PostMapping("/savePerson")
    public String savePerson(@RequestParam("document") Long document,
                             @RequestParam("firstname") String firstname,
                             @RequestParam("lastname") String lastname) {
        try {
            Person newPerson = new Person();
            newPerson.setDocument(document);
            newPerson.setFirstName(firstname);
            newPerson.setLastName(lastname);

            personService.savePerson(newPerson);
            return "index";
        } catch (Exception e) {
            // Manejar la excepción
            // Puedes mostrar un mensaje de error o redirigir a una página de error
            return "error";
        }
    }

    /**
     * Maneja la solicitud GET para la ruta "/persons".
     * Retorna una lista de todos las personas.
     *
     * @return Una lista de todos las personas.
     */
    @GetMapping("/persons")
    @ResponseBody
    public List<Person> getAllPersons() {
        try {
            return personService.getAllPersons();
        } catch (Exception e) {
            // Manejar la excepción
            // Lanzar la excepción personalizada
            throw new BadRequestException("Error retrieving all persons: " + e.getMessage());
        }
    }
}
