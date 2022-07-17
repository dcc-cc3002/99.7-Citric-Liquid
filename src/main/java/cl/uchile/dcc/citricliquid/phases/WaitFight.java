package cl.uchile.dcc.citricliquid.phases;

public class WaitFight extends Phase{

    public WaitFight() {
        this.canStart = false;
        this.canMove = false;
        this.canFight = false;
        this.stayHome = false;
        this.recover = false;
        this.waitHome = false;
        this.waitFight = true;
        this.waitPath = false;
        this.canEnd = false;
        this.battle = false;
    }

    @Override
    public String toString() {
        return "WaitFightPhase";
    }
}
