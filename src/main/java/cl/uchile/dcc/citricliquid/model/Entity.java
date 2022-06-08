package cl.uchile.dcc.citricliquid.model;

import java.util.Random;

public class Entity {
  private final Random random;
  private final String name;
  private final int maxHp;
  private final int atk;
  private final int def;
  private final int evd;
  private int currentHp;
  private int stars;

  public Entity(final String name, final int hp, final int atk, final int def,
                final int evd) {
    this.name = name;
    this.maxHp = currentHp = hp;
    this.atk = atk;
    this.def = def;
    this.evd = evd;
    random = new Random();
    stars = 0;
  }

  /**
   * Set's the seed for this entity's random number generator.
   *
   * <p>The random number generator is used for taking non-deterministic decisions, this method is
   * declared to avoid non-deterministic behaviour while testing the code.
   */
  public void setSeed(final long seed) {
    random.setSeed(seed);
  }

  /**
   * Returns a uniformly distributed random value in [1, 6].
   */
  public int roll() {
    return random.nextInt(6) + 1;
  }

  /**
   * Returns the entity's name.
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the entity's max hit points.
   */
  public int getMaxHp() {
    return maxHp;
  }

  /**
   * Returns the current entity's attack points.
   */
  public int getAtk() {
    return atk;
  }

  /**
   * Returns the current entity's defense points.
   */
  public int getDef() {
    return def;
  }

  /**
   * Returns the current entity's evasion points.
   */
  public int getEvd() {
    return evd;
  }

  /**
   * Returns the current hit points of the entity.
   */
  public int getCurrentHp() {
    return currentHp;
  }

  /**
   * Sets the current entity's hit points.
   *
   * <p>The entity's hit points have a constraint to always be between 0 and maxHP, both
   * inclusive.
   */
  public void setCurrentHp(final int newHp) {
    this.currentHp = Math.max(Math.min(newHp, maxHp), 0);
  }

  /**
   * Reduces this entity's star count by a given amount.
   *
   * <p>The star count will must always be greater or equal to 0
   */
  public void reduceStarsBy(final int amount) {
    stars = Math.max(0, stars - amount);
  }

  /**
   * Increases this player's star count by an amount.
   */
  public void increaseStarsBy(final int amount) {
    stars += amount;
  }

  /**
   * Returns this player's star count.
   */
  public int getStars() {
    return stars;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof final Entity entity)) {
      return false;
    }
    return getMaxHp() == entity.getMaxHp()
            && getAtk() == entity.getAtk()
            && getDef() == entity.getDef()
            && getEvd() == entity.getEvd()
            && getStars() == entity.getStars()
            && getCurrentHp() == entity.getCurrentHp()
            && getName().equals(entity.getName());
  }
}
