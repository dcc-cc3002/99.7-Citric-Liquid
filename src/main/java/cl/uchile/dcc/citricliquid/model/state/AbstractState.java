package cl.uchile.dcc.citricliquid.model.state;

import cl.uchile.dcc.citricliquid.model.exeptions.InvalidStateOperationException;
import cl.uchile.dcc.citricliquid.model.personaje.AbstractCharacter;
import org.jetbrains.annotations.NotNull;

/**
 * concrete class that inheritors all the posible states.
 *
 */
public abstract class AbstractState {

  protected AbstractCharacter context;

  protected AbstractState(AbstractCharacter context) {
    this.context = context;
  }

  /**
   * Set a new context.
   *
   */
  public void setContext(AbstractCharacter newcontext) {
    context = newcontext;
  }
  

  /**
   * change state to StartTurn.
   *
   */
  public void toStartTurn() throws InvalidStateOperationException {
    throw new InvalidStateOperationException();
  }

  /**
   * change state to RecoveryState.
   *
   */
  public void toRecoveryState() throws InvalidStateOperationException {
    throw new InvalidStateOperationException();
  }

  /**
   * change state to attacked.
   *
   */
  public void toAttackedState() throws InvalidStateOperationException {
    throw new InvalidStateOperationException();
  }

  /**
   * change state to CounterAttack.
   *
   */
  public void toCounterAttackState() throws InvalidStateOperationException {
    throw new InvalidStateOperationException();
  }

  /**
   * change state to Inactive.
   *
   */
  public void toInactiveState() throws InvalidStateOperationException {
    throw new InvalidStateOperationException();
  }

  /**
   * change state to MoveState.
   *
   */
  public void toMoveState() throws InvalidStateOperationException {
    throw new InvalidStateOperationException();
  }

  /**
   * change state to Inactive.
   *
   */
  public void toDetenidoState() throws InvalidStateOperationException {
    throw new InvalidStateOperationException();
  }

  /**
   * change state to attack.
   *
   */
  public void toAttack()  throws InvalidStateOperationException {
    throw new InvalidStateOperationException();
  }

  /**
   * change state to Effect panel.
   *
   */
  public void toEffectPanelState()  throws InvalidStateOperationException {
    throw new InvalidStateOperationException();
  }


  /**
   * change state to Effect panel.
   *
   */
  public void toCounterAttacked() throws InvalidStateOperationException {
    throw new InvalidStateOperationException();
  }


  /**
   * change state to Effect panel.
   *
   */
  public void toAttackedDefender() throws InvalidStateOperationException {
    throw new InvalidStateOperationException();
  }

  /**
   * change state to Effect panel.
   *
   */
  public void toAttackedEsquivar() throws InvalidStateOperationException {
    throw new InvalidStateOperationException();
  }

  /**
   * change state to CounterAttackedDefender.
   *
   */
  public void toCounterAttackedDefender() throws InvalidStateOperationException {
    throw new InvalidStateOperationException();
  }

  /**
   * change state to CounterAttackedEsquivar.
   *
   */
  public void toCounterAttackedEsquivar()  throws InvalidStateOperationException {
    throw new InvalidStateOperationException();
  }

  /**
   * change state to Active.
   *
   */
  public void toActiveState() throws InvalidStateOperationException {
    throw new InvalidStateOperationException();
  }

  /**
   * Drive the logic of hoy a chatacter that is attacked manage this attack,
   * with the damage done.
   *
   * @return the damage.
   */
  public int attacked(int baseatk) throws InvalidStateOperationException {
    throw new InvalidStateOperationException();
  }

  /**
   * star a battle between a player who call the method and another character
   * that is attacked.
   *
   */
  public void battle(@NotNull AbstractCharacter atacado) throws InvalidStateOperationException {
    throw new InvalidStateOperationException();
  }

  /**
   * Method that do a determinatet deterministic action acording a state.
   *
   */
  public int runAction() throws InvalidStateOperationException {
    throw new InvalidStateOperationException();
  }

  public int mover_un_panel() {
    return 1;
  }

  /**
   * return true if the state is Active.
   *
   * @return true if the state is Active.
   */
  public boolean isActive() {
    return false;
  }

  /**
   * return true if the state is DetenidoState.
   *
   * @return true if the state is DetenidoState.
   */
  public boolean isDetenidoState() {
    return false;
  }

  /**
   * return true if the state is EffectPanelState.
   *
   * @return true if the state is EffectPanelState.
   */
  public boolean isEffectPanelState() {
    return false;
  }

  /**
   * return true if the state is Inactive.
   *
   * @return true if the state is Inactive.
   */
  public boolean isInactive() {
    return false;
  }

  /**
   * return true if the state is MoveState.
   *
   * @return true if the state is MoveState.
   */
  public boolean isMoveState() {
    return false;
  }

  /**
   * return true if the state is RecoveryState.
   *
   * @return true if the state is RecoveryState.
   */
  public boolean isRecoveryState() {
    return false;
  }

  /**
   * return true if the state is StartTurn.
   *
   * @return true if the state is StartTurn.
   */
  public boolean isStartTurn() {
    return false;
  }

  /**
   * return true if the state is Attack.
   *
   * @return true if the state is Attack.
   */
  public boolean isAttack() {
    return false;
  }

  /**
   * return true if the state is CounterAtatck.
   *
   * @return true if the state is CounterAttack.
   */
  public boolean isCounterAttack() {
    return false;
  }

  /**
   * return true if the state is AttackedDefender.
   *
   * @return true if the state is AttackedDefender.
   */
  public boolean isAttackedDefender() {
    return false;
  }

  /**
   * return true if the state is AttackedEsquivar.
   *
   * @return true if the state is AttackedEsquivar.
   */
  public boolean isAttackedEsquivar() {
    return false;
  }

  /**
   * return true if the state is AttackedState.
   *
   * @return true if the state is AttackedState.
   */
  public boolean isAttackedState() {
    return false;
  }

  /**
   * return true if the state is CounterAttacked.
   *
   * @return true if the state is CounterAttacked.
   */
  public boolean isCounterAttacked() {
    return false;
  }

  /**
   * return true if the state is CounterDefender.
   *
   * @return true if the state is CounterDefender.
   */
  public boolean isCounterDefender() {
    return false;
  }

  /**
   * return true if the state is CounterEsquivar.
   *
   * @return true if the state is CounterEsquivar.
   */
  public boolean isCounterEsquivar() {
    return false;
  }

}
