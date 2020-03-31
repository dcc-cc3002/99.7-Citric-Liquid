package com.github.cc3002.citricjuice.model;

import java.util.Random;

/**
 * This class represents a player in the game 99.7% Citric Liquid.
 *
 * @author <a href="mailto:ignacio.slater@ug.uchile.cl">Ignacio Slater
 *     Muñoz</a>.
 * @version 1.0.6-b.4
 * @since 1.0
 */
public class Player {
  private final Random random;
  private String name;
  private int maxHP;
  private int atk;
  private int def;
  private int evd;
  private int normaLevel;
  private int stars;
  private int currentHP;

  /**
   * Creates a new character.
   *
   * @param name
   *     the character's name.
   * @param hp
   *     the initial (and max) hit points of the character.
   * @param atk
   *     the base damage the character does.
   * @param def
   *     the base defense of the character.
   * @param evd
   *     the base evasion of the character.
   */
  public Player(final String name, final int hp, final int atk, final int def,
                final int evd) {
    this.name = name;
    this.maxHP = currentHP = hp;
    this.atk = atk;
    this.def = def;
    this.evd = evd;
    normaLevel = 1;
    random = new Random();
  }

  /**
   * Increases this player's star count by an amount.
   */
  public void increaseStarsBy(int amount) {
    stars += amount;
  }

  public int getStars() {
    return stars;
  }

  public void setSeed(final long seed) {
    random.setSeed(seed);
  }

  /**
   * @return a uniformly distributed random value in [1, 6]
   */
  public int roll() {
    return random.nextInt(6) + 1;
  }

  /**
   * @return the character's name.
   */
  public String getName() {
    return name;
  }

  /**
   * @return the character's max hit points.
   */
  public int getMaxHP() {
    return maxHP;
  }

  /**
   * @return the current character's attack points.
   */
  public int getAtk() {
    return atk;
  }

  /**
   * @return the current character's defense points.
   */
  public int getDef() {
    return def;
  }

  /**
   * @return the current character's evasion points.
   */
  public int getEvd() {
    return evd;
  }

  /**
   * @return the current norma level
   */
  public int getNormaLevel() {
    return normaLevel;
  }

  /**
   * Performs a norma clear action; the {@code norma} counter increases in 1.
   */
  public void normaClear() {
    normaLevel++;
  }

  /**
   * @return the current hit points of the character.
   */
  public int getCurrentHP() {
    return currentHP;
  }

  /**
   * Sets the current character's hit points.
   * <p>
   * The character's hit points have a constraint to always be between 0 and
   * maxHP, both inclusive.
   */
  public void setCurrentHP(final int newHP) {
    this.currentHP = Math.max(Math.min(newHP, maxHP), 0);
  }
}
