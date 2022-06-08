package cl.uchile.dcc.citricliquid.model.Entities;

/**
 * Interface which handles the Players and Entites at the game.
 */
public interface CharacterInterface {
  /**
   * Sets the seed for the random generator.
   *
   * @param seed the seed for random
   */
  void setSeed(final long seed);

  /**
   * Rolls a dice and returns a value between 1 and 6.
   *
   * @return the value of the dice.
   */
  int roll();

  /**
   * Gets the name of an entity or a player.
   *
   * @return the name.
   */
  String getName();

  /**
   * rolls a dice to get a new ATK.
   *
   * @return the tempATK.
   */
  int rollAtk();

  /**
   * rolls a die to get the new DEF.
   *
   * @return the tempDEF.
   */
  int rollDef();

  /**
   * Method that handles the defense from an attack.
   *
   * @param enemy is the source of the attack.
   */
  void defend(Character enemy);

  /**
   * Method that handles the evasion from an attack.
   *
   * @param enemy is the source of the attack.
   */
  void evade(Character enemy);

  /**
   * boolean that returns true if an entity or a player is dead.
   *
   * @return a boolean.
   */
  boolean isKo();

  /**
   * A boolean if is dead or not.
   *
   * @return the attack of the character.
   */
  int getAtk();

  /**
   * Gets the defense stat.
   *
   * @return the defense of the character.
   */
  int getDef();

  /**
   * Gets the stars stat.
   *
   * @return the stars of the character.
   */
  int getStars();

  /**
   * Gets the evasion stat.
   *
   * @return the evasion of the character.
   */
  int getEvd();

  /**
   * Gets the current hp stat.
   *
   * @return the actual hp of the character.
   */

  int getCurrentHp();

  /**
   * set the actual hp of the character.
   *
   * @param newHp set the actual hp of the character.
   */
  void setCurrentHp(final int newHp);

  /**
   * Increases the hp of the character.
   *
   * @param amount increases the stars by amount.
   */
  void increaseStarsBy(final int amount);

  /**
   * reduces the stars by amount.
   *
   * @param amount reduces the stars by amount.
   */
  void reduceStarsBy(final int amount);


}
