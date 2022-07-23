package cl.uchile.dcc.citricliquid.model.Entities.Wild_Unit;


import cl.uchile.dcc.citricliquid.model.Panel.Panel;

/**
 * Creates a robo ball.
 */
public class Robo_Ball extends WildUnit {
  public Robo_Ball(Panel panel) {
    super("Robo Ball", - 1, + 1, 3, - 1, panel);
  }
}
