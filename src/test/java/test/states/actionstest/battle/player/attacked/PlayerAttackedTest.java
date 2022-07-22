package test.states.actionstest.battle.player.attacked;

import cl.uchile.dcc.citricliquid.model.exeptions.InvalidStateOperationException;
import cl.uchile.dcc.citricliquid.model.personaje.player.Player;
import cl.uchile.dcc.citricliquid.model.state.states.battle.defender.attacked.AttackedDefender;
import cl.uchile.dcc.citricliquid.model.state.states.battle.defender.attacked.AttackedEsquivar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;


/**
 * test when a player is attacked.
 *
 */
public class PlayerAttackedTest {
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
  //Test al recibir un ataque un boss
  @RepeatedTest(100)
  public void attackedDefenderTest() throws InvalidStateOperationException {
    suguri.setState(new AttackedDefender(suguri));
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
  public void attackedEsquiveTest() throws InvalidStateOperationException {
    suguri.setState(new AttackedEsquivar(suguri));
    int hp = suguri.getMaxHp();
    int des = suguri.attacked(4);
    if (des == 0) {
      Assertions.assertEquals(hp, suguri.getCurrentHp());
    } else {
      Assertions.assertEquals(Math.max(0, hp - des), suguri.getCurrentHp());
    }
  }

  //Suguri está esn estado inactive. Desde acá, no se puede llamar a attacked.
  //Se prueba que se da lal exepción.
  @Test
  public void exceptionTest() {
    boolean e = false;
    try {
      suguri.attacked(4);
    } catch (InvalidStateOperationException ex) {
      e = true;
    }
    Assertions.assertTrue(e);
  }



}
