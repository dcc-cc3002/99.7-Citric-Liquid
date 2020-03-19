package com.github.islaterm;

/**
 * This class represents a character in the game 99.7% Citric Liquid.
 *
 * @author <a href="mailto:ignacio.slater@ug.uchile.cl">Ignacio Slater Muñoz</a>.
 * @version 1.0.5-b3
 * @since 1.0
 */
public class Player {

  private String name;
  private int maxHP;
  private int atk;
  private int def;
  private int evd;
  private int norma;

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
    this.maxHP = hp;
    this.atk = atk;
    this.def = def;
    this.evd = evd;
    norma = 1;
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

  private void setAtk(final int atk) {
    this.atk = atk;
  }

  /**
   * @return the current character's defense points.
   */
  public int getDef() {
    return def;
  }

  private void setDef(final int def) {
    this.def = def;
  }

  /**
   * @return the current character's evasion points.
   */
  public int getEvd() {
    return evd;
  }

  private void setEvd(final int evd) {
    this.evd = evd;
  }

  public int getNorma() {
    return norma;
  }

  public void normaClear() {

  }
}
