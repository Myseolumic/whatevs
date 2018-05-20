package common;

import battle.Battle;

public class NextTurnData {
    private Battle battle;
    private boolean isVisited;

    public NextTurnData(Battle battle, boolean isVisited){
        this.battle = battle;
        this.isVisited = isVisited;
    }

    public void setBattle(Battle battle){
        this.battle = battle;
    }

    public Battle getBattle(){
        return battle;
    }

    public boolean isNextVisited(){
        return isVisited;
    }
}
