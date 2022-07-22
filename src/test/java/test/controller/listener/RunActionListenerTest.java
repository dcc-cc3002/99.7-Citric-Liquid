package test.controller.listener;

import cl.uchile.dcc.citricliquid.model.controller.Controller;
import cl.uchile.dcc.citricliquid.model.exeptions.InvalidStateOperationException;
import cl.uchile.dcc.citricliquid.model.personaje.player.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test the listener that heatd the states and run the method runAction.
 *
 */
public class RunActionListenerTest {
  Controller controller;
  Player p1;

  /**
   * Create the elements we need for testing.
   *
   */
  @BeforeEach
  public void set_up() {
    controller = new Controller();
    p1 = controller.createPlayer("P1", 4, 4, 4, 4);
  }

  @Test
  public void listenerTest() throws InvalidStateOperationException {
    Assertions.assertTrue(p1.isInactive());
    p1.toActiveState();
    Assertions.assertFalse(p1.isActive());
  }

}
