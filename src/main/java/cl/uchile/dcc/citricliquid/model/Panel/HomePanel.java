package cl.uchile.dcc.citricliquid.model.Panel;

import cl.uchile.dcc.citricliquid.model.Entities.Player;


/**
 * Manages the Home Panel.
 */
public class HomePanel extends Panel {
  /**
   * x and y are the coordinates of the panel in the world
   * type is the panel type according to PanelType
   * Also, every panel has the possible directions that could use the player.
   *
   * @param x    horizontal coordinate
   * @param y    vertical coordinate
   * @param path the path
   */
  public HomePanel(int x, int y, int[] path) {
    super(x, y, PanelType.HOME, path);
  }

  @Override
  public void activatedBy(Player player) {
    if (this.getPlayer_home() == player) {
      player.setCurrentHp(player.getCurrentHp() + 1);
    }
  }
}
