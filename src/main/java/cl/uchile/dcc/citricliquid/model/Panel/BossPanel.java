package cl.uchile.dcc.citricliquid.model.Panel;

import cl.uchile.dcc.citricliquid.model.Entities.Player;


/**
 * Handles the boss panel.
 * It has methods when the players trigger an entity spawn.
 */
public class BossPanel extends Panel {
  /**
   * x and y are the coordinates of the panel in the world
   * type is the panel type according to PanelType
   * Also, every panel has the possible directions that could use the player.
   * activated marks if the panel is activated as a boss panel or simply as an encounter panel
   */
  private boolean active;

  public BossPanel(int x, int y, int[] path, boolean active) {
    super(x, y, PanelType.BOSS, path);
    this.active = active;
  }


  public void setActive() {
    this.active = true;
  }
  /**
   * Sets the chain of events if a player falls in a boss panel.
   * If someone is level>=4, the boss panel is active. It will spawn a boss.
   * If not, it will spawn a wild unit
   *
   * @param player player who activates the panel
   */
  public void activatedBy(Player player) {
    if (active) {
      setBattleAgainstBossUnit(player);
    } else {
      setBattleAgainstWildUnit(player);
    }
  }


}
