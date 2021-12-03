package edu.baylor.cs.se.dataModel.builder;

import edu.baylor.cs.se.dataModel.model.Person;

import java.util.Date;

public class PersonFactory {

    public Person makePerson(String name, String university, Date birthdate, String email, String role) {
        Person person = new Person();
        person.setName(name);
        person.setUniversity(university);
        person.setBirthdate(birthdate);
        person.setEmail(email);
        person.setRole(role);

        return person;
    }
}
