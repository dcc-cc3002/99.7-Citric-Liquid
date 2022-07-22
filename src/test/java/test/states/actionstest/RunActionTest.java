package test.states.actionstest;

import cl.uchile.dcc.citricliquid.model.exeptions.InvalidStateOperationException;
import cl.uchile.dcc.citricliquid.model.personaje.player.Player;
import cl.uchile.dcc.citricliquid.model.state.states.turnstate.Active;
import cl.uchile.dcc.citricliquid.model.state.states.turnstate.RecoveryState;
import cl.uchile.dcc.citricliquid.model.state.states.turnstate.StartTurn;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

/**
 *  test for runAction() in state.
 *
 */
public class RunActionTest {
  private Player suguri;

  @BeforeEach
  public void setUp() {
    suguri = new Player("Javi", 4, 1, -1, 2);
  }

  //Se prueba que al llamar runAction desde un estado no permitido (inactive)
  //de una exepción.
  @Test
  public void exeptionTest() {
    boolean e = false;
    try {
      suguri.runContextAction();
    } catch (InvalidStateOperationException ex) {
      e = true;
    }
    Assertions.assertTrue(e);
  }

  @Test
  public void activatetoStartTurnTest() throws InvalidStateOperationException {
    suguri.setState(new Active(suguri));
    suguri.runContextAction();
    Assertions.assertTrue(suguri.isStartTurn());

  }

  @Test
  public void activatetoRecoveryTurnTest() throws InvalidStateOperationException {
    suguri.setState(new Active(suguri));
    suguri.setCurrentHp(0);
    suguri.runContextAction();
    Assertions.assertTrue(suguri.isRecoveryState());

  }

  /**
   * we know the result because tha chapter is 6.
   *
   */
  @RepeatedTest(50)
  public void recoverytoStartTurnTest() throws InvalidStateOperationException {
    suguri.setState(new RecoveryState(suguri));
    suguri.setChapters(6);
    suguri.runContextAction();
    Assertions.assertTrue(suguri.isStartTurn());

  }

  //we know the result because tha chapter is 0.
  @Test
  public void recoverytoInactiveTest() throws InvalidStateOperationException {
    suguri.setState(new RecoveryState(suguri));
    suguri.runContextAction();
    Assertions.assertTrue(suguri.isInactive());
  }

  @Test
  public void startTurn() throws InvalidStateOperationException {
    int i = 1;
    int exp = 0;
    while (i < 7) {
      suguri.setState(new StartTurn(suguri));
      suguri.setChapters(i);
      suguri.runContextAction();
      int newStars = suguri.getStars();
      exp = exp + (int) Math.floor(1 + ((float) i) / 5);
      Assertions.assertEquals(exp, newStars);
      System.out.println(exp);
      i++;
    }
  }

}
