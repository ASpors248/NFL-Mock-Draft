package Model;

import java.util.List;

public class Pick{

    private int pickNum;
    private Team team;
    private Prospect prospect;

    public Pick(int pickNum, Team team, Prospect prospect) {
        this.pickNum = pickNum;
        this.team = team;
        this.prospect = null;
    }

    @Override
    public String toString() {
        if (prospect == null) {
            System.exit(1);
        }
        return pickNum + ". " + team + ": " + prospect;
    }
    
    public int getPickNum() {
        return pickNum;
    }

    public void setPickNum(int pickNum) {
        this.pickNum = pickNum;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Prospect getProspect() {
        return prospect;
    }

    public void setProspect(Prospect prospect) {
        this.prospect = prospect;
    }




}
