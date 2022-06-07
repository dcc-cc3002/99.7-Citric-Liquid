package cl.uchile.dcc.citricliquid.model.Entities.BossUnit;


import cl.uchile.dcc.citricliquid.model.Panel.Panel;

/**
 * Creates Flying Castle in a panel.
 */
public class FlyingCastle extends BossUnit {

  public FlyingCastle(Panel panel) {
    super("Flying Castle", 2, 1, 10, - 3, panel);
  }
}
