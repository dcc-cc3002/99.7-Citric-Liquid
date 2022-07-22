package cl.uchile.dcc.citricliquid.model.state.states.battle.defender.attacked;

import cl.uchile.dcc.citricliquid.model.personaje.AbstractCharacter;
import cl.uchile.dcc.citricliquid.model.state.AbstractState;
import cl.uchile.dcc.citricliquid.model.state.states.battle.atacar.CounterAttack;
import cl.uchile.dcc.citricliquid.model.state.states.turnstate.Inactive;

/**
 * State for beeing attacked and chose to esquivar.
 *
 */
public class AttackedEsquivar extends AbstractState {
  public AttackedEsquivar(AbstractCharacter context) {
    super(context);
  }

  @Override
  public int attacked(int baseatk) {
    int dice = context.roll();
    System.out.println("Se decidió esquivar");
    if (baseatk < dice + context.getDef()) {
      System.out.println("SE ESQUIVO EL ATAQUE. NO SE RECIBE DANNO");
      return 0;
    }
    context.isAttacked(baseatk);
    System.out.println("NO SE ESQUIVA ATAQUE. DANNO RECIBIDO: " + baseatk);
    return baseatk;
  }

  //Si un personaje es atacado tiene dos transiciones posibles:
  //Pasa a CounterAttack, en caso de tener hp>0.
  //Pasa a Inactive en caso de tener hp==0;
  @Override
  public void toCounterAttackState() {
    if (context.getCurrentHp() != 0) {
      context.setState(new CounterAttack(context));
    }

  }

  @Override
  public void toInactiveState() {
    if (context.getCurrentHp() == 0) {
      context.setState(new Inactive(context));
    }
  }

  @Override
  public boolean isAttackedEsquivar() {
    return true;
  }

}
