package com.correoargentino.credentials.services;

import com.correoargentino.credentials.models.Person;
import com.correoargentino.credentials.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Clase de servicio para gestionar empleados.
 */
@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    /**
     * Recupera un empleado por su DNI (Documento Nacional de Identidad).
     *
     * @param document El DNI de la persona a recuperar.
     * @return Un Optional que contiene al empleado si se encuentra, o un Optional vacío si no se encuentra.
     */
    public Optional<Person> getPersonByDni(Long document) {
        return personRepository.findById(document);
    }

    /**
     * Recupera una lista de todos los persona.
     *
     * @return Una lista de todos los persona.
     */
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    /**
     * Guarda un persona.
     *
     * @param person El empleado a guardar.
     * @return El persona guardado.
     */
    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

}
