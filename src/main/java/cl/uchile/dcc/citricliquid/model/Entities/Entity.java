package cl.uchile.dcc.citricliquid.model.Entities;


import cl.uchile.dcc.citricliquid.model.Panel.Panel;
import org.jetbrains.annotations.NotNull;

/**
 * Handles an Entity (wild or boss unit).
 */
public abstract class Entity extends Character {


  private final Panel panel;
  private int horizontalPositionEntity;
  private int verticalPositionEntity;

  /**
   * Constructor of an Entity.
   *
   * @param name  Name of the entity.
   * @param atk   atk stat.
   * @param def   def stat.
   * @param hp    hp stat.
   * @param evd   evasion stat.
   * @param panel panel in which the entity lives.
   */
  public Entity(String name, int atk, int def, int hp, int evd, Panel panel) {
    super(name, hp, atk, def, evd);
    this.panel = panel;
    this.setLocation(panel);
    horizontalPositionEntity = panel.getPanelLocation()[0];
    verticalPositionEntity = panel.getPanelLocation()[1];
  }

  /**
   * Sets the location of an Entity. Then, the entity is added to the entity field in the panel
   *
   * @param panel the panel of the entity.
   */
  public void setLocation(@NotNull Panel panel) {
    this.horizontalPositionEntity = panel.getPanelLocation()[0];
    this.verticalPositionEntity = panel.getPanelLocation()[1];
    panel.addEntity(this);


  }

  /**
   * Gets the panel in which the entity lives.
   *
   * @return the panel of the entity
   */
  public Panel getPanel() {
    return this.panel;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (! (o instanceof final Entity entity)) {
      return false;
    }
    return getMaxHp() == entity.getMaxHp()
            && getAtk() == entity.getAtk()
            && getDef() == entity.getDef()
            && getEvd() == entity.getEvd()
            && getStars() == entity.getStars()
            && getCurrentHp() == entity.getCurrentHp()
            && getName().equals(entity.getName());
  }

}
