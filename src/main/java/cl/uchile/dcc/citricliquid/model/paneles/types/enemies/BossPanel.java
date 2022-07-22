package cl.uchile.dcc.citricliquid.model.paneles.types.enemies;

import cl.uchile.dcc.citricliquid.model.personaje.enemies.AbstractEnemies;


/**
 * Class for create a BossPanel.
 *
 */
public class BossPanel extends AbstractPanelEnemies {


  /**
   * constructor for boss panel. No parameter.
   *
   */
  public BossPanel() {

  }

  /**
   * Set a rival in the panel. It can be wild unit or boss unit.
   *
   */
  public void setRival(AbstractEnemies rival) {
    this.rival = rival;
  }

  @Override
  public String toString() {
    return "BossPanel";
  }

  @Override
  public boolean isBossPanel() {
    return true;
  }
}
