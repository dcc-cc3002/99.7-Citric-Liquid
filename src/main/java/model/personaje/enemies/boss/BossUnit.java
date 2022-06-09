package model.personaje.enemies.boss;



import java.util.Objects;
import model.personaje.enemies.Enemies;

//Esta es una clase que crea a los enemigos del tipo boss.
// En esta se implementa la interfaz enemies
//que tiene los métodos que comparten todos los tipos de
// enemigos. Para crear un enemigo de este tipo
//se debe entregar un parámetro de BossType de donde se obtienen
// los parámetros name,maxHp,atk,def,evd,
//y adicionamente se crean: currentHp y stars, las cuales
// cambian según el estado del enemigo.


/**
* Have all the enemies that are boss and can use it in battle.
*
*
*/
public class BossUnit implements Enemies {
  private final BossType tipo;
  private final int maxHp;
  private final int atk;
  private final int def;
  private final double evd;
  private int currentHp;
  private int stars;
  private final String name;

  /**
  * get Boss's  name.
  *
  * @param tipo the boss's type.
  */
  public BossUnit(final BossType tipo) {
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
   * get  Boss's type.
   *
   * @return  Boss's type.
   */
  public BossType getTipo() {
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


  public void increase_star_by(final int amount) {
    stars = stars + amount;
  }

  public void reduce_star_by(final int amount) {
    stars = Math.max(0, stars - amount);
  }

  public void setCurrentHp(final int newHp) {
    currentHp = Math.max(Math.min(newHp, maxHp), 0);
  }


  @Override
  public boolean equals(final Object o) {
    if (o instanceof BossUnit) {
      return ((BossUnit) o).getMaxHp() == (this.getMaxHp())
            && ((BossUnit) o).getAtk() == this.getAtk()
            && ((BossUnit) o).getDef() == this.getDef()
            && ((BossUnit) o).getEvd() == this.getEvd()
            && ((BossUnit) o).getStars() == this.getStars()
            && ((BossUnit) o).getCurrentHp() == this.getCurrentHp()
            && ((BossUnit) o).getName().equals(this.getName())
            && ((BossUnit) o).getTipo().equals(this.getTipo());
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(BossUnit.class);
  }
}
