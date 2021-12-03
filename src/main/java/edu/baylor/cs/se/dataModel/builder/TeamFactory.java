package edu.baylor.cs.se.dataModel.builder;

import edu.baylor.cs.se.dataModel.model.Team;

public class TeamFactory {

    public Team makeTeam(String name, int rank, String state) {
        Team team = new Team();
        team.setName(name);
        team.setRank(rank);
        team.setState(state);

        return team;
    }

}
