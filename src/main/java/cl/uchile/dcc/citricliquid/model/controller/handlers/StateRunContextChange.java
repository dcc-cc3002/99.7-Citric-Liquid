package cl.uchile.dcc.citricliquid.model.controller.handlers;

import cl.uchile.dcc.citricliquid.model.controller.Controller;
import cl.uchile.dcc.citricliquid.model.exeptions.InvalidStateOperationException;
import cl.uchile.dcc.citricliquid.model.personaje.player.Player;
import java.beans.PropertyChangeEvent;

/**
 *  Handler ot changes in state.
 *
 */
public class StateRunContextChange implements Handler {

  private final Controller controller;

  public StateRunContextChange(Controller controller) {
    this.controller = controller;
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    Player value = (Player) evt.getNewValue();

    if (value != null) {
      try {
        value.runContextAction();
      } catch (InvalidStateOperationException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
