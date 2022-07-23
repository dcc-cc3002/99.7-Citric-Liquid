package cl.uchile.dcc.citricliquid.model.Panel;

import cl.uchile.dcc.citricliquid.model.Entities.Player;
import org.jetbrains.annotations.NotNull;

/**
 * BonusPanel handles the methods when the player falls in a bonus panel.
 */
public class BonusPanel extends Panel {


  /**
   * x and y are the coordinates of the panel in the world
   * type is the panel type according to PanelType
   * Also, every panel has the possible directions that could use the player.
   *
   * @param x    horizontal
   * @param y    vertical
   * @param path paths
   */
  public BonusPanel(int x, int y, int[] path) {
    super(x, y, PanelType.BONUS, path);
  }

  @Override
  public void activatedBy(@NotNull Player player) {

    int stars = player.roll() * Math.min(player.getNormaLevel(), 3);
    player.increaseStarsBy(stars);
  }
}
