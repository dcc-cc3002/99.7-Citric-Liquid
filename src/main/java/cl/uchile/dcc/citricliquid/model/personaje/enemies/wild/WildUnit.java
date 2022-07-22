package cl.uchile.dcc.citricliquid.model.personaje.enemies.wild;

import cl.uchile.dcc.citricliquid.model.personaje.enemies.AbstractEnemies;
import java.util.Random;


//Esta es una clase que crea a los enemigos del tipo wild.
/**
 * Have all the enemies that are wild and can use it in battle.
 *
 *
 */
public class WildUnit extends AbstractEnemies {
  private final WildType tipo;

  /**
  * get Boss's  name.
  *
  * @param tipo the wild's type.
  */
  public WildUnit(WildType tipo) {
    super(tipo.getName(), tipo.getAtk(), tipo.getDef(), tipo.getEvd(), tipo.getMaxHp());
    this.tipo = tipo;
    winValue = 2;
  }

  /**
  * get  Wild's type.
  *
  * @return the wild's type
  */
  public WildType getTipo() {
    return tipo;
  }

  /**
   *  Create a random wildUnit.
   *
   */
  public static WildUnit create_random_Wild_Rival() {
    Random r = new Random();
    int rand = r.nextInt(3) + 1;
    WildUnit rival;
    if (rand == 1) {
      rival = new WildUnit(WildType.CHICKEN);
    } else if (rand == 2) {
      rival = new WildUnit(WildType.ROBO_BALL);
    } else {
      rival = new WildUnit(WildType.SEAGULL);
    }
    return rival;

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


}
