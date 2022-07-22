package cl.uchile.dcc.citricliquid.model.paneles.types.star;

import cl.uchile.dcc.citricliquid.model.exeptions.InvalidStateOperationException;
import cl.uchile.dcc.citricliquid.model.paneles.AbstracPanel;
import cl.uchile.dcc.citricliquid.model.personaje.player.Player;
import org.jetbrains.annotations.NotNull;

/**
 * Class for create a drop Panel.
 *
 */
public class DropPanel extends AbstracPanel {

  /**
   * constructor for boss panel. No parameter.
   *
   */
  public  DropPanel() {
  }

  /**
   * player's stars reduced for drop.
   *
   *
   * @param player revived drop.
   */
  private static void applyDropTo(final @NotNull Player player) {
    player.reduceStarsBy(player.roll() * player.getNormaLevel());
  }

  /**
   * Panel is activated by player. player's stars reduced for drop.
   *
   * @param player player who activated the panel.
   */
  @Override
  public void activate(final  Player player) throws InvalidStateOperationException {
    if (getJugadores().contains(player)) {
      applyDropTo(player);
    }
    player.toInactiveState();
  }


  @Override
  public String toString() {
    return "DropPanel";
  }

  @Override
  public boolean isDropPanel() {
    return true;
  }

}
