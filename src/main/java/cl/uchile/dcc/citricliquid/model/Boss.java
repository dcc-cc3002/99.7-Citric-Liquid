package cl.uchile.dcc.citricliquid.model;

/**
 * This class represent a Boss in the game 99.7% Citric Liquid.
 */
public class Boss extends Entity {

  /**
   * Create a new Boss
   *
   * @param name the boss's name.
   * @param hp   the initial (and max) hit points of the boss.
   * @param atk  the base damage the boss does.
   * @param def  the base defense of the boss.
   * @param evd  the base evasion of the boss.
   */
  public Boss(final String name, final int hp, final int atk, final int def,
              final int evd) {
    super(name, hp, atk, def, evd);
  }
}
