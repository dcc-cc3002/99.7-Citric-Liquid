package cl.uchile.dcc.citricliquid.model.Entities;

import org.jetbrains.annotations.NotNull;

import java.util.Random;


/**
 * Abstract class which handles the character ,
 * this includes entities ( wild and boss unit) and players.
 */
public abstract class Character implements CharacterInterface {
  private final String name;
  private final int maxHp;
  private final int atk;
  private final int def;
  private final int evd;
  private final Random random;

  private int tempAtk;
  private int tempDef;
  private int currentHp;
  private int stars;

  /**
   * Constructor of character.
   *
   * @param name Character name.
   * @param hp   Sets the current and max Hp.
   * @param atk  The attack of the character.
   * @param def  The defense of the character.
   * @param evd  The evasion of the character.
   */
  public Character(final String name, final int hp, final int atk, final int def, final int evd) {
    this.name = name;
    this.maxHp = currentHp = hp;
    this.atk = atk;
    this.def = def;
    this.evd = evd;
    this.stars = 0;
    this.random = new Random();

  }

  public int rollAtk() {
    this.tempAtk = roll();
    return this.tempAtk;
  }

  public int rollDef() {
    this.tempDef = roll();
    return this.tempDef;
  }

  /**
   * If a character is dead.
   *
   * @return a boolean
   */
  public boolean isKo() {
    if (getCurrentHp() == 0) {
      System.out.println("The Character " + getName() + " is KO");
      return true;

    } else {
      return false;
    }
  }

  public void setSeed(final long seed) {
    random.setSeed(seed);
  }

  public int roll() {
    return random.nextInt(6) + 1;
  }

  public String getName() {
    return name;
  }

  public int getCurrentHp() {
    return currentHp;
  }

  public void setCurrentHp(final int newHp) {
    this.currentHp = Math.max(Math.min(newHp, maxHp), 0);
  }


  /**
   * Returns the character's max hit points.
   */
  public int getMaxHp() {
    return maxHp;
  }

  /**
   * Defense from an enemy.
   *
   * @param enemy is the source of the attack
   */
  public void defend(@NotNull Character enemy) {
    System.out.println(enemy.getName()
            +
            " attacks"
    );
    int damage = Math.max(1, enemy.rollAtk() + enemy.getAtk() - (rollDef() + def));
    setCurrentHp(this.currentHp - damage);
    System.out.println("Attack defended");
    System.out.println("Damage Done:" + damage);
    System.out.println("current hp: " + this.currentHp);

  }

  /**
   * Evasion from an enemy.
   *
   * @param enemy is the source of the attack
   */
  public void evade(@NotNull Character enemy) {
    System.out.println(enemy.getName()
            +
            " attacks"
    );
    if (rollDef() + this.def > enemy.getAtk() + enemy.rollAtk()) {

      System.out.println(this.getName()
              +
              " evaded the attack!! "
      );
      System.out.println("Damage Done:" + 0);
      System.out.println("current hp: " + this.currentHp);
    } else {
      int damage = enemy.getAtk() + enemy.rollAtk();
      setCurrentHp(this.getCurrentHp() - damage);
      System.out.println(this.getName()
              +
              " didn't evaded the attack!! "
      );
      System.out.println("Attack not evaded, full damage done");
      System.out.println("Damage Done:" + damage);
      System.out.println("current hp: " + this.currentHp);

    }
  }

  public void increaseStarsBy(final int amount) {
    stars += amount;
  }

  public void reduceStarsBy(final int amount) {
    stars = Math.max(0, stars - amount);

  }

  public int getStars() {
    return this.stars;
  }

  public int getDef() {
    return this.def;
  }

  public int getAtk() {
    return this.atk;
  }

  public int getEvd() {
    return this.evd;
  }

}
