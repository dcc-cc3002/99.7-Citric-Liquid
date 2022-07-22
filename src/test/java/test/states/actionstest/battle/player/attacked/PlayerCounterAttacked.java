package test.states.actionstest.battle.player.attacked;

import cl.uchile.dcc.citricliquid.model.exeptions.InvalidStateOperationException;
import cl.uchile.dcc.citricliquid.model.personaje.player.Player;
import cl.uchile.dcc.citricliquid.model.state.states.battle.defender.counterattacked.CounterDefender;
import cl.uchile.dcc.citricliquid.model.state.states.battle.defender.counterattacked.CounterEsquivar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

/**
 * Class for test when a player is counter-attacked.
 *
 */
public class PlayerCounterAttacked {

  private Player suguri;

  /**
   * create elements for all the test.
   * In this case, it is just a player.
   *
   */
  @BeforeEach
  public void setUp() {
    suguri = new Player("Juan", 4, 1, -1, 2);
  }

  /**
   * test when te player decide to defender.
   *
   */
  //Test al recibir un ataque.
  @RepeatedTest(100)
  public void counterattackedDefenderTest() throws InvalidStateOperationException {
    suguri.setState(new CounterDefender(suguri));
    int hp = suguri.getMaxHp();
    int des = suguri.attacked(4);
    Assertions.assertTrue(suguri.getCurrentHp() < hp);
    Assertions.assertEquals(Math.max(0, hp - des), suguri.getCurrentHp());
  }

  /**
   * test when te player decide to esquive.
   *
   */
  @RepeatedTest(100)
  public void counterattackedEsquiveTest() throws InvalidStateOperationException {
    suguri.setState(new CounterEsquivar(suguri));
    int hp = suguri.getMaxHp();
    int des = suguri.attacked(4);
    if (des == 0) {
      Assertions.assertEquals(hp, suguri.getCurrentHp());
    } else {
      Assertions.assertEquals(Math.max(0, hp - des), suguri.getCurrentHp());
    }
  }
}
