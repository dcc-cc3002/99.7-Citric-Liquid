package model.paneles.types;



import java.util.Objects;
import model.paneles.AbstracPanel;
import model.personaje.player.Player;
import org.jetbrains.annotations.NotNull;

/**
 * Bonus Panel.
 *
 */
public class BonusPanel extends AbstracPanel {
  public  BonusPanel() {
  }

  /**
   * Increase the player's star count by the min of D6 roll multiplied by player's norma
   * and 3 multiplied by the D6 roll.
   *
   * @param player player get a bonus increasing player's stars.
   */
  private void applyBonusTo(final @NotNull Player player) {
    player.increaseStarsBy(player.roll() * Math.min(player.getNormaLevel(), 3));
  }

  /**
   * Panel is activated by player.
   *
   * @param player -player who activated the panel.
   */
  @Override
  public void activate(final  Player player) {
    if (getJugadores().contains(player)) {
      applyBonusTo(player);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof BonusPanel) {
      return ((BonusPanel) o).getJugadores().equals(this.getJugadores())
              && ((BonusPanel) o).getNextPanels().equals(this.getNextPanels());
    } else {
      return  false;
    }
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(BonusPanel.class);
  }

}
