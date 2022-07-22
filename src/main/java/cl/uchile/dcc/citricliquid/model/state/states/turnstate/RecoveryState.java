package cl.uchile.dcc.citricliquid.model.state.states.turnstate;

import cl.uchile.dcc.citricliquid.model.exeptions.InvalidStateOperationException;
import cl.uchile.dcc.citricliquid.model.personaje.AbstractCharacter;
import cl.uchile.dcc.citricliquid.model.personaje.player.Player;
import cl.uchile.dcc.citricliquid.model.state.AbstractState;

/**
 * Class to recovery when the player's hp==0.
 *
 */
public class RecoveryState extends AbstractState {
  public RecoveryState(AbstractCharacter context) {
    super(context);
  }

  //Hay dos posibles transiciones:
  //En caso de no poder recuperarse, a inactive.
  //en caso de recuperarse, a StartTurn.

  @Override
  public void toInactiveState() {
    context.setState(new Inactive(context));
  }

  @Override
  public void toStartTurn() {
    context.setState(new StartTurn(context));
  }

  @Override
  public int runAction() throws InvalidStateOperationException {
    int dice = context.roll();
    int charper = ((Player) context).getChapters();
    if (dice + charper < 7) { //No se cumple la condicion para recuperarse
      context.toInactiveState();
      return 0;
    }
    context.setCurrentHp(context.getMaxHp());
    ((Player) context).toStartTurn();
    return 0;
  }

  @Override
  public boolean isRecoveryState() {
    return true;
  }

}
