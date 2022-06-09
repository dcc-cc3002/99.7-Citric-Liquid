
package model.personaje.player;

import java.util.Objects;
import java.util.Random;
//La clase player nos permite crear un jugador.

/**
 *Class for create a player.
 *
 */
public class Player {
  private final Random random;
  private final String name;
  private final int maxHp;
  private final int atk;
  private final int def;
  private final int evd;
  private int normaLevel;
  private int stars;
  private int wins;
  private int currentHp;
  private SubirCon starorwins;

  /**
  * constructor for the class player.
  *
  * @param evd  evasion points.
  * @param def defense points.
  * @param atk attack points.
  * @param hp hit points.
  * @param name playe's name.
  */
  public Player(final String name, final int hp, final int atk, final int def, final int evd) {
    this.name = name;
    this.maxHp = currentHp = hp;
    this.atk = atk;
    this.def = def;
    this.evd = evd;
    normaLevel = 1;
    starorwins = SubirCon.STARS;
    random = new Random();
  }


  /**
  *get player's star count.
  *
  * @return this player's star count.
  */
  public int getStars() {
    return stars;
  }

  /**
  * get player's wins count.
  *
  * @return this player's wins count.
  */
  public int getWins() {
    return wins;
  }

  /**
  * get player's norma up preferences.
  *
  * @return the character's preferences for norma up.
  */
  public SubirCon getStar_or_wins() {
    return starorwins;
  }

  /**
  * get player's character's name.
  *
  * @return the character's name.
  */
  public String getName() {
    return name;
  }

  /**
  * get player's max hit points.
  *
  * @return the character's max hit points.
  */
  public int getMaxHp() {
    return maxHp;
  }

  /**
  * get player's attack points.
  *
  * @return the current character's attack points.
  */
  public double getAtk() {
    return atk;
  }

  /**
   * get player's defense points.
   *
   * @return the current character's defense points.
   */
  public double getDef() {
    return def;
  }

  /**
  * get player's evasion points.
  *
  * @return the current character's evasion points.
  */
  public double getEvd() {
    return evd;
  }

  /**
   * get player's norma level.
   *
   * @return the current norma level.
   */
  public int getNormaLevel() {
    return normaLevel;
  }

  /**
  * get player's current hit points.
  *
  * @return the current hit points of the character.
  */
  public int getCurrentHp() {
    return currentHp;
  }

  @Override
  public boolean equals(final Object o) {
    if (o instanceof  Player) {
      return ((Player) o).getMaxHp() == (this.getMaxHp())
              && ((Player) o).getAtk() == this.getAtk()
              && ((Player) o).getDef() == this.getDef()
              && ((Player) o).getEvd() == this.getEvd()
              && ((Player) o).getNormaLevel() == this.getNormaLevel()
              && ((Player) o).getStars() == this.getStars()
              && ((Player) o).getWins() == this.getWins()
              && ((Player) o).getCurrentHp() == this.getCurrentHp()
              && ((Player) o).getName().equals(this.getName())
              && ((Player) o).getStar_or_wins().equals(this.getStar_or_wins());
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(Player.class);
  }


  /**
  * Set's the seed for this player's random number generator.
  *
  * <p>The random number generator is used for taking non-deterministic decisions, this method is
  * declared to avoid non-deterministic behaviour while testing the code.
  *
  *
  */
  public void setSeed(final long seed) {
    random.setSeed(seed);
  }

  /**
  * Sets the current character's hit points.
  *
  * <p>The character's hit points have a constraint to always be between 0 and maxHP, both
  * inclusive.
  *
  * @param newHp new value for hit points.
  */
  public void setCurrentHp(final int newHp) {
    this.currentHp = Math.max(Math.min(newHp, maxHp), 0);
  }

  public void normaClear() {
    normaLevel++;
  }

  /**
  *  returns a copy of the player.
  *
  * @return a copy of this character.
  */
  public Player copy() {
    return new Player(name, maxHp, atk, def, evd);
  }

  /**
  * act like a dice .
  *
  * @return a uniformly distributed random value in [1, 6].
  */
  public int roll() {
    return random.nextInt(6) + 1;
  }


  /**
  * Reduces this player's star count by a given amount.
  *
  * <p>The star count will must always be greater or equal to 0
  *
  * @param amount numeric value for reduce player's stars.
  */
  public void reduceStarsBy(final int amount) {
    stars = Math.max(0, stars - amount);
  }

  /**
  * Increases this player's star count by an amount.
  *
  * @param amount numeric value for increase player's stars.
  */
  public void increaseStarsBy(final int amount) {
    stars += amount;
  }


  /**
  * Increases this player's wins count by an amount.
  *
  * @param amount numeric value for reduce player's wins.
  */
  public void  increaseWinsBy(final int amount) {
    wins += amount;
  }

  /**
  * next norma check will be with stars.
  *
  */
  public void subir_star() {
    starorwins = SubirCon.STARS;
  }

  /**
  * next norma check will be with wins.
  */
  public void subir_wins() {
    starorwins = SubirCon.WINS;
  }


}

