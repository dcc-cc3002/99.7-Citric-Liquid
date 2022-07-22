package test.controller.checks;

import cl.uchile.dcc.citricliquid.model.controller.Controller;
import cl.uchile.dcc.citricliquid.model.personaje.enemies.boss.BossUnit;
import cl.uchile.dcc.citricliquid.model.personaje.enemies.wild.WildUnit;
import cl.uchile.dcc.citricliquid.model.personaje.player.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *  Test the turn.
 *
 */
public class Turns {

  Controller controller;
  Player player1;

  Player player2;

  Player player3;

  Player player4;
  WildUnit wildUnit;
  BossUnit bossUnit;

  /**
   * create elements for all the test.
   *
   */
  @BeforeEach
  public void set_up() {
    controller = new Controller();

    player1 = controller.createPlayer("player1", 4, 2, 1, 4);
    player2 = controller.createPlayer("player2", 4, 3, 2, 3);
    player3 = controller.createPlayer("player3", 4, 4, 1, 5);
    player4 = controller.createPlayer("player4", 5, 1, 1, 5);

  }

  @Test
  public void test() {
    Assertions.assertEquals("player1", controller.getPlayers()
        .get(controller.getActualTurn()).getName());

    controller.nextTurn();
    Assertions.assertEquals("player2", controller.getPlayers()
        .get(controller.getActualTurn()).getName());

    controller.nextTurn();
    Assertions.assertEquals("player3", controller.getPlayers()
        .get(controller.getActualTurn()).getName());

    controller.nextTurn();
    Assertions.assertEquals("player4", controller.getPlayers()
        .get(controller.getActualTurn()).getName());

    controller.nextTurn();
    Assertions.assertEquals("player1", controller.getPlayers()
        .get(controller.getActualTurn()).getName());
  }

}
