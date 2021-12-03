package edu.baylor.cs.se.dataModel.controllers;

import edu.baylor.cs.se.dataModel.DTOs.StudentAgeGroup;
import edu.baylor.cs.se.dataModel.model.Contest;
import edu.baylor.cs.se.dataModel.model.Person;
import edu.baylor.cs.se.dataModel.model.Team;
import edu.baylor.cs.se.dataModel.services.ContestService;
import edu.baylor.cs.se.dataModel.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.List;

@RestController
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @RequestMapping(value = "/person", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Person>> getPersons() {
        Iterable<Person> persons = personService.getPersons();
        return new ResponseEntity(persons, HttpStatus.OK);
    }

    @RequestMapping(value = "/studentsbyAge", method = RequestMethod.GET)
    public ResponseEntity<List<StudentAgeGroup>> getStudentsGroupByAge() {
        List<StudentAgeGroup> studentAgeGroups = personService.getStudentsGroupByAge();
        return new ResponseEntity(studentAgeGroups, HttpStatus.OK);
    }
}
