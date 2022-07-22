package cl.uchile.dcc.citricliquid.model.state.states.battle.defender.counterattacked;

import cl.uchile.dcc.citricliquid.model.personaje.AbstractCharacter;
import cl.uchile.dcc.citricliquid.model.state.AbstractState;

/**
 * the player counterAttack.
 *
 */
public class CounterAttacked extends AbstractState {
  public CounterAttacked(AbstractCharacter context) {
    super(context);
  }


  @Override
  public void toCounterAttackedDefender() {
    context.setState(new CounterDefender(context));
  }

  @Override
  public void toCounterAttackedEsquivar() {
    context.setState(new CounterEsquivar(context));
  }

  @Override
  public boolean isCounterAttacked() {
    return true;
  }

}
