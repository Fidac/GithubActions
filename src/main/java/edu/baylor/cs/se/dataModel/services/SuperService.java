package edu.baylor.cs.se.dataModel.services;

import edu.baylor.cs.se.dataModel.builder.ContestFactory;
import edu.baylor.cs.se.dataModel.builder.PersonFactory;
import edu.baylor.cs.se.dataModel.builder.TeamFactory;
import edu.baylor.cs.se.dataModel.data.ContestRepository;
import edu.baylor.cs.se.dataModel.data.PersonRepository;
import edu.baylor.cs.se.dataModel.data.TeamRepository;
import edu.baylor.cs.se.dataModel.model.Contest;
import edu.baylor.cs.se.dataModel.model.Person;
import edu.baylor.cs.se.dataModel.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SuperService {

    @Autowired
    private ContestRepository contestRepo;
    @Autowired
    private PersonRepository personRepo;
    @Autowired
    private TeamRepository teamRepo;

    public SuperService(ContestRepository contestRepo,
                        PersonRepository personRepo,
                        TeamRepository teamRepo) {
        this.contestRepo = contestRepo;
        this.personRepo = personRepo;
        this.teamRepo = teamRepo;
    }

    public void populate() {

        ContestFactory contestFactory = new ContestFactory();
        TeamFactory teamFactory = new TeamFactory();
        PersonFactory personFactory = new PersonFactory();

        Contest subcontest = contestFactory.makeContest("Local Round Texas", 10, new Date("02/02/2021"),
                new Date("02/04/2021"), new Date("02/05/2021"), true);

        Contest subcontest2 = contestFactory.makeContest("Local Round Texas2", 2, new Date("02/02/2021"),
                new Date("02/04/2021"), new Date("02/05/2021"), true);

        Contest subcontest3 = contestFactory.makeContest("Local Round Texas3", 1, new Date("02/02/2021"),
                new Date("02/04/2021"), new Date("02/05/2021"), true);

        Contest subcontest4 = contestFactory.makeContest("Local Round Texas4", 10, new Date("02/02/2021"),
                new Date("02/04/2021"), new Date("02/05/2021"), true);

        Contest contest = contestFactory.makeContest("National Round USA", 50, new Date("02/08/2021"),
                new Date("02/09/2021"), new Date("02/10/2021"), true);

        Contest notOpencontest = contestFactory.makeContest("NULL", 50, new Date("02/08/2021"),
                new Date("02/09/2021"), new Date("02/10/2021"), false);

        Team team1 = teamFactory.makeTeam("TEAM 1", 3, "Pending");
        Team team2 = teamFactory.makeTeam("TEAM 2", 2, "Canceled");
        Team team3 = teamFactory.makeTeam("TEAM 3", 2, "Canceled");
        Team team4 = teamFactory.makeTeam("TEAM 4", 0, "Accepted");
        Team team5 = teamFactory.makeTeam("TEAM 5", 0, "Accepted");

        Person contestant1 = personFactory.makePerson("Contestant 1", "Baylor University",
                new Date("03/07/1998"), "contestant1@gmail.com", "student");
        Person contestant2 = personFactory.makePerson("Contestant 2", "Baylor University",
                new Date("03/07/1999"), "contestant2@gmail.com", "student");
        Person contestant3 = personFactory.makePerson("Contestant 3", "Baylor University",
                new Date("01/07/2000"), "contestant3@gmail.com", "student");
        Person contestant4 = personFactory.makePerson("Contestant 4", "Baylor University",
                new Date("02/07/1998"), "contestant4@gmail.com", "student");
        Person contestant5 = personFactory.makePerson("Contestant 5", "Baylor University",
                new Date("03/07/1999"), "contestant5@gmail.com", "student");
        Person contestant6 = personFactory.makePerson("Contestant 6", "Baylor University",
                new Date("03/07/2000"), "contestant6@gmail.com", "student");
        Person contestant7 = personFactory.makePerson("Contestant 7", "Baylor University",
                new Date("03/07/1998"), "contestant7@gmail.com", "student");
        Person contestant8 = personFactory.makePerson("Contestant 8", "Baylor University",
                new Date("03/07/1999"), "contestant8@gmail.com", "student");
        Person contestant9 = personFactory.makePerson("Contestant 9", "Baylor University",
                new Date("03/07/1982"), "contestant9@gmail.com", "student");

        Person contestant10 = personFactory.makePerson("Contestant 10", "Baylor University",
                new Date("03/07/1999"), "contestant7@gmail.com", "student");
        Person contestant11 = personFactory.makePerson("Contestant 11", "Baylor University",
                new Date("03/07/1998"), "contestant8@gmail.com", "student");
        Person contestant12 = personFactory.makePerson("Contestant 12", "Baylor University",
                new Date("03/07/1996"), "contestant9@gmail.com", "student");

        Person coach1 = personFactory.makePerson("Coach 1", "Baylor University",
                new Date("03/07/1980"), "coach1@gmail.com", "coach");

        Person coach2 = personFactory.makePerson("Coach 2", "Baylor University",
                new Date("03/04/1980"), "coach2@gmail.com", "coach");

        Person coach3 = personFactory.makePerson("Coach 3", "Baylor University",
                new Date("01/02/1980"), "coach3@gmail.com", "coach");

        Person coach4 = personFactory.makePerson("Coach 4", "Baylor University",
                new Date("01/02/1978"), "coach4@gmail.com", "coach");

        Person contestManager = personFactory.makePerson("Contest Manager", "Baylor University",
                new Date("01/02/1975"), "contestManager@gmail.com", "contestManager");

        team1.getMembers().add(contestant1);
        team1.getMembers().add(contestant2);
        team1.getMembers().add(contestant3);
        team1.setCoach(coach1);
        team1.setId(18l);

        team2.getMembers().add(contestant4);
        team2.getMembers().add(contestant5);
        team2.getMembers().add(contestant6);
        team2.setCoach(coach2);
        team2.setId(20l);

        team3.getMembers().add(contestant7);
        team3.getMembers().add(contestant8);
        team3.getMembers().add(contestant9);
        team3.setCoach(coach3);
        team3.setId(22l);

        team4.getMembers().add(contestant10);
        team4.getMembers().add(contestant11);
        team4.getMembers().add(contestant5); //Repeat Contestant to test validation
        team4.setCoach(coach4);
        team4.setId(24l);

        team5.getMembers().add(contestant10);
        team5.getMembers().add(contestant11);
        team5.getMembers().add(contestant5); //Repeat Contestant to test validation
        team5.setCoach(coach4);
        team5.setId(26l);

        subcontest.getContestManagers().add(contestManager);
        subcontest2.getContestManagers().add(contestManager);
        subcontest3.getContestManagers().add(contestManager);
        subcontest4.getContestManagers().add(contestManager);

        subcontest.setWritable(true);
        subcontest2.setWritable(false);
        subcontest3.setWritable(true);
        subcontest4.setWritable(true);

        contest.getContestManagers().add(contestManager);
        contest.setPreliminaryRound(subcontest);

        team1.setContest(subcontest);
        team2.setContest(subcontest2);
        team3.setContest(subcontest3);
        team4.setContest(subcontest3);
        team5.setContest(subcontest4);

        personRepo.save(contestant1);
        personRepo.save(contestant2);
        personRepo.save(contestant3);
        personRepo.save(contestant4);
        personRepo.save(contestant5);
        personRepo.save(contestant6);
        personRepo.save(contestant7);
        personRepo.save(contestant8);
        personRepo.save(contestant9);
        personRepo.save(contestant10);
        personRepo.save(contestant11);
        personRepo.save(contestant12);

        personRepo.save(coach1);
        personRepo.save(coach2);
        personRepo.save(coach3);
        personRepo.save(coach4);

        personRepo.save(contestManager);

        teamRepo.save(team1);
        teamRepo.save(team2);
        teamRepo.save(team3);
        teamRepo.save(team4);
        teamRepo.save(team5);

        contestRepo.save(subcontest);
        contestRepo.save(subcontest2);
        contestRepo.save(subcontest3);
        contestRepo.save(subcontest4);
        contestRepo.save(contest);
        contestRepo.save(notOpencontest);

    }
}
