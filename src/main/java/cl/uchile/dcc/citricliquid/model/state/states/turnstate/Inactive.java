package cl.uchile.dcc.citricliquid.model.state.states.turnstate;

import cl.uchile.dcc.citricliquid.model.personaje.AbstractCharacter;
import cl.uchile.dcc.citricliquid.model.state.AbstractState;
import cl.uchile.dcc.citricliquid.model.state.states.battle.defender.attacked.AttackedState;

/**
 * class that is the player state while is not his turn to play.
 *
 */
public class Inactive extends AbstractState {

  public Inactive(AbstractCharacter context) {
    super(context);
  }

  //Sus posibles transiciones va a ser a:
  //un estado de defensa (si algún jugador inició un ataque en su contra)
  //Estado Active, en caso de iniciar su turno.
  @Override
  public void toAttackedState() {
    context.setState(new AttackedState(context));
  }

  @Override
  public void toActiveState() {
    context.setState(new Active(context));
  }

  @Override
  public boolean isInactive() {
    return true;
  }



}
