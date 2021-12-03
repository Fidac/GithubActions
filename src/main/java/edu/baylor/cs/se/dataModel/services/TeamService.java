package edu.baylor.cs.se.dataModel.services;

import edu.baylor.cs.se.dataModel.DTOs.OperationOutcome;
import edu.baylor.cs.se.dataModel.data.ContestRepository;
import edu.baylor.cs.se.dataModel.data.TeamRepository;
import edu.baylor.cs.se.dataModel.model.Contest;
import edu.baylor.cs.se.dataModel.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {

    @Autowired
    private ContestService contestService;
    @Autowired
    private ContestRepository contestRepo;
    @Autowired
    private TeamRepository teamRepo;

    public TeamService(ContestService contestService,
                       ContestRepository contestRepo,
                       TeamRepository teamRepo) {
        this.contestRepo = contestRepo;
        this.teamRepo = teamRepo;
        this.contestService = contestService;
    }

    public Iterable<Team> getTeams() {
        return teamRepo.findAll();
    }

    public Team getTeamById(Long id) {
        return teamRepo.findById(id).get();
    }

    public void edit(Team team) {
        teamRepo.save(team);
    }

    public OperationOutcome promote(Long superContestId, Long teamId) {
        Team team = teamRepo.findById(teamId).get();

        if (team.getRank() < 1 || team.getRank() > 5) {
            return new OperationOutcome(false, "Team doesn't have the proper rank");
        }
        Team cloneTeam = new Team(team);

        OperationOutcome registerOutcome = contestService.register(superContestId, cloneTeam);
        return registerOutcome;
    }

    public String receiveMessageOfUpdateStatus(String teamStatus){

        Long teamId = Long.parseLong(teamStatus.split(",")[0]);
        String status = teamStatus.split(",")[1];
        Team team = teamRepo.findById(teamId).get();
        Contest contest = team.getContest();
        Integer occupancy = contest.getTeamsAttending().size();
        Integer capacity = contest.getCapacity();

        String message = "";

        if(!contest.isWritable()){
            message = "Contest associated to team " + team.getId() + " is not writable";
            return message;
        }

        if(team.getState().equals("Canceled") && occupancy == capacity && !status.equals("Canceled")){
            message = "Cannot change the Canceled status of the team since there is not more capacity in the contest";
            return message;
        }

        if(status.equals("Canceled") && !team.getState().equals("Canceled")){
            occupancy -= 1;
        }

//        message += "Accepted Teams in contest " + contest.getName() + ":";
//        message += contest.getTeamsAttending().stream().filter(t -> t.getState() == "Accepted").count();
//        message += "\n";
//
//        message += "Pending Teams in contest " + contest.getName() + ":";
//        message += contest.getTeamsAttending().stream().filter(t -> t.getState() == "Pending").count();
//        message += "\n";

        message += occupancy + "/" + capacity;

        team.setState(status);
        teamRepo.save(team);

        return message;
    }
}
