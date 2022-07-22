package cl.uchile.dcc.citricliquid.model.state.states.battle.defender.counterattacked;

import cl.uchile.dcc.citricliquid.model.exeptions.InvalidStateOperationException;
import cl.uchile.dcc.citricliquid.model.personaje.AbstractCharacter;
import cl.uchile.dcc.citricliquid.model.state.AbstractState;
import cl.uchile.dcc.citricliquid.model.state.states.turnstate.Inactive;


/**
 * State for beeing attacked and chose to esquivar.
 *
 */
public class CounterEsquivar extends AbstractState {
  public CounterEsquivar(AbstractCharacter context) {
    super(context);
  }

  @Override
  public int attacked(int baseatk) throws InvalidStateOperationException {
    int dice = context.roll();
    System.out.println("Se decidio esquivar");
    if (baseatk < dice + context.getDef()) {
      System.out.println("SE ESQUIVO EL ATAQUE. NO SE RECIBE DANNO");
      context.toInactiveState();
      return 0;
    }
    context.isAttacked(baseatk);
    System.out.println("NO SE ESQUIVA ATAQUE. DANNO RECIBIDO: " + baseatk);
    context.toInactiveState();
    return baseatk;
  }

  //como es un contraataque, la única transición permitida será:
  //pasar al estado Inactivo.

  @Override
  public void toInactiveState() {
    context.setState(new Inactive(context));
  }

  @Override
  public boolean isCounterEsquivar() {
    return true;
  }

}
