package test.states.transitiontest;

import cl.uchile.dcc.citricliquid.model.exeptions.InvalidStateOperationException;
import cl.uchile.dcc.citricliquid.model.personaje.player.Player;
import cl.uchile.dcc.citricliquid.model.state.states.battle.atacar.Attack;
import cl.uchile.dcc.citricliquid.model.state.states.battle.atacar.CounterAttack;
import cl.uchile.dcc.citricliquid.model.state.states.battle.defender.attacked.AttackedState;
import cl.uchile.dcc.citricliquid.model.state.states.battle.defender.counterattacked.CounterAttacked;
import cl.uchile.dcc.citricliquid.model.state.states.turnstate.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test the transitions of a player.
 *
 */
public class PlayerTransitionTest {

  private static final String PLAYER_NAME = "Suguri";
  private Player suguri;

  /**
   * create elements for all the test.
   *
   */
  @BeforeEach
  public void setUp() {
    suguri = new Player(PLAYER_NAME, 4, 1, -1, 2);
  }

  @Test
  public void setStateTest() {
    Assertions.assertTrue(suguri.isInactive());

    suguri.setState(new Active(suguri));
    Assertions.assertTrue(suguri.isActive());

    suguri.setState(new DetenidoState(suguri));
    Assertions.assertTrue(suguri.isDetenidoState());

    suguri.setState(new EffectPanelState(suguri));
    Assertions.assertTrue(suguri.isEffectPanelState());

    suguri.setState(new MoveState(suguri));
    Assertions.assertTrue(suguri.isMoveState());

    suguri.setState(new RecoveryState(suguri));
    Assertions.assertTrue(suguri.isRecoveryState());

    suguri.setState(new StartTurn(suguri));
    Assertions.assertTrue(suguri.isStartTurn());
  }


  /**
   * test transition and condition when player is attacked.
   *
   */
  @Test
  public void attackedStatesTest() throws InvalidStateOperationException {
    suguri.toAttackedState();
    Assertions.assertTrue(suguri.isAttackedState());


    //defiende y contraataca
    suguri.setState(new AttackedState(suguri));
    suguri.toAttackedDefender();
    Assertions.assertTrue(suguri.isAttackedDefender());
    suguri.toCounterAttackState();
    Assertions.assertTrue(suguri.isCounterAttack());
    suguri.toInactiveState();
    Assertions.assertTrue(suguri.isInactive());


    //defiende y muere.
    suguri.setState(new AttackedState(suguri));
    suguri.toAttackedDefender();
    suguri.toInactiveState();
    Assertions.assertTrue(suguri.isAttackedDefender());
    suguri.setCurrentHp(0);
    suguri.toInactiveState();
    Assertions.assertTrue(suguri.isInactive());


    //esquiva y contraataca.
    suguri.setState(new AttackedState(suguri));
    suguri.toAttackedEsquivar();
    Assertions.assertTrue(suguri.isAttackedEsquivar());
    suguri.setCurrentHp(2);
    suguri.toCounterAttackState();

    Assertions.assertTrue(suguri.isCounterAttack());
    suguri.toInactiveState();
    Assertions.assertTrue(suguri.isInactive());

    //Esquiva y muere.
    suguri.setState(new AttackedState(suguri));
    suguri.toAttackedEsquivar();
    suguri.toInactiveState();
    Assertions.assertTrue(suguri.isAttackedEsquivar());
    suguri.setCurrentHp(0);
    suguri.toInactiveState();
    Assertions.assertTrue(suguri.isInactive());
  }

  /**
   * test transition and condition
   * for player's start.
   *
   */
  @Test
  public void inactiveToActiveTest() throws InvalidStateOperationException {
    //camino uno.
    suguri.toActiveState();
    suguri.toStartTurn();
    Assertions.assertTrue(suguri.isStartTurn());
    //Camino dos
    suguri.setState(new Inactive(suguri));
    suguri.setCurrentHp(0);
    suguri.toActiveState();
    suguri.toRecoveryState();
    Assertions.assertTrue(suguri.isRecoveryState());
    suguri.toStartTurn();
    Assertions.assertTrue(suguri.isStartTurn());

  }

  /**
   * test for look a turn without battle.
   *
   */
  @Test
  public void startTurnTransitions() throws InvalidStateOperationException {
    //turno sin batalla
    suguri.setState(new StartTurn(suguri));
    suguri.toMoveState();
    Assertions.assertTrue(suguri.isMoveState());
    suguri.toDetenidoState();
    Assertions.assertTrue(suguri.isDetenidoState());
    suguri.toEffectPanelState();
    Assertions.assertTrue(suguri.isEffectPanelState());
    suguri.toInactiveState();
    Assertions.assertTrue(suguri.isInactive());

  }


  /**
   * test for all transition that can start a battle.
   *
   */
  @Test
  public void starbattles() throws InvalidStateOperationException {
    //uno
    suguri.setState(new DetenidoState(suguri));
    suguri.toAttack();
    Assertions.assertTrue(suguri.isAttack());

    //Dos
    suguri.setState(new EffectPanelState(suguri));
    suguri.toAttack();
    Assertions.assertTrue(suguri.isAttack());
  }


  /**
   * test for all transition beeing counterattacked.
   *
   */
  @Test
  public void counterAttackTransitions() throws InvalidStateOperationException {
    suguri.setState(new Attack(suguri));
    suguri.toCounterAttacked();
    Assertions.assertTrue(suguri.isCounterAttacked());
    //camino uno
    suguri.toCounterAttackedDefender();
    Assertions.assertTrue(suguri.isCounterDefender());
    suguri.toInactiveState();
    Assertions.assertTrue(suguri.isInactive());

    //Camino dos
    suguri.setState(new CounterAttacked(suguri));
    suguri.toCounterAttackedEsquivar();
    Assertions.assertTrue(suguri.isCounterEsquivar());
    suguri.toInactiveState();
    Assertions.assertTrue(suguri.isInactive());

  }

  /**
   * test for all transition beeing counterattacked.
   *
   */
  @Test
  public void conterattacktransitiontest() throws InvalidStateOperationException {
    suguri.setState(new CounterAttack(suguri));
    suguri.toInactiveState();
    Assertions.assertTrue(suguri.isInactive());
  }

  @Test
  public void attackTransitions() throws InvalidStateOperationException {
    suguri.setState(new Attack(suguri));
    suguri.toInactiveState();
    Assertions.assertTrue(suguri.isInactive());
  }





}
