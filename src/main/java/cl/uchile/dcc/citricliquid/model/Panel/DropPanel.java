package cl.uchile.dcc.citricliquid.model.Panel;

import cl.uchile.dcc.citricliquid.model.Entities.Player;


/**
 * Handles the methods when a player falls in a Drop Panel.
 */
public class DropPanel extends Panel {
  /**
   * x and y are the coordinates of the panel in the world
   * type is the panel type according to PanelType
   * Also, every panel has the possible directions that could use the player.
   *
   * @param x    horizontal coordinate
   * @param y    vertical coordinate
   * @param path the possible paths
   */
  public DropPanel(int x, int y, int[] path) {
    super(x, y, PanelType.DROP, path);
  }

  @Override
  public void activatedBy(Player player) {
    int stars = player.roll() * player.getNormaLevel();
    player.reduceStarsBy(Math.max(0, stars));

  }
}
