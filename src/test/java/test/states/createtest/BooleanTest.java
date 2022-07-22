package test.states.createtest;

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
 * Test for boolean values of states.
 *
 */
public class BooleanTest {

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

  }

  @Test
  public void turnBoolTest() {
    Assertions.assertFalse(player.isActive());
    player.setState(active);
    Assertions.assertTrue(player.isActive());

    Assertions.assertFalse(player.isDetenidoState());
    player.setState(detenido);
    Assertions.assertTrue(player.isDetenidoState());

    Assertions.assertFalse(player.isEffectPanelState());
    player.setState(effectPanel);
    Assertions.assertTrue(player.isEffectPanelState());

    Assertions.assertFalse(player.isInactive());
    player.setState(inactive);
    Assertions.assertTrue(player.isInactive());

    Assertions.assertFalse(player.isMoveState());
    player.setState(moveState);
    Assertions.assertTrue(player.isMoveState());

    Assertions.assertFalse(player.isRecoveryState());
    player.setState(recoveryState);
    Assertions.assertTrue(player.isRecoveryState());

    Assertions.assertFalse(player.isStartTurn());
    player.setState(startTurn);
    Assertions.assertTrue(player.isStartTurn());

  }

  @Test
  public void battleBooleanTest() {
    Assertions.assertFalse(player.isAttack());
    player.setState(attack);
    Assertions.assertTrue(player.isAttack());

    Assertions.assertFalse(player.isCounterAttack());
    player.setState(counterAttack);
    Assertions.assertTrue(player.isCounterAttack());

    Assertions.assertFalse(player.isAttackedDefender());
    player.setState(atDefender);
    Assertions.assertTrue(player.isAttackedDefender());

    Assertions.assertFalse(player.isAttackedEsquivar());
    player.setState(atEsquivar);
    Assertions.assertTrue(player.isAttackedEsquivar());

    Assertions.assertFalse(player.isAttackedState());
    player.setState(attacked);
    Assertions.assertTrue(player.isAttackedState());

    Assertions.assertFalse(player.isCounterAttacked());
    player.setState(coAttacked);
    Assertions.assertTrue(player.isCounterAttacked());

    Assertions.assertFalse(player.isCounterDefender());
    player.setState(coDefender);
    Assertions.assertTrue(player.isCounterDefender());

    Assertions.assertFalse(player.isCounterEsquivar());
    player.setState(coEsquivar);
    Assertions.assertTrue(player.isCounterEsquivar());


  }
}
