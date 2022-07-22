package cl.uchile.dcc.citricliquid.model.personaje.enemies.wild;

//Esta enum es utilizado como único parámetro por la clase wild, ya que contiene los parámetros
// que no cambian de cada uno de estos enemigos, name,maxHp,atk,def,evd,name. También se generaron
//métodos get públicos para poderlos utilizar en otras clases para obtener estos argumentos.

/**
 * Have all the enemies that are wild type.
 *
 *
 */
public enum WildType {
  CHICKEN("Chicken", 3, -1, -1, 1),
  ROBO_BALL("Robo ball", 2, -1, 1, -1),
  SEAGULL("Seagull", 3, 1, -1, -1);
  private final int maxHp;
  private final int atk;
  private final int def;
  private final int evd;
  private final String name;

  WildType(String name, int maxHp, int atk, int def, int evd) {
    this.name = name;
    this.maxHp = maxHp;
    this.atk = atk;
    this.def = def;
    this.evd = evd;
  }

  /**
   * get wild's max hit points.
   *
   * @return  the wild's max hit points.
   */
  public int getMaxHp() {
    return maxHp;
  }

  /**
   *get wild's attack points.
   *
   * @return  wild's attack points.
   */
  public int getAtk() {
    return atk;
  }

  /**
  * get wild's defense points.
  *
  * @return wild's defense points.
  */
  public int getDef() {
    return def;
  }

  /**
  * get wild's evasion points.
  *
  * @return the wild's evasion points.
  */
  public int getEvd() {
    return evd;
  }

  /**
  * get wild's name.
  *
  * @return the wild's name.
  */
  public String getName() {
    return name;
  }
}

