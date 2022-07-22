package test.states.transitiontest;

import cl.uchile.dcc.citricliquid.model.exeptions.InvalidStateOperationException;
import cl.uchile.dcc.citricliquid.model.personaje.player.Player;
import cl.uchile.dcc.citricliquid.model.state.AbstractState;
import cl.uchile.dcc.citricliquid.model.state.states.battle.atacar.Attack;
import cl.uchile.dcc.citricliquid.model.state.states.battle.atacar.CounterAttack;
import cl.uchile.dcc.citricliquid.model.state.states.battle.defender.attacked.AttackedDefender;
import cl.uchile.dcc.citricliquid.model.state.states.battle.defender.attacked.AttackedEsquivar;
import cl.uchile.dcc.citricliquid.model.state.states.battle.defender.attacked.AttackedState;
import cl.uchile.dcc.citricliquid.model.state.states.battle.defender.counterattacked.CounterAttacked;
import cl.uchile.dcc.citricliquid.model.state.states.battle.defender.counterattacked.CounterDefender;
import cl.uchile.dcc.citricliquid.model.state.states.battle.defender.counterattacked.CounterEsquivar;
import cl.uchile.dcc.citricliquid.model.state.states.turnstate.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test the transition that are not permited, so it give a exeption.
 *
 */
public class ExeptionTransitionTest {
  public AbstractState inactive;
  public AbstractState active;
  public AbstractState startTurn;
  public AbstractState moveState;
  public AbstractState detenido;
  public AbstractState effectPanel;
  public AbstractState recoveryState;


  public AbstractState attack;
  public AbstractState attacked;
  public AbstractState atDefender;
  public AbstractState atEsquivar;
  public AbstractState counterAttack;
  public AbstractState coAttacked;
  public AbstractState coDefender;
  public AbstractState coEsquivar;

  public Player player;

  boolean toCounterAttack;
  boolean toAttack;
  boolean toStartTurn;
  boolean toMove;
  boolean toDetenido;
  boolean toEffect;
  boolean toRecovery;
  boolean toActive;
  boolean toInactive;
  boolean toCounterAttacked;
  boolean toAttackedDefender;
  boolean toAttackedEsquivar;
  boolean toCounterAttackedDefender;
  boolean toCounterAttackedEsquivar;
  boolean toAttacked;

  /**
   * create elements for all the test.
   * it includes a player, states, and boolean that originally are all false.
   */
  @BeforeEach
  public void setUp() {
    player = new Player("juan", 2, 2, 2, 2);
    inactive = new Inactive(player);
    active = new Active(player);
    startTurn = new StartTurn(player);
    detenido = new DetenidoState(player);
    moveState = new MoveState(player);
    effectPanel = new EffectPanelState(player);
    recoveryState = new RecoveryState(player);

    attack = new Attack(player);
    attacked = new AttackedState(player);
    atDefender = new AttackedDefender(player);
    atEsquivar = new AttackedEsquivar(player);
    counterAttack = new CounterAttack(player);
    coAttacked = new CounterAttacked(player);
    coDefender = new CounterDefender(player);
    coEsquivar = new CounterEsquivar(player);


    toCounterAttack = false;
    toAttack = false;
    toStartTurn = false;
    toMove = false;
    toDetenido = false;
    toEffect = false;
    toRecovery = false;
    toActive = false;
    toInactive = false;
    toCounterAttacked = false;
    toAttackedDefender = false;
    toAttackedEsquivar = false;
    toCounterAttackedDefender = false;
    toCounterAttackedEsquivar = false;
    toAttacked = false;
  }

  @Test
  public void fromInactive() {

    try {
      inactive.toRecoveryState();
    } catch (InvalidStateOperationException exception) {
      toRecovery = true;
    }

    try {
      inactive.toCounterAttackState();
    } catch (InvalidStateOperationException exception) {
      toCounterAttack = true;
    }

    try {
      inactive.toAttack();
    } catch (InvalidStateOperationException exception) {
      toAttack = true;
    }

    try {
      inactive.toStartTurn();
    } catch (InvalidStateOperationException exception) {
      toStartTurn = true;
    }

    try {
      inactive.toMoveState();
    } catch (InvalidStateOperationException exception) {
      toMove = true;
    }

    try {
      inactive.toDetenidoState();
    } catch (InvalidStateOperationException exception) {
      toDetenido = true;
    }

    try {
      inactive.toEffectPanelState();
    } catch (InvalidStateOperationException exception) {
      toEffect = true;
    }

    try {
      inactive.toCounterAttacked();
    } catch (InvalidStateOperationException exception) {
      toCounterAttacked = true;
    }

    try {
      inactive.toAttackedDefender();
    } catch (InvalidStateOperationException exception) {
      toAttackedDefender = true;
    }

    try {
      inactive.toAttackedEsquivar();
    } catch (InvalidStateOperationException exception) {
      toAttackedEsquivar = true;
    }


    try {
      inactive.toCounterAttackedDefender();
    } catch (InvalidStateOperationException exception) {
      toCounterAttackedDefender = true;
    }

    try {
      inactive.toCounterAttackedEsquivar();
    } catch (InvalidStateOperationException exception) {
      toCounterAttackedEsquivar = true;
    }

    Assertions.assertTrue(toCounterAttackedEsquivar);
    Assertions.assertTrue(toCounterAttackedDefender);
    Assertions.assertTrue(toAttackedEsquivar);
    Assertions.assertTrue(toAttackedDefender);
    Assertions.assertTrue(toCounterAttacked);
    Assertions.assertTrue(toCounterAttack);
    Assertions.assertTrue(toAttack);
    Assertions.assertTrue(toStartTurn);
    Assertions.assertTrue(toMove);
    Assertions.assertTrue(toDetenido);
    Assertions.assertTrue(toEffect);
    Assertions.assertTrue(toRecovery);

  }


  @Test
  public void fromAttackedState() {
    try {
      attacked.toAttackedState();
    } catch (InvalidStateOperationException exception) {
      toAttacked = true;
    }

    try {
      attacked.toRecoveryState();
    } catch (InvalidStateOperationException exception) {
      toRecovery = true;
    }

    try {
      attacked.toActiveState();
    } catch (InvalidStateOperationException exception) {
      toActive = true;
    }

    try {
      inactive.toAttack();
    } catch (InvalidStateOperationException exception) {
      toAttack = true;
    }

    try {
      attacked.toCounterAttackState();
    } catch (InvalidStateOperationException exception) {
      toCounterAttack = true;
    }

    try {
      attacked.toStartTurn();
    } catch (InvalidStateOperationException exception) {
      toStartTurn = true;
    }

    try {
      attacked.toMoveState();
    } catch (InvalidStateOperationException exception) {
      toMove = true;
    }

    try {
      attacked.toDetenidoState();
    } catch (InvalidStateOperationException exception) {
      toDetenido = true;
    }

    try {
      attacked.toEffectPanelState();
    } catch (InvalidStateOperationException exception) {
      toEffect = true;
    }

    try {
      attacked.toCounterAttacked();
    } catch (InvalidStateOperationException exception) {
      toCounterAttacked = true;
    }

    try {
      attacked.toCounterAttackedDefender();
    } catch (InvalidStateOperationException exception) {
      toCounterAttackedDefender = true;
    }

    try {
      attacked.toCounterAttackedEsquivar();
    } catch (InvalidStateOperationException exception) {
      toCounterAttackedEsquivar = true;
    }

    Assertions.assertTrue(toCounterAttackedEsquivar);
    Assertions.assertTrue(toCounterAttackedDefender);
    Assertions.assertTrue(toCounterAttacked);
    Assertions.assertTrue(toRecovery);
    Assertions.assertTrue(toActive);
    Assertions.assertTrue(toCounterAttack);
    Assertions.assertTrue(toAttack);
    Assertions.assertTrue(toStartTurn);
    Assertions.assertTrue(toMove);
    Assertions.assertTrue(toDetenido);
    Assertions.assertTrue(toEffect);
    Assertions.assertTrue(toAttacked);

  }


  @Test
  public void fromActive() {
    try {
      active.toAttackedState();
    } catch (InvalidStateOperationException exception) {
      toAttacked = true;
    }

    try {
      inactive.toAttack();
    } catch (InvalidStateOperationException exception) {
      toAttack = true;
    }

    try {
      active.toInactiveState();
    } catch (InvalidStateOperationException exception) {
      toInactive = true;
    }

    try {
      active.toCounterAttackState();
    } catch (InvalidStateOperationException exception) {
      toCounterAttack = true;
    }


    try {
      active.toMoveState();
    } catch (InvalidStateOperationException exception) {
      toMove = true;
    }


    try {
      active.toDetenidoState();
    } catch (InvalidStateOperationException exception) {
      toDetenido = true;
    }

    try {
      active.toEffectPanelState();
    } catch (InvalidStateOperationException exception) {
      toEffect = true;
    }

    try {
      active.toCounterAttacked();
    } catch (InvalidStateOperationException exception) {
      toCounterAttacked = true;
    }

    try {
      active.toAttackedDefender();
    } catch (InvalidStateOperationException exception) {
      toAttackedDefender = true;
    }

    try {
      active.toAttackedEsquivar();
    } catch (InvalidStateOperationException exception) {
      toAttackedEsquivar = true;
    }

    try {
      active.toCounterAttackedDefender();
    } catch (InvalidStateOperationException exception) {
      toCounterAttackedDefender = true;
    }

    try {
      active.toCounterAttackedEsquivar();
    } catch (InvalidStateOperationException exception) {
      toCounterAttackedEsquivar = true;
    }

    Assertions.assertTrue(toCounterAttackedEsquivar);
    Assertions.assertTrue(toCounterAttackedDefender);
    Assertions.assertTrue(toAttackedEsquivar);
    Assertions.assertTrue(toAttackedDefender);
    Assertions.assertTrue(toCounterAttacked);
    Assertions.assertTrue(toInactive);
    Assertions.assertTrue(toCounterAttack);
    Assertions.assertTrue(toAttack);
    Assertions.assertTrue(toMove);
    Assertions.assertTrue(toDetenido);
    Assertions.assertTrue(toEffect);
    Assertions.assertTrue(toAttacked);

  }

  @Test
  public void fromRecovery() {

    try {
      recoveryState.toRecoveryState();
    } catch (InvalidStateOperationException exception) {
      toRecovery = true;
    }

    try {
      recoveryState.toActiveState();
    } catch (InvalidStateOperationException exception) {
      toActive = true;
    }

    try {
      recoveryState.toCounterAttackState();
    } catch (InvalidStateOperationException exception) {
      toCounterAttack = true;
    }

    try {
      recoveryState.toAttack();
    } catch (InvalidStateOperationException exception) {
      toAttack = true;
    }

    try {
      recoveryState.toMoveState();
    } catch (InvalidStateOperationException exception) {
      toMove = true;
    }


    try {
      recoveryState.toDetenidoState();
    } catch (InvalidStateOperationException exception) {
      toDetenido = true;
    }

    try {
      recoveryState.toEffectPanelState();
    } catch (InvalidStateOperationException exception) {
      toEffect = true;
    }

    try {
      recoveryState.toCounterAttacked();
    } catch (InvalidStateOperationException exception) {
      toCounterAttacked = true;
    }

    try {
      recoveryState.toAttackedDefender();
    } catch (InvalidStateOperationException exception) {
      toAttackedDefender = true;
    }

    try {
      recoveryState.toAttackedEsquivar();
    } catch (InvalidStateOperationException exception) {
      toAttackedEsquivar = true;
    }

    try {
      recoveryState.toCounterAttackedDefender();
    } catch (InvalidStateOperationException exception) {
      toCounterAttackedDefender = true;
    }

    try {
      recoveryState.toCounterAttackedEsquivar();
    } catch (InvalidStateOperationException exception) {
      toCounterAttackedEsquivar = true;
    }

    Assertions.assertTrue(toCounterAttackedEsquivar);
    Assertions.assertTrue(toCounterAttackedDefender);
    Assertions.assertTrue(toAttackedEsquivar);
    Assertions.assertTrue(toAttackedDefender);
    Assertions.assertTrue(toCounterAttacked);
    Assertions.assertTrue(toRecovery);
    Assertions.assertTrue(toActive);
    Assertions.assertTrue(toCounterAttack);
    Assertions.assertTrue(toAttack);
    Assertions.assertTrue(toMove);
    Assertions.assertTrue(toDetenido);
    Assertions.assertTrue(toEffect);

  }


  @Test
  public void fromStartTurn() {

    try {
      startTurn.toActiveState();
    } catch (InvalidStateOperationException exception) {
      toActive = true;
    }

    try {
      startTurn.toCounterAttackState();
    } catch (InvalidStateOperationException exception) {
      toCounterAttack = true;
    }

    try {
      startTurn.toAttack();
    } catch (InvalidStateOperationException exception) {
      toAttack = true;
    }

    try {
      startTurn.toInactiveState();
    } catch (InvalidStateOperationException exception) {
      toInactive = true;
    }

    try {
      startTurn.toDetenidoState();
    } catch (InvalidStateOperationException exception) {
      toDetenido = true;
    }

    try {
      startTurn.toEffectPanelState();
    } catch (InvalidStateOperationException exception) {
      toEffect = true;
    }

    try {
      startTurn.toCounterAttacked();
    } catch (InvalidStateOperationException exception) {
      toCounterAttacked = true;
    }

    try {
      startTurn.toAttackedDefender();
    } catch (InvalidStateOperationException exception) {
      toAttackedDefender = true;
    }

    try {
      startTurn.toAttackedEsquivar();
    } catch (InvalidStateOperationException exception) {
      toAttackedEsquivar = true;
    }

    try {
      startTurn.toCounterAttackedDefender();
    } catch (InvalidStateOperationException exception) {
      toCounterAttackedDefender = true;
    }

    try {
      startTurn.toCounterAttackedEsquivar();
    } catch (InvalidStateOperationException exception) {
      toCounterAttackedEsquivar = true;
    }

    Assertions.assertTrue(toCounterAttackedEsquivar);
    Assertions.assertTrue(toCounterAttackedDefender);
    Assertions.assertTrue(toAttackedEsquivar);
    Assertions.assertTrue(toAttackedDefender);
    Assertions.assertTrue(toCounterAttacked);
    Assertions.assertTrue(toActive);
    Assertions.assertTrue(toCounterAttack);
    Assertions.assertTrue(toAttack);
    Assertions.assertTrue(toInactive);
    Assertions.assertTrue(toDetenido);
    Assertions.assertTrue(toEffect);
  }


  @Test
  public void fromMove() {

    try {
      moveState.toRecoveryState();
    } catch (InvalidStateOperationException exception) {
      toRecovery = true;
    }

    try {
      moveState.toActiveState();
    } catch (InvalidStateOperationException exception) {
      toActive = true;
    }

    try {
      moveState.toCounterAttackState();
    } catch (InvalidStateOperationException exception) {
      toCounterAttack = true;
    }

    try {
      moveState.toAttack();
    } catch (InvalidStateOperationException exception) {
      toAttack = true;
    }

    try {
      moveState.toStartTurn();
    } catch (InvalidStateOperationException exception) {
      toStartTurn = true;
    }

    try {
      moveState.toEffectPanelState();
    } catch (InvalidStateOperationException exception) {
      toEffect = true;
    }

    try {
      moveState.toCounterAttacked();
    } catch (InvalidStateOperationException exception) {
      toCounterAttacked = true;
    }

    try {
      moveState.toAttackedDefender();
    } catch (InvalidStateOperationException exception) {
      toAttackedDefender = true;
    }

    try {
      moveState.toAttackedEsquivar();
    } catch (InvalidStateOperationException exception) {
      toAttackedEsquivar = true;
    }

    try {
      moveState.toCounterAttackedDefender();
    } catch (InvalidStateOperationException exception) {
      toCounterAttackedDefender = true;
    }

    try {
      moveState.toCounterAttackedEsquivar();
    } catch (InvalidStateOperationException exception) {
      toCounterAttackedEsquivar = true;
    }

    Assertions.assertTrue(toCounterAttackedEsquivar);
    Assertions.assertTrue(toCounterAttackedDefender);
    Assertions.assertTrue(toAttackedEsquivar);
    Assertions.assertTrue(toAttackedDefender);
    Assertions.assertTrue(toCounterAttacked);
    Assertions.assertTrue(toRecovery);
    Assertions.assertTrue(toActive);
    Assertions.assertTrue(toCounterAttack);
    Assertions.assertTrue(toAttack);
    Assertions.assertTrue(toStartTurn);
    Assertions.assertTrue(toEffect);

  }

  @Test
  public void fromDetendio() {

    try {
      detenido.toRecoveryState();
    } catch (InvalidStateOperationException exception) {
      toRecovery = true;
    }

    try {
      detenido.toActiveState();
    } catch (InvalidStateOperationException exception) {
      toActive = true;
    }

    try {
      detenido.toCounterAttackState();
    } catch (InvalidStateOperationException exception) {
      toCounterAttack = true;
    }

    try {
      detenido.toStartTurn();
    } catch (InvalidStateOperationException exception) {
      toStartTurn = true;
    }

    try {
      detenido.toDetenidoState();
    } catch (InvalidStateOperationException exception) {
      toDetenido = true;
    }

    try {
      detenido.toMoveState();
    } catch (InvalidStateOperationException exception) {
      toMove = true;
    }

    try {
      detenido.toCounterAttacked();
    } catch (InvalidStateOperationException exception) {
      toCounterAttacked = true;
    }

    try {
      detenido.toAttackedDefender();
    } catch (InvalidStateOperationException exception) {
      toAttackedDefender = true;
    }

    try {
      detenido.toAttackedEsquivar();
    } catch (InvalidStateOperationException exception) {
      toAttackedEsquivar = true;
    }

    try {
      detenido.toCounterAttackedDefender();
    } catch (InvalidStateOperationException exception) {
      toCounterAttackedDefender = true;
    }

    try {
      detenido.toCounterAttackedEsquivar();
    } catch (InvalidStateOperationException exception) {
      toCounterAttackedEsquivar = true;
    }

    Assertions.assertTrue(toCounterAttackedEsquivar);
    Assertions.assertTrue(toCounterAttackedDefender);
    Assertions.assertTrue(toAttackedEsquivar);
    Assertions.assertTrue(toAttackedDefender);
    Assertions.assertTrue(toCounterAttacked);
    Assertions.assertTrue(toRecovery);
    Assertions.assertTrue(toActive);
    Assertions.assertTrue(toCounterAttack);
    Assertions.assertTrue(toStartTurn);
    Assertions.assertTrue(toDetenido);
    Assertions.assertTrue(toMove);

  }

  @Test
  public void fromEffectPanel() {

    try {
      effectPanel.toRecoveryState();
    } catch (InvalidStateOperationException exception) {
      toRecovery = true;
    }

    try {
      effectPanel.toActiveState();
    } catch (InvalidStateOperationException exception) {
      toActive = true;
    }

    try {
      effectPanel.toCounterAttackState();
    } catch (InvalidStateOperationException exception) {
      toCounterAttack = true;
    }

    try {
      effectPanel.toStartTurn();
    } catch (InvalidStateOperationException exception) {
      toStartTurn = true;
    }

    try {
      effectPanel.toDetenidoState();
    } catch (InvalidStateOperationException exception) {
      toDetenido = true;
    }

    try {
      effectPanel.toMoveState();
    } catch (InvalidStateOperationException exception) {
      toMove = true;
    }

    try {
      effectPanel.toCounterAttacked();
    } catch (InvalidStateOperationException exception) {
      toCounterAttacked = true;
    }

    try {
      effectPanel.toAttackedDefender();
    } catch (InvalidStateOperationException exception) {
      toAttackedDefender = true;
    }

    try {
      effectPanel.toAttackedEsquivar();
    } catch (InvalidStateOperationException exception) {
      toAttackedEsquivar = true;
    }

    try {
      effectPanel.toCounterAttackedDefender();
    } catch (InvalidStateOperationException exception) {
      toCounterAttackedDefender = true;
    }

    try {
      effectPanel.toCounterAttackedEsquivar();
    } catch (InvalidStateOperationException exception) {
      toCounterAttackedEsquivar = true;
    }

    Assertions.assertTrue(toCounterAttackedEsquivar);
    Assertions.assertTrue(toCounterAttackedDefender);
    Assertions.assertTrue(toAttackedEsquivar);
    Assertions.assertTrue(toAttackedDefender);
    Assertions.assertTrue(toCounterAttacked);
    Assertions.assertTrue(toRecovery);
    Assertions.assertTrue(toActive);
    Assertions.assertTrue(toCounterAttack);
    Assertions.assertTrue(toStartTurn);
    Assertions.assertTrue(toDetenido);
    Assertions.assertTrue(toMove);

  }

  @Test
  public void fromCounterAttack() {

    try {
      counterAttack.toActiveState();
    } catch (InvalidStateOperationException exception) {
      toActive = true;
    }

    try {
      counterAttack.toCounterAttackState();
    } catch (InvalidStateOperationException exception) {
      toCounterAttack = true;
    }

    try {
      counterAttack.toAttack();
    } catch (InvalidStateOperationException exception) {
      toAttack = true;
    }

    try {
      counterAttack.toStartTurn();
    } catch (InvalidStateOperationException exception) {
      toStartTurn = true;
    }

    try {
      counterAttack.toDetenidoState();
    } catch (InvalidStateOperationException exception) {
      toDetenido = true;
    }

    try {
      counterAttack.toMoveState();
    } catch (InvalidStateOperationException exception) {
      toMove = true;
    }

    try {
      counterAttack.toRecoveryState();
    } catch (InvalidStateOperationException exception) {
      toRecovery = true;
    }

    try {
      counterAttack.toCounterAttacked();
    } catch (InvalidStateOperationException exception) {
      toCounterAttacked = true;
    }

    try {
      counterAttack.toAttackedDefender();
    } catch (InvalidStateOperationException exception) {
      toAttackedDefender = true;
    }

    try {
      counterAttack.toAttackedEsquivar();
    } catch (InvalidStateOperationException exception) {
      toAttackedEsquivar = true;
    }

    try {
      counterAttack.toCounterAttackedDefender();
    } catch (InvalidStateOperationException exception) {
      toCounterAttackedDefender = true;
    }

    try {
      counterAttack.toCounterAttackedEsquivar();
    } catch (InvalidStateOperationException exception) {
      toCounterAttackedEsquivar = true;
    }

    Assertions.assertTrue(toCounterAttackedEsquivar);
    Assertions.assertTrue(toCounterAttackedDefender);
    Assertions.assertTrue(toAttackedEsquivar);
    Assertions.assertTrue(toAttackedDefender);
    Assertions.assertTrue(toCounterAttacked);
    Assertions.assertTrue(toRecovery);
    Assertions.assertTrue(toActive);
    Assertions.assertTrue(toCounterAttack);
    Assertions.assertTrue(toAttack);
    Assertions.assertTrue(toStartTurn);
    Assertions.assertTrue(toDetenido);
    Assertions.assertTrue(toMove);

  }
}