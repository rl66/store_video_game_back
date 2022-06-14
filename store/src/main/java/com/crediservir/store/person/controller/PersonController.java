package com.crediservir.store.person.controller;

import com.crediservir.store.person.dto.PersonDto;
import com.crediservir.store.person.entity.Person;
import com.crediservir.store.person.service.PersonService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    private final ModelMapper modelMapper;

    public PersonController(PersonService personService, ModelMapper modelMapper) {
        this.personService = personService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{personId}")
    @ApiOperation("Get person by UUID")
    @ApiResponses({@ApiResponse(code = 200, message = "success")})
    public ResponseEntity<PersonDto> findPersonByUUID(@PathVariable UUID personId){
        return personService.findById(personId).map(person -> new ResponseEntity<>(modelMapper.map(person, PersonDto.class), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/all")
    @ApiOperation("Get all persons")
    @ApiResponses({@ApiResponse(code = 200, message = "success")})
    public ResponseEntity<List<PersonDto>> findAll(){
        List<Person> personList = personService.getAll();
        return new ResponseEntity<>(personList.stream().map(person -> modelMapper.map(person,PersonDto.class))
                .collect(Collectors.toList()),HttpStatus.OK);
    }

    @GetMapping("/persondocument/{personDocument}")
    @ApiOperation("Get person by document")
    @ApiResponses({@ApiResponse(code = 200, message = "success")})
    public ResponseEntity<PersonDto> findPersonByDocument(@PathVariable String personDocument){
        return personService.getPersonByDocument(personDocument).map(person -> new ResponseEntity<>(modelMapper.map(person, PersonDto.class), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save/")
    @ApiOperation("Create person")
    @ApiResponses({@ApiResponse(code = 201, message = "area created"), @ApiResponse(code = 200, message = "area bad request")})
    public ResponseEntity<?> create(@Valid @RequestBody PersonDto personDto) {
        HashMap<String, String> map = new HashMap<>();
        if (personService.exitPersonByDocument(personDto.getPersonDocument())) {
            map.put("message", "Estea persona con este documento ya existe");
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
        Object document = personDto.getPersonDocumentTypeId();
        if (Objects.isNull(document)) {
            map.put("message", "Debe asginar un tipo de documento");
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
        Person person = personService.savePerson(modelMapper.map(personDto, Person.class));
        return new ResponseEntity<>(modelMapper.map(person, PersonDto.class), HttpStatus.CREATED);
    }

    @PutMapping("/update/{personId}")
    @ApiOperation("Update person")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Return the updated person"),
            @ApiResponse(code = 404, message = "Returns the data sent is invalid")
    })
    public ResponseEntity<Map<String, Object>> updatePerson(@RequestBody PersonDto personDto, @PathVariable("personId") UUID personId){
        Map<String, Object> map = new HashMap<>();
        map.put("message","Datos invalidos");
        if(personService.findById(personId).isPresent()){
            map.put("message", modelMapper.map(personService.updatePersonByUuid(personId, modelMapper.map(personDto, Person.class)), Person.class));
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/delete/{personId}")
    @ApiOperation("Delete person by UUID")
    @ApiResponses({@ApiResponse(code = 200, message = "success")})
    public ResponseEntity<HashMap<String, String>> deletePersonByUUID(@PathVariable("personId") UUID personId) {
        Optional<Person> person = personService.findById(personId);
        if (person.isPresent()) {
            personService.deletePersonById(personId);
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

}
