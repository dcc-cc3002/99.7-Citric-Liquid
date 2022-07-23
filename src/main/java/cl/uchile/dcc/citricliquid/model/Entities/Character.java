package cl.uchile.dcc.citricliquid.model.Entities;

import cl.uchile.dcc.citricliquid.States.IdleState;
import cl.uchile.dcc.citricliquid.States.State;
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
  private State state;

  private int tempAtk;
  private int tempDef;

  private int tempEvd;
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
    this.setState(new IdleState());
  }

  /**
   * Changes the state (attacking, defending, etc)
   * @param state to set.
   */
  public void setState(@NotNull State state){
    this.state = state;
    state.setCharacter(this);
  }

  public void ChangeState(State state){
    this.setState(state);
  }

  /**
   * Gets the states in which the character is.
   * @return the state.
   */
  public State getState(){
    return this.state;
  }

  public void attack(Character enemy){state.attack(enemy);}

  public void defendDecision(Character enemy, String decision){state.defendDecision(enemy,decision);}

  public void defendFrom(Character enemy){
    state.defend(enemy);
  }

  public void evadeFrom(Character enemy){
    state.evade(enemy);
  }

  public boolean isAttacking(){
    return state.isAttacking();
  }
  public boolean isAttacked() { return state.isAttacked();}
  public boolean isDefending(){
    return state.isDefending();
  }
  public boolean isRecovering(){
    return state.isRecovering();
  }
  public boolean isIdle(){
    return state.isIdle();
  }

  public int rollAtk() {
    this.tempAtk = roll();
    return this.tempAtk;
  }

  public int rollDef() {
    this.tempDef = roll();
    return this.tempDef;
  }
  public int rollEvd(){
    this.tempDef=roll();
    return this.tempEvd;
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

  public void increaseStarsBy(final int amount) {
    stars += amount;
  }
  public void decreaseStarsBy(final int amount){
    stars -= amount;
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
