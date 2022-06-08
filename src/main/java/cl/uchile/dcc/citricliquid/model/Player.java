package cl.uchile.dcc.citricliquid.model;

import java.util.Random;

public class Player {
  private final Random random;
  private final String name;
  private int maxHp;
  private int atk;
  private int def;
  private int evd;
  private int normaLevel;
  private int stars;
  private int currentHp;


  public Player(final String name, final int hp, final int atk, final int def,
                final int evd) {
    this.name = name;
    this.maxHp = currentHp = hp;
    this.atk = atk;
    this.def = def;
    this.evd = evd;
    normaLevel = 1;
    stars = 0;
    random = new Random();
  }


  public void increaseStarsBy(final int amount) {
    this.stars += amount;
  }


  public int getStars() {
    return stars;
  }


  public void setSeed(final long seed) {
    random.setSeed(seed);
  }


  public int roll() {
    return random.nextInt(6) + 1;
  }


  public String getName() {
    return this.name;
  }


  public int getMaxHp() {
    return this.maxHp;
  }


  public int getAtk() {
    return this.atk;
  }


  public int getDef() {
    return this.def;
  }


  public int getEvd() {
    return this.evd;
  }


  public int getNormaLevel() {
    return normaLevel;
  }


  public void normaClear() {
    normaLevel++;
  }


  public int getCurrentHp() {
    return this.currentHp;
  }


  public void setCurrentHp(final int newHp) {
    this.currentHp = Math.max(Math.min(newHp, maxHp), 0);
  }

  public void reduceStarsBy(final int amount) {
    this.stars = Math.max(0, this.stars - amount);
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof final Player player)) {
      return false;
    }
    return getMaxHp() == player.getMaxHp()
            && getAtk() == player.getAtk()
            && getDef() == player.getDef()
            && getEvd() == player.getEvd()
            && getNormaLevel() == player.getNormaLevel()
            && getStars() == player.getStars()
            && getCurrentHp() == player.getCurrentHp()
            && getName().equals(player.getName());
  }

  public Player copy() {
    return new Player(name, maxHp, atk, def, evd);
  }
}
