package edu.baylor.cs.se.dataModel.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

@Entity
@Table(name="TEAM")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        scope=Team.class)
public class Team {

    @Id
//    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int rank;

    private enum State{
        Accepted("Accepted"),
        Pending("Pending"),
        Canceled("Canceled");

        private String state;

        State(String state){
            this.state = state;
        }

        public String getState(){
            return state;
        }
    }

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private State state;

    public Team(){

    }

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Person coach;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Contest contest;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="TEAM_ID")
    private Team prevTeamClone;

    @OneToOne(mappedBy="prevTeamClone")
    private Team nextTeamClone;

    public void setPrevTeamClone(Team prevTeamClone) {
        this.prevTeamClone = prevTeamClone;
    }

    public Team(Team other){
        super();
        BeanUtils.copyProperties(other, this);

        for (Person member: other.getMembers()) {
            Person clone = new Person(member);
            this.getMembers().add(clone);
        }
        this.state = other.state;
        this.id = null;
        other.nextTeamClone = this;
        this.prevTeamClone = other;
    }

    public Team clone() { return new Team(this); }

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "PERSON_TEAM",
            joinColumns = { @JoinColumn(name = "TEAM_ID", referencedColumnName = "ID") }, //do not forget referencedColumnName if name is different
            inverseJoinColumns = { @JoinColumn(name = "PERSON_ID", referencedColumnName = "ID") })
    @Size(min=1,max=3)
    private List<Person> members = new ArrayList<>();

    public List<Person> getMembers() {
        return members;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getState() {
        return state.toString();
    }

    public void setState(String state) {
        this.state = State.valueOf(state);
    }

    public Person getCoach() {
        return coach;
    }

    public void setCoach(Person coach) {
        this.coach = coach;
    }

    public Contest getContest() {
        return contest;
    }

    public void setContest(Contest contest) {
        this.contest = contest;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

