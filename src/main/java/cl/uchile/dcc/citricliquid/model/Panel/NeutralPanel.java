package cl.uchile.dcc.citricliquid.model.Panel;

import cl.uchile.dcc.citricliquid.model.Entities.Player;


/**
 * Handles the neutral panel.
 * In this panel, nothing happens, but in the sake of good design, I choose to add it.
 */
public class NeutralPanel extends Panel {
  /**
   * x and y are the coordinates of the panel in the world
   * type is the panel type according to PanelType
   * Also, every panel has the possible directions that could use the player.
   *
   * @param x    horizontal coordinate
   * @param y    vertical coordinate
   * @param path the paths
   */
  public NeutralPanel(int x, int y, int[] path) {
    super(x, y, PanelType.NEUTRAL, path);
  }

  @Override
  public void activatedBy(Player player) {
    //Do Nothing
  }
}