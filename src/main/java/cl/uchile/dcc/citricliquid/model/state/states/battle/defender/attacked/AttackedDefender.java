package cl.uchile.dcc.citricliquid.model.state.states.battle.defender.attacked;

import cl.uchile.dcc.citricliquid.model.personaje.AbstractCharacter;
import cl.uchile.dcc.citricliquid.model.state.AbstractState;
import cl.uchile.dcc.citricliquid.model.state.states.battle.atacar.CounterAttack;
import cl.uchile.dcc.citricliquid.model.state.states.turnstate.Inactive;


/**
 * State for beeing attacked and chose to Defender.
 *
 */
public class AttackedDefender extends AbstractState {
  public AttackedDefender(AbstractCharacter context)  {
    super(context);
  }

  @Override
  public int attacked(int baseatk) {
    int dice = context.roll();
    int def = context.getDef();
    int res = dice + def;
    int des = Math.max(1, baseatk - res);
    System.out.println("Se decidió defender");
    context.isAttacked(des);
    System.out.println("ATAQUE RECIBIDO: "
        + baseatk + " EL ATAQUE EFECTIVO: " + des);
    return des;
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
  public boolean isAttackedDefender() {
    return true;
  }

}
