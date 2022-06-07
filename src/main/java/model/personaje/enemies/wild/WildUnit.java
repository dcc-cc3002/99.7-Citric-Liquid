package model.personaje.enemies.wild;

import java.util.Objects;
import model.personaje.enemies.Enemies;


//Esta es una clase que crea a los enemigos del tipo wild.
/**
 * Have all the enemies that are wild and can use it in battle.
 *
 *
 */
public class WildUnit implements Enemies {
  private final WildType tipo;
  private final int maxHp;
  private final double atk;
  private final double def;
  private final double evd;
  private int currentHp;
  private int stars;
  private final String name;

  /**
  * get Boss's  name.
  *
  * @param tipo the wild's type.
  */
  public WildUnit(WildType tipo) {
    this.tipo = tipo;
    this.maxHp = tipo.getMaxHp();
    this.atk = tipo.getAtk();
    this.def = tipo.getDef();
    this.evd = tipo.getEvd();
    currentHp = tipo.getMaxHp();
    stars = 0;
    name = tipo.getName();
  }

  /**
  * get  Wild's type.
  *
  * @return the wild's type
  */
  public WildType getTipo() {
    return tipo;
  }

  public String getName() {
    return name;
  }

  public int getMaxHp() {
    return maxHp;
  }

  public double getAtk() {
    return atk;
  }

  public double getDef() {
    return def;
  }

  public double getEvd() {
    return evd;
  }

  public int getCurrentHp() {
    return currentHp;
  }

  public int getStars() {
    return stars;
  }


  public void increase_star_by(int amount) {
    stars = stars + amount;
  }

  public void reduce_star_by(int amount) {
    stars = Math.max(0, stars - amount);
  }

  public void setCurrentHp(final int newHp) {
    currentHp = Math.max(Math.min(newHp, maxHp), 0);
  }

  @Override
  public boolean equals(final Object o) {
    if (o instanceof WildUnit) {
      return ((WildUnit) o).getMaxHp() == (this.getMaxHp())
              && ((WildUnit) o).getAtk() == this.getAtk()
              && ((WildUnit) o).getDef() == this.getDef()
              && ((WildUnit) o).getEvd() == this.getEvd()
              && ((WildUnit) o).getStars() == this.getStars()
              && ((WildUnit) o).getCurrentHp()
              == this.getCurrentHp()
              && ((WildUnit) o).getName().equals(this.getName())
              && ((WildUnit) o).getTipo().equals(this.getTipo());
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(WildUnit.class);
  }

}
