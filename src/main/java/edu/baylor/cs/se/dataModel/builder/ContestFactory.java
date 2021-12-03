package edu.baylor.cs.se.dataModel.builder;

import edu.baylor.cs.se.dataModel.model.Contest;

import java.util.Date;

public class ContestFactory {

    public Contest makeContest(String name, int capacity, Date startReg, Date endReg, Date date, boolean regAllow) {
        Contest contest = new Contest();
        contest.setName(name);
        contest.setCapacity(capacity);
        contest.setRegistration_from(startReg);
        contest.setRegistration_to(endReg);
        contest.setDate(date);
        contest.setRegistration_allowed(regAllow);

        return contest;
    }
}
