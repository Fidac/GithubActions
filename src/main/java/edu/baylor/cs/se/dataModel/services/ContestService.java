package edu.baylor.cs.se.dataModel.services;

import edu.baylor.cs.se.dataModel.DTOs.OperationOutcome;
import edu.baylor.cs.se.dataModel.data.ContestRepository;
import edu.baylor.cs.se.dataModel.data.TeamRepository;
import edu.baylor.cs.se.dataModel.model.Contest;
import edu.baylor.cs.se.dataModel.model.Person;
import edu.baylor.cs.se.dataModel.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.List;

@Service
public class ContestService {

    @Autowired
    private ContestRepository contestRepo;

    @Autowired
    private TeamRepository teamRepo;

    public ContestService(ContestRepository contestRepo,
                          TeamRepository teamRepo) {
        this.contestRepo = contestRepo;
        this.teamRepo = teamRepo;
    }

    public Iterable<Contest> getContests() {
        return contestRepo.findAll();
    }

    public Contest getContestById(Long id) {
        return contestRepo.findById(id).get();
    }

    public Integer getContestOccupancy(Long contestId) {
        Contest contest = contestRepo.findById(contestId).get();
        Set<Team> contestTeams = contest.getTeamsAttending();
        return contestTeams.size();
    }

    public Integer getContestCapacity(Long contestId) {
        Contest contest = contestRepo.findById(contestId).get();
        return contest.getCapacity();
    }

    public Contest setEditable(Long contestId) {
        Contest contest = contestRepo.findById(contestId).get();
        contest.setWritable(true);
        contestRepo.save(contest);
        return contest;
    }

    public Contest setReadOnly(Long contestId) {
        Contest contest = contestRepo.findById(contestId).get();
        contest.setWritable(false);
        contestRepo.save(contest);
        return contest;
    }

    public OperationOutcome edit(Contest contest) {
        Contest original = contestRepo.findById(contest.getId()).get();

        if (original.isWritable()) {

            original.setName(contest.getName());
            original.setDate(contest.getDate());
            original.setPreliminaryRound(contest.getPreliminaryRound());
            original.setCapacity(contest.getCapacity());
            original.setRegistration_allowed(contest.isRegistration_allowed());
            original.setRegistration_to(contest.getRegistration_to());
            original.setRegistration_from(contest.getRegistration_from());

            contestRepo.save(original);
            return new OperationOutcome(true, "");
        }
        return new OperationOutcome(false, "The contest is not writable");
    }

    public OperationOutcome register(Long contestId, Team team) {
        Contest contest = contestRepo.findById(contestId).get();

        OperationOutcome outcome = checkValidTeam(team);
        if (!outcome.isSuccess()) {
            return outcome;
        } else if (getContestOccupancy(contest.getId()) + 1 > contest.getCapacity()) {
            return new OperationOutcome(false, "The contest is full in capacity");
        } else if (!checkMemberAreNotInOtherTeam(contest, team)) {
            return new OperationOutcome(false, "There is a member that belongs to another team in the contest");
        } else if (!contest.isRegistration_allowed()) {
            return new OperationOutcome(false, "Contest is not open to registration");
        } else {
            team.setContest(contest);
            teamRepo.save(team);
            return new OperationOutcome(true, "");
        }
    }

    private boolean checkMemberAreNotInOtherTeam(Contest contest, Team registerTeam) {
        List<Person> registerMembers = registerTeam.getMembers();
        Set<Team> teams = contest.getTeamsAttending();

        for (Person member : registerMembers) {
            for (Team team : teams) {
                if (team.getId() == registerTeam.getId()) {
                    continue;
                }

                List<Person> teamMembers = team.getMembers();
                if (teamMembers.stream().anyMatch(m -> m.getId() == member.getId())) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkValidStudents(List<Person> members) {
        return members.stream().allMatch(s -> s.getAge() < 24);
    }

    private OperationOutcome checkValidTeam(Team team) {
        List<Person> members = team.getMembers();

        if (!checkValidStudents(members)) {
            return new OperationOutcome(false, "Exist at least a student with age greater or equal than 24");
        }

        Person coach = team.getCoach();

        if (coach == null) {
            return new OperationOutcome(false, "The coach of the team is null");
        }

        if (new HashSet<Person>(members).size() != members.size()) {
            return new OperationOutcome(false, "There are at least two members that are the same");
        }

        return new OperationOutcome(true, "");
    }
}
