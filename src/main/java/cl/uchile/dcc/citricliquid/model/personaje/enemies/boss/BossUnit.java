package cl.uchile.dcc.citricliquid.model.personaje.enemies.boss;

import cl.uchile.dcc.citricliquid.model.personaje.enemies.AbstractEnemies;
import java.util.Random;

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
public class BossUnit extends AbstractEnemies {
  private final BossType tipo;


  /**
  * get Boss's  name.
  *
  * @param tipo the boss's type.
  */
  public BossUnit(final BossType tipo) {
    super(tipo.getName(), tipo.getAtk(), tipo.getDef(), tipo.getEvd(), tipo.getMaxHp());
    this.tipo = tipo;
    winValue = 3;
  }

  /**
   * get  Boss's type.
   *
   * @return  Boss's type.
   */
  public BossType getTipo() {
    return tipo;
  }

  /**
   * Create a random enemy.
   *
   * @return a random enemy.
   */
  public static BossUnit create_random_Boss_Rival() {
    Random r = new Random();
    int rand = r.nextInt(3) + 1;
    BossUnit rival;
    if (rand == 1) {
      rival = new BossUnit(BossType.STORE_MANAGER);
    } else if (rand == 2) {
      rival = new BossUnit(BossType.SHIFU_ROBOT);
    } else {
      rival = new BossUnit(BossType.FLYING_CASTLE);
    }
    return rival;
  }

  //Como en una partida solo hay un bossUnit, comparamos sus campos
  //y si son todos iguales entonces decimos que es la misma unidad.
  /**
   * Compares boss units and return true it this are equals.
   *
   */
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

}
