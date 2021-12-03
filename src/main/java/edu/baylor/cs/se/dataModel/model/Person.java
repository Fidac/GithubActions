package edu.baylor.cs.se.dataModel.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Formula;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="PERSON")
@EqualsAndHashCode
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        scope=Person.class)
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Date birthdate;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String university;

    @Formula("(YEAR(CURDATE()) - YEAR(BIRTHDATE))")
    private Long age;

    public Person(){

    }

    @ManyToMany(mappedBy = "contestManagers", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Contest> contestsManaged = new HashSet();


    @ManyToMany(mappedBy = "members", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Team> teams = new HashSet();


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "coach")
    private Set<Team> teamsCoached = new HashSet();


    private enum Role{
        student("student"),
        coach("coach"),
        contestManager("contestManager");

        private String role;

        Role(String role){
            this.role = role;
        }

        public String getRole(){
            return role;
        }
    }

    public Long getId() {
        return id;
    }

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    public Person(Person other){
        super();
        this.id = other.id;
        this.university = other.university;
        this.email = other.email;
        this.birthdate = other.birthdate;
        this.age = other.age;
        this.role = other.role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public Long getAge() {
        return age;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = Role.valueOf(role);
    }
}
