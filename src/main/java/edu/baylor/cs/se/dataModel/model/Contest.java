package edu.baylor.cs.se.dataModel.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="CONTEST")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        scope=Contest.class)
public class Contest {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private int capacity;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private boolean registration_allowed;

    @Column(nullable = false)
    private Date registration_from;

    @Column(nullable = false)
    private Date registration_to;

    @Column(nullable = false)
    private boolean writable;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "PERSON_CONTEST",
            joinColumns = { @JoinColumn(name = "CONTEST_ID", referencedColumnName = "ID") }, //do not forget referencedColumnName if name is different
            inverseJoinColumns = { @JoinColumn(name = "PERSON_ID", referencedColumnName = "ID") })
    private Set<Person> contestManagers = new HashSet();

    public Set<Person> getContestManagers() {
        return contestManagers;
    }

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "contest")
    private Set<Team> teamsAttending = new HashSet();

    public Set<Team> getTeamsAttending() {
        return teamsAttending;
    }

    @ManyToOne
    private Contest preliminaryRound;

    public Contest getPreliminaryRound() {
        return preliminaryRound;
    }

    public void setPreliminaryRound(Contest preliminaryRound) {
        this.preliminaryRound = preliminaryRound;
    }

    @OneToMany(mappedBy = "preliminaryRound")
    private Set<Contest> subContests = new HashSet<>();

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isWritable() {
        return writable;
    }

    public void setWritable(boolean writable) {
        this.writable = writable;
    }

    public boolean isRegistration_allowed() {
        return registration_allowed;
    }

    public Date getDate() {
        return date;
    }

    public Date getRegistration_to() {
        return registration_to;
    }

    public Date getRegistration_from() {
        return registration_from;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setRegistration_to(Date registration_to) {
        this.registration_to = registration_to;
    }

    public void setRegistration_allowed(boolean registration_allowed) {
        this.registration_allowed = registration_allowed;
    }

    public void setRegistration_from(Date registration_from) {
        this.registration_from = registration_from;
    }

}
