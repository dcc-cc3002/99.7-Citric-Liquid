package cl.uchile.dcc.citricliquid.phases;

import cl.uchile.dcc.citricliquid.controller.GameController;

public class RecoveryPhase extends Phase{

    public RecoveryPhase() {
        this.canStart = true;
        this.canMove = false;
        this.canFight = false;
        this.stayHome = false;
        this.recover = true;
        this.waitHome = false;
        this.waitFight = false;
        this.waitPath = false;
        this.canEnd = false;
        this.battle = false;
    }

    @Override
    public String toString() {
        return "RecoveryPhase";
    }

    @Override
    public void setController(GameController controller) {
        super.setController(controller);
    }
}
