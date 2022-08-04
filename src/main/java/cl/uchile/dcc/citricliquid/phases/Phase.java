package cl.uchile.dcc.citricliquid.phases;

import cl.uchile.dcc.citricliquid.controller.GameController;

public class Phase {
    protected GameController controller;
    public boolean battle;
    public boolean recover;
    public boolean stayHome;
    public boolean waitFight;
    public boolean waitHome;
    public boolean waitPath;
    public boolean canStart;
    public boolean canMove;
    public boolean canFight;
    public boolean canEnd;

    public void setController(GameController controller) {
        this.controller = controller;
    }

    public void changePhase(Phase phase) {
        controller.changePhase(phase);
    }

    @Override
    public String toString() {
        return "Phase";
    }
}
