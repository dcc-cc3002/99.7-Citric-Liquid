package cl.uchile.dcc.citricliquid.model.paneles.types;


import cl.uchile.dcc.citricliquid.model.exeptions.InvalidStateOperationException;
import cl.uchile.dcc.citricliquid.model.paneles.AbstracPanel;
import cl.uchile.dcc.citricliquid.model.personaje.player.Player;

/**
 * Class for create a neutral Panel.
 *
 */
public class NeutralPanel extends AbstracPanel {
  public  NeutralPanel() {

  }

  /**
   * Panel is activated by player. Nothing happend.
   *
   * @param player player who activated the panel.
   */
  @Override
  public void activate(final Player player) throws InvalidStateOperationException {
    player.toInactiveState();
  }

  @Override
  public String toString() {
    return "NeutralPanel";
  }

  @Override
  public boolean isNeutralPanel() {
    return true;
  }


}
