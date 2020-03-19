package com.github.islaterm;

/**
 * This class represents a character in the game 99.7% Citric Liquid.
 *
 * @author <a href="mailto:ignacio.slater@ug.uchile.cl">Ignacio Slater Muñoz</a>.
 * @version 1.0.5-b1
 * @since 1.0
 */
public class Player {

  private String name;
  private int maxHP;
  private int atk;
  private int def;
  private int evd;

  public Player(final String name, final int hp, final int atk, final int def,
                final int evd) {

  }

  public String getName() {
    return name;
  }

  public int getMaxHP() {
    return maxHP;
  }

  public int getAtk() {
    return atk;
  }

  private void setAtk(final int atk) {
    this.atk = atk;
  }

  public int getDef() {
    return def;
  }

  private void setDef(final int def) {
    this.def = def;
  }

  public int getEvd() {
    return evd;
  }

  private void setEvd(final int evd) {
    this.evd = evd;
  }
}
