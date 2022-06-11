package com.crediservir.store.person.service;


import com.crediservir.store.person.entity.Person;
import com.crediservir.store.person.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getAll(){
        return personRepository.findAll();
    }

    public Optional<Person> findById(UUID personId){
        return personRepository.findById(personId);
    }

    public Person findByUUID(UUID personId){
        return personRepository.findByPersonId(personId);
    }

    public Boolean exitPersonByDocument(String personDocument){
       return personRepository.existsByPersonDocument(personDocument);
    }

    public Optional<Person> getPersonByDocument(String personDocument){
        return personRepository.getByPersonDocument(personDocument);
    }

    public Person savePerson(Person person){
        return personRepository.save(person);
    }

    public Person updatePersonByUuid(UUID personId, Person person){
        return personRepository.findById(personId).map(person1 -> {
            person1.setPersonDocument((person.getPersonDocument() !=null )? person.getPersonDocument() : person1.getPersonDocument());
            person1.setPersonName((person.getPersonName() !=null )? person.getPersonName() : person1.getPersonName());
            person1.setPersonLastName((person.getPersonLastName() !=null )? person.getPersonLastName() : person1.getPersonLastName());
            person1.setPersonAddress((person.getPersonAddress() !=null )? person.getPersonAddress() : person1.getPersonAddress());
            person1.setPersonEmail((person.getPersonEmail() !=null )? person.getPersonEmail() : person1.getPersonEmail());
            person1.setPersonPhone((person.getPersonPhone() !=null)? person.getPersonPhone() : person1.getPersonPhone());
            return personRepository.save(person1);
        }).orElse(null);
    }

    public void deletePersonById(UUID personId){
        personRepository.deleteById(personId);
    }
}
