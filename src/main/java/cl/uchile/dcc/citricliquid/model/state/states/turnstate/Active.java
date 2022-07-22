package cl.uchile.dcc.citricliquid.model.state.states.turnstate;

import cl.uchile.dcc.citricliquid.model.exeptions.InvalidStateOperationException;
import cl.uchile.dcc.citricliquid.model.personaje.AbstractCharacter;
import cl.uchile.dcc.citricliquid.model.personaje.player.Player;
import cl.uchile.dcc.citricliquid.model.state.AbstractState;

/**
 * class thar initialize a turn.
 *
 */
public class Active extends AbstractState {
  public Active(AbstractCharacter context) {
    super(context);
  }


  //Hay dos transiciones posibles:
  //Recovery si hp=0
  //StartTurn si hp>0

  @Override
  public void toStartTurn() {
    context.setState(new StartTurn(context));
  }

  @Override
  public void toRecoveryState() {
    context.setState(new RecoveryState(context));
  }

  @Override
  public int runAction() throws InvalidStateOperationException {
    if (context.getCurrentHp() == 0) {
      System.out.println("Se tiene hp=0. Se pasa a estado de recuperacion");
      ((Player) context).toRecoveryState();
      return 0;
    }
    ((Player) context).toStartTurn();
    return 0;
  }

  @Override
  public boolean isActive() {
    return true;
  }
}
