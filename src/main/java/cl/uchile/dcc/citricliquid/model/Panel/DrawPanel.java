package cl.uchile.dcc.citricliquid.model.Panel;

import cl.uchile.dcc.citricliquid.model.Entities.Player;


/**
 * Handles the methods when a player falls in a Draw Panel.
 */
public class DrawPanel extends Panel {
  /**
   * x and y are the coordinates of the panel in the world
   * type is the panel type according to PanelType
   * Also, every panel has the possible directions that could use the player.
   *
   * @param x    horizontal coordinate
   * @param y    vertical coordinate
   * @param path the possible paths
   */
  public DrawPanel(int x, int y, int[] path) {
    super(x, y, PanelType.DRAW, path);
  }

  @Override
  public void activatedBy(Player player) {
    //Draw a card
  }
}
