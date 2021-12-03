package edu.baylor.cs.se.dataModel.data;

import java.util.List;

import edu.baylor.cs.se.dataModel.DTOs.StudentAgeGroup;
import edu.baylor.cs.se.dataModel.model.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface PersonRepository extends CrudRepository<Person, Long> {
    @Query("select new edu.baylor.cs.se.dataModel.DTOs.StudentAgeGroup(p.age as age2, " + "count(p) as count2) "
            + "from Person p " + "where p.role = 'student' " + " group by p.age")
    List<StudentAgeGroup> groupByAge();
}
