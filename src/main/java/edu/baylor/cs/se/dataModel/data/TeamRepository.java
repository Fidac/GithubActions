package edu.baylor.cs.se.dataModel.data;

import java.util.List;

import edu.baylor.cs.se.dataModel.model.Contest;
import edu.baylor.cs.se.dataModel.model.Team;
import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends CrudRepository<Team, Long> {
    Team findByName(String name);
}