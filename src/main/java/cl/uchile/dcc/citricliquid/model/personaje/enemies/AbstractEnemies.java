package cl.uchile.dcc.citricliquid.model.personaje.enemies;

import cl.uchile.dcc.citricliquid.model.exeptions.InvalidStateOperationException;
import cl.uchile.dcc.citricliquid.model.personaje.AbstractCharacter;
import cl.uchile.dcc.citricliquid.model.state.states.battle.defender.attacked.AttackedState;

/**
 * An Enemy is a character that is never controlled by a Player.
 *
 */
public abstract class AbstractEnemies extends AbstractCharacter {

  protected AbstractEnemies(String name, int atk, int def, int evd, int maxHp) {
    super(name, atk, def, evd, maxHp);
  }

  @Override
  public void toAttackedState() throws InvalidStateOperationException {
    setState(new AttackedState(this));  //como no hay input se pasa directamente al estado.
    int rand = randomGenerator.nextInt(2) + 1;
    if (rand == 1) {
      state.toAttackedDefender();
    }
    if (rand == 2) {
      state.toAttackedEsquivar();
    }
  }

}
