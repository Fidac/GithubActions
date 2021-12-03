package edu.baylor.cs.se.dataModel.controllers;

import edu.baylor.cs.se.dataModel.DTOs.OperationOutcome;
import edu.baylor.cs.se.dataModel.model.Contest;
import edu.baylor.cs.se.dataModel.model.Team;
import edu.baylor.cs.se.dataModel.services.ContestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class ContestController {

    private ContestService contestService;

    @Autowired
    public ContestController(ContestService contestService) {
        this.contestService = contestService;
    }

    @RequestMapping(value = "/contest", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Contest>> getContests() {
        Iterable<Contest> contests = contestService.getContests();
        return new ResponseEntity(contests, HttpStatus.OK);
    }

    @RequestMapping(value = "/contest/{id}", method = RequestMethod.GET)
    public ResponseEntity<Contest> getContestById(@PathVariable("id") Long id) {
        Contest contest = contestService.getContestById(id);
        return new ResponseEntity(contest, HttpStatus.OK);
    }

    @RequestMapping(value = "/contest/occupancy/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> getOccupancyByContestName(@PathVariable(value = "id") Long id) {
        Integer occupancy = contestService.getContestOccupancy(id);
        Integer capacity = contestService.getContestCapacity(id);

        if (occupancy > capacity) {
            System.out.println("Error: Occupancy greater than capacity");
        }

        return new ResponseEntity("The occupancy is " + occupancy + " and the capacity " + capacity, HttpStatus.OK);
    }

    @PostMapping(value = "/contest/register/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<OperationOutcome> contestRegistration(@PathVariable("id") Long id, @RequestBody Team team) {
        OperationOutcome outcome = contestService.register(id, team);
        return new ResponseEntity(outcome, HttpStatus.OK);

    }

    @RequestMapping(value = "/contest/setEditable/{id}", method = RequestMethod.GET)
    public ResponseEntity<Contest> contestSetEditable(@PathVariable("id") Long id) {
        Contest contest = contestService.setEditable(id);
        return new ResponseEntity(contest, HttpStatus.OK);
    }

    @RequestMapping(value = "/contest/setReadOnly/{id}", method = RequestMethod.GET)
    public ResponseEntity<Contest> contestSetReadOnly(@PathVariable("id") Long id) {
        Contest contest = contestService.setReadOnly(id);
        return new ResponseEntity(contest, HttpStatus.OK);
    }

    @PostMapping(value = "/contest/edit", consumes = "application/json", produces = "application/json")
    public ResponseEntity<OperationOutcome> contestEdit(@RequestBody Contest contest) {
        OperationOutcome outcome = contestService.edit(contest);
        return new ResponseEntity(outcome, HttpStatus.OK);
    }
}
