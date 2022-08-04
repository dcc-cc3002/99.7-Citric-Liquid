package cl.uchile.dcc.citricliquid.phases;

public class TurnEndPhase extends Phase{
    public TurnEndPhase(){
        this.canStart = true;
        this.canMove = false;
        this.canFight = false;
        this.stayHome = false;
        this.recover = false;
        this.waitHome = false;
        this.waitFight = false;
        this.waitPath = false;
        this.canEnd = true;
        this.battle = false;
    }

    @Override
    public String toString() {
        return "TurnEndPhase";
    }
}
