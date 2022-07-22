package test.controller.listener;

import cl.uchile.dcc.citricliquid.model.controller.Controller;
import cl.uchile.dcc.citricliquid.model.paneles.types.enemies.BossPanel;
import cl.uchile.dcc.citricliquid.model.personaje.player.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test Norma4 handler.
 *
 */
public class Norma4ListenerTest {
  Controller controller;
  Player p1;
  Player p2;
  Player p3;
  Player p4;

  BossPanel bossPanel1;
  BossPanel bossPanel2;
  BossPanel bossPanel3;
  BossPanel bossPanel4;

  /**
   * Create the objet we need in test.
   *
   */
  @BeforeEach
  public void set_up() {
    controller = new Controller();

    p1 = controller.createPlayer("P1", 4, 4, 4, 4);
    p2 = controller.createPlayer("P2", 4, 4, 4, 4);
    p3 = controller.createPlayer("P3", 4, 4, 4, 4);
    p4 = controller.createPlayer("P4", 4, 4, 4, 4);

    bossPanel1 = controller.createBossPanel();
    bossPanel2 = controller.createBossPanel();
    bossPanel3 = controller.createBossPanel();
    bossPanel4 = controller.createBossPanel();
  }

  @Test
  public void test() {
    Assertions.assertNull(bossPanel1.getRival());
    Assertions.assertNull(bossPanel2.getRival());
    Assertions.assertNull(bossPanel3.getRival());
    Assertions.assertNull(bossPanel4.getRival());

    p1.normaClear();
    p1.normaClear();
    p1.normaClear();

    Assertions.assertNotNull(bossPanel1.getRival());
    Assertions.assertNotNull(bossPanel2.getRival());
    Assertions.assertNotNull(bossPanel3.getRival());
    Assertions.assertNotNull(bossPanel4.getRival());

    Assertions.assertEquals(bossPanel1.getRival(), bossPanel2.getRival());
    Assertions.assertEquals(bossPanel2.getRival(), bossPanel3.getRival());
    Assertions.assertEquals(bossPanel3.getRival(), bossPanel4.getRival());

    Assertions.assertEquals(bossPanel1.getRival().getName(), bossPanel2.getRival().getName());

  }


}
