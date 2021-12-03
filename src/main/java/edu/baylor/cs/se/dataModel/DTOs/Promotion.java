package edu.baylor.cs.se.dataModel.DTOs;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
@AllArgsConstructor
public class Promotion {

    private Long superContestId;
    private Long teamId;

    public Long getSuperContestId() {
        return superContestId;
    }

    public Long getTeamId() {
        return teamId;
    }
}
