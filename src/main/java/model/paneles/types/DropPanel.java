package model.paneles.types;

import java.util.Objects;
import model.paneles.AbstracPanel;
import model.personaje.player.Player;
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
   * Panel is activated by player.
   *
   * @param player player who activated the panel.
   */
  @Override
  public void activate(final  Player player) {
    if (getJugadores().contains(player)) {
      applyDropTo(player);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof DropPanel) {
      return ((DropPanel) o).getJugadores().equals(this.getJugadores())
              && ((DropPanel) o).getNextPanels().equals(this.getNextPanels());
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(DropPanel.class);
  }





}
