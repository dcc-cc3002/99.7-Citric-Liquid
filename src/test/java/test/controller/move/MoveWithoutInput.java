package test.controller.move;

import cl.uchile.dcc.citricliquid.model.controller.Controller;
import cl.uchile.dcc.citricliquid.model.exeptions.InvalidStateOperationException;
import cl.uchile.dcc.citricliquid.model.paneles.types.DrawPanel;
import cl.uchile.dcc.citricliquid.model.paneles.types.HomePanel;
import cl.uchile.dcc.citricliquid.model.paneles.types.NeutralPanel;
import cl.uchile.dcc.citricliquid.model.paneles.types.enemies.BossPanel;
import cl.uchile.dcc.citricliquid.model.paneles.types.star.BonusPanel;
import cl.uchile.dcc.citricliquid.model.paneles.types.star.DropPanel;
import cl.uchile.dcc.citricliquid.model.personaje.player.Player;
import cl.uchile.dcc.citricliquid.model.state.states.turnstate.MoveState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Testing the move of a panel in the board when have just
 * one way to follow.
 *
 */
public class MoveWithoutInput {

  Controller controller;
  Player player;

  DrawPanel drawPanel;
  HomePanel homePanel;
  NeutralPanel neutralPanel;
  NeutralPanel neutralPanel2;
  BossPanel bossPanel;
  BonusPanel bonusPanel;
  DropPanel dropPanel;

  /**
   * create objets to test.
   *
   */
  @BeforeEach
  public void set_up() {
    controller = new Controller();

    player = controller.createPlayer("Juan", 4, 1, -1, 2);

    neutralPanel = controller.createNeutralPanel();
    drawPanel = controller.createDrawPanel();
    homePanel =  controller.createHomePanel(player);
    neutralPanel2 = controller.createNeutralPanel();
    bonusPanel = controller.createBonusPanel();
    dropPanel = controller.createDropPanel();
    bossPanel = controller.createBossPanel();

    controller.addNextPanel(neutralPanel, drawPanel);
    controller.addNextPanel(drawPanel, bonusPanel);
    controller.addNextPanel(bonusPanel, homePanel);
    controller.addNextPanel(homePanel, neutralPanel2);
    controller.addNextPanel(neutralPanel2, bossPanel);
    controller.addNextPanel(bossPanel, neutralPanel);

    controller.setPlayerInPanel(player, neutralPanel);
    player.setState(new MoveState(player));

  }

  @Test
  public void boleanTest() {
    Player p2 = new Player("j", 2, 2, 2, 2);
    Player p3 = new Player("k", 2, 2, 2, 2);
    controller.setPlayerInPanel(p2, bossPanel);
    Assertions.assertFalse(controller.askToStopforBattle(p2));
    controller.setPlayerInPanel(p3, bossPanel);
    Assertions.assertTrue(controller.askToStopforBattle(p2));
    Assertions.assertFalse(controller.askToStopforHomePanel(p2));

    Player p4 = new Player("s", 2, 2, 2, 2);
    HomePanel h4 =  controller.createHomePanel(p4);
    controller.setPlayerInPanel(p4, h4);
    Assertions.assertTrue(controller.askToStopforHomePanel(p4));
  }

  @Test
  public void moveOnePanel() throws InvalidStateOperationException {
    controller.move(player, 1, false, false);
    Assertions.assertTrue(drawPanel.getJugadores().contains(player));
    Assertions.assertTrue(player.isDetenidoState());
  }

  @Test
  public void moverseVariosPaneles() throws InvalidStateOperationException {
    controller.move(player, 4, false, false);
    Assertions.assertTrue(neutralPanel2.getJugadores().contains(player));
    Assertions.assertTrue(player.isDetenidoState());
  }

  @Test
  public void stopHomePanel() throws InvalidStateOperationException {
    player.setState(new MoveState(player));
    int moves = controller.move(player, 5, false, true);
    Assertions.assertTrue(homePanel.getJugadores().contains(player));
    Assertions.assertEquals(3, moves);
    Assertions.assertTrue(player.isDetenidoState());
  }

  @Test
  public void stopforbattle() throws InvalidStateOperationException {
    bonusPanel.addPlayer(new Player("a", 2, 3, 4, 4));
    int moves = controller.move(player, 6, true, false);
    Assertions.assertTrue(bonusPanel.getJugadores().contains(player));
    Assertions.assertEquals(-1, moves);
    Assertions.assertTrue(player.isDetenidoState());
  }

}
