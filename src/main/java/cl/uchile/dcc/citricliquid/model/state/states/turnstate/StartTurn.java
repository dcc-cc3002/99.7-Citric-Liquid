package cl.uchile.dcc.citricliquid.model.state.states.turnstate;

import cl.uchile.dcc.citricliquid.model.exeptions.InvalidStateOperationException;
import cl.uchile.dcc.citricliquid.model.personaje.AbstractCharacter;
import cl.uchile.dcc.citricliquid.model.personaje.player.Player;
import cl.uchile.dcc.citricliquid.model.state.AbstractState;

/**
 * Star a player turn. It recive the stars here.
 *
 */
public class StartTurn extends AbstractState {
  public StartTurn(AbstractCharacter context) {
    super(context);
  }


  //Desde este estado solo puede pasar a MoveState.
  @Override
  public void toMoveState() {
    context.setState(new MoveState(context));
  }

  /**
   * recive stars and chanche the state to move state.
   *
   */
  @Override
  public int runAction() throws InvalidStateOperationException {
    int cap = ((Player) context).getChapters();
    int s = (int) Math.floor(1 + ((float) cap) / 5);
    context.increaseStarsBy(s);
    System.out.println("Se encuentra en el cap: " + cap + " y recibe: " + s + " stars.");
    ((Player) context).toMoveState();
    return 0;
  }

  @Override
  public boolean isStartTurn() {
    return true;
  }



}
