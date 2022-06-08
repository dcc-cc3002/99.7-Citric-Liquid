package cl.uchile.dcc.citricliquid.model.Entities.Wild_Unit;


import cl.uchile.dcc.citricliquid.model.Panel.Panel;

/**
 * Creates a chicken in a panel.
 */
public class Chicken extends WildUnit {
  public Chicken(Panel panel) {
    super("Chicken", - 1, - 1, 3, + 1, panel);
  }
}
