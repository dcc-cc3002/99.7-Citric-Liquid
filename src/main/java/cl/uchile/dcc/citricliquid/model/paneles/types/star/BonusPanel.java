package cl.uchile.dcc.citricliquid.model.paneles.types.star;

import cl.uchile.dcc.citricliquid.model.exeptions.InvalidStateOperationException;
import cl.uchile.dcc.citricliquid.model.paneles.AbstracPanel;
import cl.uchile.dcc.citricliquid.model.personaje.player.Player;
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
   * Panel is activated by player. Player recive a bonus.
   *
   * @param player -player who activated the panel.
   */
  @Override
  public void activate(final  Player player) throws InvalidStateOperationException {
    if (getJugadores().contains(player)) {
      applyBonusTo(player);
    }
    player.toInactiveState();
  }


  @Override
  public String toString() {
    return "BonusPanel";
  }

  @Override
  public boolean isBonusPanel() {
    return true;
  }

}
