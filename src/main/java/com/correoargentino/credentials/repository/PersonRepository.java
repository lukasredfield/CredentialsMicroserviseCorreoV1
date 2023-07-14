package com.correoargentino.credentials.repository;


import com.correoargentino.credentials.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para acceder a los datos de las personas.
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
