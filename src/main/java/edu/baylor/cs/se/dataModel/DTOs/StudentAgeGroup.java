package edu.baylor.cs.se.dataModel.DTOs;

import lombok.Data;

@Data
public class StudentAgeGroup {
    private Long count;
    private Long age;

    public StudentAgeGroup(Long age, Long count) {
        this.age = age;
        this.count = count;
    }
}