package cl.uchile.dcc.citricliquid.model.personaje;

import cl.uchile.dcc.citricliquid.model.exeptions.InvalidStateOperationException;
import cl.uchile.dcc.citricliquid.model.state.AbstractState;
import cl.uchile.dcc.citricliquid.model.state.states.turnstate.Inactive;
import java.util.Random;

/**
 * Abstract class containing the common methods and attributes of all characters.
 *
 */
public abstract class AbstractCharacter implements GameCharacter {

  protected AbstractState state;
  private final int atk;
  private int currentHp;
  private final int def;
  private final int evd;
  private final int maxHp;

  private final String name;
  protected final Random randomGenerator;

  private int stars;

  protected int winValue;

  protected AbstractCharacter(String name, int atk, int def, int evd, int maxHp) {
    this.name = name;
    this.maxHp = currentHp = maxHp;
    this.atk = atk;
    this.def = def;
    this.evd = evd;
    randomGenerator = new Random();
    stars = 0;
    state = new Inactive(this);
  }


  @Override
  public int getWinValue() {
    return winValue;
  }

  @Override
  public int getAtk() {
    return atk;
  }

  @Override
  public int getCurrentHp() {
    return currentHp;
  }

  @Override
  public int getDef() {
    return def;
  }

  @Override
  public int getEvd() {
    return evd;
  }

  @Override
  public int getMaxHp() {
    return maxHp;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void setCurrentHp(final int newHp) {
    currentHp = Math.max(Math.min(newHp, maxHp), 0);
  }

  @Override
  public int roll() {
    return randomGenerator.nextInt(6) + 1;
  }

  @Override
  public void increaseStarsBy(int amount) {
    stars = stars + amount;
  }

  @Override
  public void reduceStarsBy(int amount) {
    stars = Math.max(0, stars - amount);
  }

  @Override
  public int getStars() {
    return stars;
  }

  @Override
  public void setSeed(long seed) {
    randomGenerator.setSeed(seed);
  }

  @Override
  public int attack_point() {
    return this.roll() + this.getAtk();
  }

  public void isAttacked(int attaked) {
    int newhp = this.getCurrentHp() - attaked;
    this.setCurrentHp(newhp);
  }


  public AbstractState getState() {
    return state;
  }

  public void setState(AbstractState newstate) {
    state = newstate;
    state.setContext(this);
  }


  public void toCounterAttackState() throws InvalidStateOperationException {
    state.toCounterAttackState();
  }

  public void toInactiveState() throws InvalidStateOperationException {
    state.toInactiveState();
  }

  public void toAttackedDefender() throws InvalidStateOperationException {
    state.toAttackedDefender();
  }

  public void toAttackedEsquivar() throws InvalidStateOperationException {
    state.toAttackedEsquivar();
  }

  public int attacked(int baseatk) throws InvalidStateOperationException {
    return state.attacked(baseatk);
  }

  @Override
  public void battle(AbstractCharacter atacado) throws InvalidStateOperationException {
    state.battle(atacado);
  }


  @Override
  public boolean isActive() {
    return state.isActive();
  }

  @Override
  public boolean isDetenidoState() {
    return state.isDetenidoState();
  }

  @Override
  public boolean isEffectPanelState() {
    return state.isEffectPanelState();
  }

  @Override
  public boolean isInactive() {
    return state.isInactive();
  }

  @Override
  public boolean isMoveState() {
    return state.isMoveState();
  }

  @Override
  public boolean isRecoveryState() {
    return state.isRecoveryState();
  }

  @Override
  public boolean isStartTurn() {
    return state.isStartTurn();
  }

  @Override
  public boolean isAttack() {
    return state.isAttack();
  }

  @Override
  public boolean isCounterAttack() {
    return state.isCounterAttack();
  }

  @Override
  public boolean isAttackedDefender() {
    return state.isAttackedDefender();
  }

  @Override
  public boolean isAttackedEsquivar() {
    return state.isAttackedEsquivar();
  }

  @Override
  public boolean isAttackedState() {
    return state.isAttackedState();
  }

  @Override
  public boolean isCounterAttacked() {
    return state.isCounterAttacked();
  }

  @Override
  public boolean isCounterDefender() {
    return state.isCounterDefender();
  }

  @Override
  public boolean isCounterEsquivar() {
    return state.isCounterEsquivar();
  }

}
