package cl.uchile.dcc.citricliquid.model.personaje.enemies.boss;

//Esta enum es utilizado como único parámetro
// por la clase boss, ya que contiene los parámetros
// que no cambian de cada uno de
// estos enemigos, name,maxHp,atk,def,evd. También se generaron
//métodos get públicos para poderlos
// utilizar en otras clases para obtener estos argumentos.

/**
 * Have all the enemies that are boss type.
 *
 *
 */
public enum BossType {
  STORE_MANAGER("Store manager", 8, 3, 2, -1),
  SHIFU_ROBOT("Shifu robot", 7, 2, 3, -2),
  FLYING_CASTLE("Flying castle", 10, 2, 1, -3);
  private final int maxHp;
  private final int atk;
  private final int def;
  private final int evd;
  private final String name;

  /**
  * get Boss's  name.
  *
  * @param name the name of the boss's enemy
  * @param atk the attack points of the boss.
  * @param def the defense points of the boss.
  * @param evd the evasion points of the boss.
  * @param maxHp the max hit points of the boss.
  */
  BossType(final String name, final int maxHp, final int atk, final int def, final int evd) {
    this.name = name;
    this.maxHp = maxHp;
    this.atk = atk;
    this.def = def;
    this.evd = evd;
  }

  /**
  * get Boss's  max hit points.
  *
  * @return Boss's max hit points.
  */
  public int getMaxHp() {
    return maxHp;
  }

  /**
  * get Boss's  attack points.
  *
  * @return Boss's attack points.
  */
  public int getAtk() {
    return atk;
  }

  /**
  * get Boss's  defense points.
  *
  * @return  Boss's defense points.
  */
  public int getDef() {
    return def;
  }

  /**
  * get Boss's  evasion points.
  *
  * @return  Boss's evasion points.
  */
  public int getEvd() {
    return evd;
  }

  /**
  * get Boss's  name.
  *
  * @return the Boss's name.
  */
  public String getName() {
    return name;
  }


}

