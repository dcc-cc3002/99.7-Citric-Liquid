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
import cl.uchile.dcc.citricliquid.model.state.states.turnstate.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class for secuences of states. It is for make sure that transition
 * are fine.
 *
 */
public class SecuencesTest {
  AbstractState inactive;
  AbstractState start;
  AbstractState recovery;
  AbstractState detenido;
  AbstractState active;
  AbstractState effect;
  AbstractState move;


  AbstractState attack;
  AbstractState counterAttack;
  AbstractState attacked;
  AbstractState atEsqui;
  AbstractState atDef;
  AbstractState co;
  AbstractState coEsqui;
  AbstractState coDef;

  /**
   * Create elements for all the test. Including a player and states for testing.
   *
   */
  @BeforeEach
  public void setUp() {
    Player player = new Player("j", 3, 3, 3, 3);
    inactive = new Inactive(player);
    start = new StartTurn(player);
    recovery = new RecoveryState(player);
    detenido = new DetenidoState(player);
    active = new Active(player);
    effect = new EffectPanelState(player);
    move = new MoveState(player);

    attack = new Attack(player);
    counterAttack = new CounterAttack(player);
    attacked = new AttackedState(player);
    atEsqui = new AttackedEsquivar(player);
    atDef = new AttackedDefender(player);
    co = new CounterAttacked(player);
    coDef = new CounterDefender(player);
    coEsqui = new CounterDefender(player);


  }

  //Se comprueba el turno básico. Cuando no se inicia batalla en ningún momento.
  @Test
  public void tunrtest() throws InvalidStateOperationException {
    inactive.toActiveState();
    active.toStartTurn();
    start.toMoveState();
    move.toDetenidoState();
    detenido.toEffectPanelState();
    effect.toInactiveState();
  }

  //se comprueban las transiciones cuando un jugador pasa a attack.
  @Test
  public void atattackTest() throws InvalidStateOperationException {
    attack.toCounterAttacked();
    co.toCounterAttackedDefender();
    co.toCounterAttackedEsquivar();
    coDef.toInactiveState();
    coEsqui.toInactiveState();

  }

  //Se prueba las transiciones posibles cuando un jugador pasa de inactivo
  //a attacked.
  @Test
  public void atattackedTest() throws InvalidStateOperationException {
    inactive.toAttackedState();
    attacked.toAttackedDefender();
    attacked.toAttackedEsquivar();
    atDef.toInactiveState();
    atDef.toCounterAttackState();
    atEsqui.toInactiveState();
    atEsqui.toCounterAttackState();
    counterAttack.toInactiveState();
  }



}
