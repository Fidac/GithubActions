package edu.baylor.cs.se.dataModel.data;

import edu.baylor.cs.se.dataModel.model.Contest;
import org.springframework.data.repository.CrudRepository;

public interface ContestRepository extends CrudRepository<Contest, Long> {
    Contest findByName(String name);
}