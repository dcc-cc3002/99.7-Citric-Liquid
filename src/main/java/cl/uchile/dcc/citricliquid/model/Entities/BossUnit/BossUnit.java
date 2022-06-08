package cl.uchile.dcc.citricliquid.model.Entities.BossUnit;

import cl.uchile.dcc.citricliquid.model.Entities.Entity;
import cl.uchile.dcc.citricliquid.model.Panel.Panel;

/**
 * Abstract Class that creates a boss unit.
 */
public abstract class BossUnit extends Entity {
  public BossUnit(String name, int atk, int def, int hp, int evd, Panel panel) {
    super(name, atk, def, hp, evd, panel);
  }
}
