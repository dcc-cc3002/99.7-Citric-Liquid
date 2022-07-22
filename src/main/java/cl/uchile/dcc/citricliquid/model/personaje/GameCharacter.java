package cl.uchile.dcc.citricliquid.model.personaje;


import cl.uchile.dcc.citricliquid.model.exeptions.InvalidStateOperationException;
import cl.uchile.dcc.citricliquid.model.state.AbstractState;

/**
 * Sets the current enemy's hit points.
 *
 */
public interface GameCharacter {

  /**
   * Sets the current enemy's hit points.
   */
  void battle(AbstractCharacter opponent) throws InvalidStateOperationException;

  /**
   * get character's stars.
   *
   * @return character's stars.
   */
  int getStars();


  /**
   * get character's value when is killed.
   *
   * @return character's value.
   */
  int getWinValue();

  /**
   * Returns the current character's attack points.
   */
  int getAtk();

  /**
  * Returns the current hit points of the character.
  */
  int getCurrentHp();

  /**
   * Returns the current character's defense points.
   */
  int getDef();

  /**
   * Returns the current character's evasion points.
   */
  int getEvd();

  /**
   * Returns the character's max hit points.
   */
  int getMaxHp();

  /**
   * Returns the character's name.
   */
  String getName();

  /**
   * set this character's current hit points to a given amount.
   */
  void setCurrentHp(int amount);

  /**
   * increase the character's stars count by an amount.
   *
   * @param amount numeric value for increase enemy's stars.
   */
  void increaseStarsBy(int amount);

  /**
   * reduce the character' stars count by an amount.
   *
   * @param amount numeric value for reduce enemy's stars.
   */
  void reduceStarsBy(int amount);


  /**
   * Returns a uniformly distributed random value in [1, 6].
   */
  int roll();

  /**
   * Set's the seed for this player's random number generator.
   */
  void setSeed(long seed);


  //METODOS RELACIONADOS A LA BATALLA.
  /**
   * calculate attack point like base attak + roll.
   */
  int attack_point();

  /**
   * Reduce hp in a amount.
   */
  void isAttacked(int amount);


  //METODOS RELACIONADOS A LOS ESTADOS.
  //SON LOS ESTADOS EN QUE CUALQUIER PERSONAJE PUEDE ESTAR.

  /**
   * Get actual state.
   *
   */
  AbstractState getState();

  /**
   * set a new state.
   *
   */
  void setState(AbstractState newstate);

  /**
   * change state to InactiveState.
   *
   */
  void toInactiveState() throws InvalidStateOperationException;

  /**
   * change state to CounterAttack.
   *
   */
  void toCounterAttackState() throws InvalidStateOperationException;

  /**
   * change state to Attacked state.
   *
   */
  void toAttackedState() throws InvalidStateOperationException;

  /**
   * change state to AttackedDefender.
   *
   */
  void toAttackedDefender() throws InvalidStateOperationException;

  /**
   * change state to AttackedEsquivar.
   *
   */
  void toAttackedEsquivar() throws InvalidStateOperationException;

  int attacked(int ataque) throws InvalidStateOperationException;

  /**
   * return true if the state is Active.
   *
   * @return true if the state is Active.
   */
  boolean isActive();

  /**
   * return true if the state is DetenidoState.
   *
   * @return true if the state is DetenidoState.
   */
  boolean isDetenidoState();

  /**
   * return true if the state is EffectPanelState.
   *
   * @return true if the state is EffectPanelState.
   */
  boolean isEffectPanelState();

  /**
   * return true if the state is Inactive.
   *
   * @return true if the state is Inactive.
   */
  boolean isInactive();

  /**
   * return true if the state is MoveState.
   *
   * @return true if the state is MoveState.
   */
  boolean isMoveState();

  /**
   * return true if the state is RecoveryState.
   *
   * @return true if the state is RecoveryState.
   */
  boolean isRecoveryState();

  /**
   * return true if the state is StartTurn.
   *
   * @return true if the state is StartTurn.
   */
  boolean isStartTurn();

  /**
   * return true if the state is Attack.
   *
   * @return true if the state is Attack.
   */
  boolean isAttack();

  /**
   * return true if the state is CounterAtatck.
   *
   * @return true if the state is CounterAttack.
   */
  boolean isCounterAttack();

  /**
   * return true if the state is AttackedDefender.
   *
   * @return true if the state is AttackedDefender.
   */
  boolean isAttackedDefender();

  /**
   * return true if the state is AttackedEsquivar.
   *
   * @return true if the state is AttackedEsquivar.
   */
  boolean isAttackedEsquivar();

  /**
   * return true if the state is AttackedState.
   *
   * @return true if the state is AttackedState.
   */
  boolean isAttackedState();

  /**
   * return true if the state is CounterAttacked.
   *
   * @return true if the state is CounterAttacked.
   */
  boolean isCounterAttacked();

  /**
   * return true if the state is CounterDefender.
   *
   * @return true if the state is CounterDefender.
   */
  boolean isCounterDefender();

  /**
   * return true if the state is CounterEsquivar.
   *
   * @return true if the state is CounterEsquivar.
   */
  boolean isCounterEsquivar();
}
