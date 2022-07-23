package cl.uchile.dcc.citricliquid.model.Entities.Wild_Unit;


import cl.uchile.dcc.citricliquid.model.Panel.Panel;

/**
 * Creates a seagull in a panel.
 */
public class Seagull extends WildUnit {
  public Seagull(Panel panel) {
    super("Seagull", 1, - 1, 3, - 1, panel);
  }
}
