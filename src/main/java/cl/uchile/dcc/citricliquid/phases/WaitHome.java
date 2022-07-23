package cl.uchile.dcc.citricliquid.phases;

import cl.uchile.dcc.citricliquid.controller.GameController;

public class WaitHome extends Phase {

    public WaitHome() {
        this.canStart = false;
        this.canMove = false;
        this.canFight = false;
        this.stayHome = false;
        this.recover = false;
        this.waitHome = true;
        this.waitFight = false;
        this.waitPath = false;
        this.canEnd = false;
        this.battle = false;
    }

    @Override
    public String toString() {
        return "WaitHomePhase";
    }

    @Override
    public void setController(GameController controller) {
        super.setController(controller);
    }

    @Override
    public void changePhase(Phase phase) {
        super.changePhase(phase);
    }
}
