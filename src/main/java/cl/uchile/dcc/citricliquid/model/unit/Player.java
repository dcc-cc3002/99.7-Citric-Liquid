package cl.uchile.dcc.citricliquid.model.unit;

import cl.uchile.dcc.citricliquid.model.NormaGoal;
import cl.uchile.dcc.citricliquid.model.board.Panel;

import java.util.List;

/**
 * This class represents a player in the game 99.7% Citric Liquid.
 *
 * @author <a href="mailto:ignacio.slater@ug.uchile.cl">Ignacio Slater
 *     Muñoz</a>.
 * @version 1.1.222804
 * @since 1.0
 */
public class Player extends AbstractUnit implements Unit{
  private int normaLevel;
  private NormaGoal normaGoal;
  private Panel currentPanel;
  private Panel homePanel;
  public boolean isAtk;


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
    super(name, hp, atk, def, evd);
    normaLevel = 1;
    isAtk = false;
  }

  /**
   * Returns the current norma goal number.
   */
  public NormaGoal getNormaGoal(){ return normaGoal; }

  public void setNormaGoal(NormaGoal goal) { normaGoal = goal;}

  /**
   * Returns the current norma level.
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
   * Checks if the requirements to level up are met.
   */
  public void normaCheck() {
    if(normaGoal == NormaGoal.STARS) {
      List<Integer> starGoal = List.of(10, 30, 70, 120, 200);
      int stars = this.getStars();
      if(starGoal.get(normaLevel - 1) <= stars) {
        normaClear();
      }
    }
    if( normaGoal == NormaGoal.WINS) {
      List<Integer> winsGoal = List.of(0, 2, 5, 9, 14);
      int wins = getWins();
      if(winsGoal.get(normaLevel - 1) <= wins) {
        normaClear();
      }
    }
  }

  /**
   * Increases this Player's stars by calling the specific
   * method through the loser.
   * @param rival being the loser.
   */
  public void increaseStarsBy(Unit rival) {
    rival.increaseStarsByPlayer(this);

  }

  /**
   * Increases this Player's number of wins by an amount,
   * by calling the specific method through the rival.
   */
  public void increaseWinsBy(Unit rival) {
    rival.increaseWinsByPlayer(this);
  }

  /**
   * Increases the rival's number of wins by 2.
   * @param player being the rival
   */
  @Override
  public void increaseWinsByPlayer(Player player) {
    player.setWins(player.getWins() + 2);

  }

  /**
   * Reduces this Player's amount of stars, and adds the right
   * amount to the winner's number of stars.
   * @param player being the winner.
   */
  @Override
  public void increaseStarsByPlayer(Player player) {
    player.increaseStarsBy((int) Math.floor(this.getStars() * 0.5));
    this.reduceStarsBy((int) Math.ceil(this.getStars()*0.5));
  }

  /**
   * Increases the rival's number of wins by 2.
   * @param bossUnit being the rival
   */
  public void increaseWinsByBoss(BossUnit bossUnit) {
    bossUnit.setWins(bossUnit.getWins() + 2);
  }

  /**
   * Reduces this Player's amount of stars, and adds the right
   * amount to the winner's number of stars.
   * @param bossUnit being the winner.
   */
  public void increaseStarsByBoss(BossUnit bossUnit) {
    bossUnit.increaseStarsBy((int) Math.floor(this.getStars()*0.5));
    this.reduceStarsBy((int) Math.ceil(this.getStars()*0.5));
  }

  /**
   * Increases the rival's number of wins by 2.
   * @param wildUnit being the rival
   */
  public void increaseWinsByWild(WildUnit wildUnit) {
    wildUnit.setWins(wildUnit.getWins() + 2);
  }

  /**
   * Reduces this Player's amount of stars, and adds the right
   * amount to the winner's number of stars.
   * @param wildUnit being the winner.
   */
  public void increaseStarsByWild(WildUnit wildUnit) {
    wildUnit.increaseStarsBy((int) Math.floor(this.getStars()*0.5));
    this.reduceStarsBy((int) Math.ceil(this.getStars()*0.5));

  }
  /**
   * Sets this player's current evasion points.
   * @param newEvd is the new number of evasion points.
   */
  public void setEvd(int newEvd) { evd = newEvd; }

  /**
   * Sets this player's current attack points.
   * @param newAtk is the new number of attack points.
   */
  public void setAtk(int newAtk) { atk = newAtk; }

  /**
   * Sets this player's current defense points.
   * @param newDef is the new number of defense points.
   */
  public void setDef(int newDef) { def = newDef; }

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

  /**
   * Returns the current panel in which the player is located.
   */
  public Panel getPanel() {
    return currentPanel;
  }

  /**
   * Sets the panel in which the player is located.
   * @param panel is the panel that the player is moved to.
   */
  public void setCurrentPanel(Panel panel) {
    currentPanel = panel;
  }

  /**
   * Returns the player's home panel.
   */
  public Panel getHomePanel() { return homePanel; }

  /**
   * Sets this player's home panel.
   */
  public void setHomePanel(Panel newHome) {
    homePanel = newHome;
  }

  /**
   * Returns a copy of this character.
   */
  public Player copy() {
    return new Player(this.getName(), this.getMaxHp(), atk, def, evd);
  }

  /**
   * Set's the seed for this Player's random number generator.
   *
   * <p>The random number generator is used for taking non-deterministic decisions, this method is
   * declared to avoid non-deterministic behaviour while testing the code.
   */
  public void SetSeed(long l) {

  }
}
