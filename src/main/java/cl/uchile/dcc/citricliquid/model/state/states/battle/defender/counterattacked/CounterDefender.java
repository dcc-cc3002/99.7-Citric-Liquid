package cl.uchile.dcc.citricliquid.model.state.states.battle.defender.counterattacked;

import cl.uchile.dcc.citricliquid.model.exeptions.InvalidStateOperationException;
import cl.uchile.dcc.citricliquid.model.personaje.AbstractCharacter;
import cl.uchile.dcc.citricliquid.model.state.AbstractState;
import cl.uchile.dcc.citricliquid.model.state.states.turnstate.Inactive;

/**
 * State for beeing attacked and chose to Defender.
 *
 */
public class CounterDefender extends AbstractState {
  public CounterDefender(AbstractCharacter context) {
    super(context);
  }

  @Override
  public int attacked(int baseatk) throws InvalidStateOperationException {
    int dice = context.roll();
    int def = context.getDef();
    int res = dice + def;
    int des = Math.max(1, baseatk - res);
    System.out.println("Se decidio defender");
    context.isAttacked(des);
    System.out.println("ATAQUE RECIBIDO: "
        + baseatk + " EL ATAQUE EFECTIVO: " + des);
    context.toInactiveState();
    return des;
  }

  //como es un contraataque, la única transición permitida será:
  //pasar al estado Inactivo.

  @Override
  public void toInactiveState() {
    context.setState(new Inactive(context));
  }

  @Override
  public boolean isCounterDefender() {
    return true;
  }

}
