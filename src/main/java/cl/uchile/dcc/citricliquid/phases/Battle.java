package cl.uchile.dcc.citricliquid.phases;

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

    public void battlePvP(Player player_1, Player player_2) {
        attacker = player_1;
        rival = player_2;
    }

    @Override
    public String toString() {
        return "BattlePhase";
    }
}
