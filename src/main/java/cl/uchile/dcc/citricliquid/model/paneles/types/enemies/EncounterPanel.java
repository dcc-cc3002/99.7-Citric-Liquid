package cl.uchile.dcc.citricliquid.model.paneles.types.enemies;


import cl.uchile.dcc.citricliquid.model.personaje.enemies.AbstractEnemies;
import cl.uchile.dcc.citricliquid.model.personaje.enemies.wild.WildUnit;

/**
 * Class for create a encounter Panel.
 *
 */
public class EncounterPanel extends AbstractPanelEnemies {


  public EncounterPanel() {
    super();
  }


  /**
   * set the rival in the panel. It can be wild unit.
   *
   * @param enemy new rival
   */
  public void setRival(WildUnit enemy) {
    rival = enemy;
  }

  @Override
  public String toString() {
    return "EncounterPanel";
  }

  @Override
  public boolean isEncounterPanel() {
    return true;
  }
  
}