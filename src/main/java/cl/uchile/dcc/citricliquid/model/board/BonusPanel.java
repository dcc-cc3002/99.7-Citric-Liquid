package cl.uchile.dcc.citricliquid.model.board;

import cl.uchile.dcc.citricliquid.model.Player;
import org.jetbrains.annotations.NotNull;

public class BonusPanel extends Panel {

  public BonusPanel() {
    super(PanelType.BONUS);
  }

  /**
   * Reduces the player's star count by the D6 roll multiplied by the maximum between the player's
   * norma level and three.
   */
  private static void applyBonusTo(final @NotNull Player player) {
    player.increaseStarsBy(player.roll() * Math.min(player.getNormaLevel(), 3));
  }

  @Override
  public void activatedBy(Player player) {
    applyBonusTo(player);
  }

}
