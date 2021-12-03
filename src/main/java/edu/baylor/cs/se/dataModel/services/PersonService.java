package edu.baylor.cs.se.dataModel.services;

import java.util.List;

import edu.baylor.cs.se.dataModel.DTOs.StudentAgeGroup;
import edu.baylor.cs.se.dataModel.data.ContestRepository;
import edu.baylor.cs.se.dataModel.data.PersonRepository;
import edu.baylor.cs.se.dataModel.data.TeamRepository;
import edu.baylor.cs.se.dataModel.model.Contest;
import edu.baylor.cs.se.dataModel.model.Person;
import edu.baylor.cs.se.dataModel.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepo;

    public PersonService(PersonRepository personRepo) {
        this.personRepo = personRepo;
    }

    public Iterable<Person> getPersons() {
        return personRepo.findAll();
    }

    public List<StudentAgeGroup> getStudentsGroupByAge() {
        return personRepo.groupByAge();
    }
}
