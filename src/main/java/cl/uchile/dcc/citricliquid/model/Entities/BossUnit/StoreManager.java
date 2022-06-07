package cl.uchile.dcc.citricliquid.model.Entities.BossUnit;


import cl.uchile.dcc.citricliquid.model.Panel.Panel;

/**
 * Creates a Store Manager in a panel.
 */
public class StoreManager extends BossUnit {
  public StoreManager(Panel panel) {
    super("Store Manager", 3, 2, 8, - 1, panel);
  }
}
