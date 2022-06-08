package cl.uchile.dcc.citricliquid.model.Panel;

import cl.uchile.dcc.citricliquid.model.Entities.Player;


/**
 * Handles the methods when a player falls in an encounter panel.
 * This includes the battle against a wild or boss unit.
 */
public class EncounterPanel extends Panel {
  /**
   * x and y are the coordinates of the panel in the world
   * type is the panel type according to PanelType
   * Also, every panel has the possible directions that could use the player.
   *
   * @param x    horizontal coordinate
   * @param y    vertical coordinate
   * @param path the path
   */
  public EncounterPanel(int x, int y, int[] path) {
    super(x, y, PanelType.ENCOUNTER, path);
  }

  public void activatedBy(Player player) {
    setBattleAgainstWildUnit(player);
  }


}
