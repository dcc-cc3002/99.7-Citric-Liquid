package cl.uchile.dcc.citricliquid.model.Entities.Wild_Unit;

import cl.uchile.dcc.citricliquid.model.Entities.Entity;

import cl.uchile.dcc.citricliquid.model.Panel.Panel;

/**
 * Abstract class that creates wild Unit.
 */
public abstract class WildUnit extends Entity {

  public WildUnit(String name, int atk, int def, int hp, int evd, Panel panel) {
    super(name, atk, def, hp, evd, panel);
  }
}
