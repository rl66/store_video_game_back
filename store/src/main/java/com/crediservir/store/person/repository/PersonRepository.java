package com.crediservir.store.person.repository;

import com.crediservir.store.person.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PersonRepository extends JpaRepository<Person, UUID> {

    Optional<Person> getByPersonDocument(String personDocument);

    Person findByPersonId(UUID personId);

    Boolean existsByPersonDocument(String personDocument);

}
