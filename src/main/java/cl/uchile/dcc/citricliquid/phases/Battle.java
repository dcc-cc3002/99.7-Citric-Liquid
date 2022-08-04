package cl.uchile.dcc.citricliquid.phases;

import cl.uchile.dcc.citricliquid.controller.GameController;
import cl.uchile.dcc.citricliquid.model.unit.Player;
import cl.uchile.dcc.citricliquid.model.unit.Unit;

public class Battle extends Phase {
    Unit attacker;
    Unit rival;

    public Battle(Unit attacker, Unit rival) {
        this.canStart = false;
        this.canMove = false;
        this.canFight = true;
        this.stayHome = false;
        this.recover = false;
        this.waitHome = false;
        this.waitFight = true;
        this.waitPath = false;
        this.canEnd = false;
        this.battle = true;
    }


    @Override
    public String toString() {
        return "BattlePhase";
    }

    @Override
    public void setController(GameController controller) {
        super.setController(controller);
    }

    @Override
    public void changePhase(Phase phase) {
        super.changePhase(phase);
    }

    public void battle(Unit attacker, Unit rival) {
        GameController control = super.controller;
        control.battle(attacker, rival);
    }
}
