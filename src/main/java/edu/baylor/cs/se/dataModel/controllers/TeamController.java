package edu.baylor.cs.se.dataModel.controllers;

import java.util.List;
import java.util.Set;

import edu.baylor.cs.se.dataModel.DTOs.OperationOutcome;
import edu.baylor.cs.se.dataModel.DTOs.Promotion;
import edu.baylor.cs.se.dataModel.model.*;
import edu.baylor.cs.se.dataModel.services.ContestService;
import edu.baylor.cs.se.dataModel.services.SuperService;
import edu.baylor.cs.se.dataModel.services.TeamService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class TeamController {

    private TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @RequestMapping(value = "/team", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Team>> getTeams() {
        Iterable<Team> teams = teamService.getTeams();
        return new ResponseEntity(teams, HttpStatus.OK);
    }

    @RequestMapping(value = "/team/{id}", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Team>> getTeamById(@PathVariable("id") Long id) {
        Team team = teamService.getTeamById(id);
        return new ResponseEntity(team, HttpStatus.OK);
    }

    @PostMapping(value = "/team/edit", consumes = "application/json", produces = "application/json")
    public void teamEdit(@RequestBody Team team) {
        teamService.edit(team);
    }

    @PostMapping(value = "/team/promote", consumes = "application/json", produces = "application/json")
    public ResponseEntity<OperationOutcome> promote(@RequestBody Promotion promotion) {
        OperationOutcome outcome = teamService.promote(promotion.getSuperContestId(), promotion.getTeamId());
        return new ResponseEntity<>(outcome, HttpStatus.OK);
    }
}
