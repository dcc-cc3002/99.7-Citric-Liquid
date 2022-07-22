package cl.uchile.dcc.citricliquid.model.paneles.types;

import cl.uchile.dcc.citricliquid.model.exeptions.InvalidStateOperationException;
import cl.uchile.dcc.citricliquid.model.paneles.AbstracPanel;
import cl.uchile.dcc.citricliquid.model.personaje.player.Player;

/**
 * Class for create a draw Panel.
 *
 */
public class DrawPanel extends AbstracPanel {
  //No se tienen cartas así que no se va a implementar para esta tarea

  /**
   * constructor for draw panel. No parameter.
   *
   */
  public  DrawPanel() {
  }

  /**
   * Panel is activated by player. Nothing happens.
   *
   * @param player player who activated the panel.
   */
  @Override
  public void activate(final Player player) throws InvalidStateOperationException {
    //Acá irá lo que pase cuando se active
    player.toInactiveState();
  }

  @Override
  public String toString() {
    return "DrawPanel";
  }

  @Override
  public boolean isDrawPanel() {
    return true;
  }

}
