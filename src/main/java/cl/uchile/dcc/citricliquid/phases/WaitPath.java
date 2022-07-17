package cl.uchile.dcc.citricliquid.phases;

public class WaitPath extends Phase{

    public WaitPath() {
        this.canStart = false;
        this.canMove = false;
        this.canFight = false;
        this.stayHome = false;
        this.recover = false;
        this.waitHome = false;
        this.waitFight = false;
        this.waitPath = true;
        this.canEnd = false;
        this.battle = false;
    }

    @Override
    public String toString() {
        return "WaitPathPhase";
    }
}
