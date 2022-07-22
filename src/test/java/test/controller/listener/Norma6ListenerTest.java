package test.controller.listener;

import cl.uchile.dcc.citricliquid.model.controller.Controller;
import cl.uchile.dcc.citricliquid.model.personaje.player.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Test for listener when we have a norma level 6.
 *
 */
public class Norma6ListenerTest {
  Controller controller;
  Player p1;
  Player p2;
  Player p3;
  Player p4;

  /**
   * Create the elements we need for testing.
   *
   */
  @BeforeEach
  public void set_up() {
    controller = new Controller();

    p1 = controller.createPlayer("P1", 4, 4, 4, 4);
    p2 = controller.createPlayer("P2", 4, 4, 4, 4);
    p3 = controller.createPlayer("P3", 4, 4, 4, 4);
    p4 = controller.createPlayer("P4", 4, 4, 4, 4);
  }

  @Test
  public void test() {
    Assertions.assertNull(controller.getWinner());
    Assertions.assertEquals(1, p1.getNormaLevel());
    p3.normaClear();
    p1.normaClear();
    p1.normaClear();
    p1.normaClear();
    p2.normaClear();
    p1.normaClear();
    p4.normaClear();
    p1.normaClear();
    Assertions.assertEquals("P1", controller.getWinner().getName());
  }


}
