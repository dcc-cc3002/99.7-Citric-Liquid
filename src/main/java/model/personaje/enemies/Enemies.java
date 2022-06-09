package model.personaje.enemies;

//Esta es una interface donde se tienen los métodos que se ocuparán para los enemigos sin importar
//su tipo. Es implementada por las clases boss y wild.

/**
 * interface thar declares all method for enemies.
 *
 */
public interface Enemies {

  /**
  * get enemy's name.
  *
  *@return enemy's name.
  */
  String getName();

  /**
  * get enemy's max hit points.
  *
  * @return enemy's max hit points.
  */
  int getMaxHp();

  /**
  * get enemy's attack points.
  *
  * @return enemy's attack points.
  */
  double getAtk();

  /**
  * get enemy's defense points.
  *
  * @return enemy's defense points.
  */
  double getDef();

  /**
  * get enemy's evasion points.
  *
  * @return enemy's evasion points.
  */
  double getEvd();

  /**
  * get enemy's current hit points.
  *
  * @return enemy's current hit points.
  */
  int getCurrentHp();

  /**
  * get enemy's stars.
  *
  * @return enemy's stars.
  */
  int getStars();

  /**
  * increase the enemy's stars count by an amount.
  *
  * @param amount numeric value for increase enemy's stars.
  */
  void increase_star_by(int amount);

  /**
  * reduce the enemy' stars count by an amount.
  *
  * @param amount numeric value for reduce enemy's stars.
  */
  void reduce_star_by(int amount);

  /**
  * Sets the current enemy's hit points.
  *
  * @param newHp numeric value for enemy's Current hit points.
  */
  void setCurrentHp(final int newHp);

}
