package cl.uchile.dcc.citricliquid.model.Entities.BossUnit;


import cl.uchile.dcc.citricliquid.model.Panel.Panel;

/**
 * Creates a Shifu Robot in a panel.
 */
public class ShifuRobot extends BossUnit {

  public ShifuRobot(Panel panel) {
    super("Shifu Robot", 2, 3, 7, - 2, panel);
  }
}
