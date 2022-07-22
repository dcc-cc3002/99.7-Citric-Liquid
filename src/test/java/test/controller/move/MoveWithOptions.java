package test.controller.move;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 *  test panels move when only have one possible way.
 *
 */
public class MoveWithOptions {
  Controller controller;
  Player player;

  DrawPanel drawPanel;
  HomePanel homePanel;
  NeutralPanel neutralPanel;

  NeutralPanel neutralPanel1;
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
    neutralPanel1 = controller.createNeutralPanel();
    drawPanel = controller.createDrawPanel();
    homePanel =  controller.createHomePanel(player);
    neutralPanel2 = controller.createNeutralPanel();
    bonusPanel = controller.createBonusPanel();
    dropPanel = controller.createDropPanel();
    bossPanel = controller.createBossPanel();

    controller.addNextPanel(neutralPanel, neutralPanel1);
    controller.addNextPanel(neutralPanel1, drawPanel);

    controller.addNextPanel(neutralPanel1, homePanel);

    controller.addNextPanel(drawPanel, bonusPanel);
    controller.addNextPanel(bonusPanel, neutralPanel);

    controller.addNextPanel(homePanel, neutralPanel2);
    controller.addNextPanel(neutralPanel2, bossPanel);
    controller.addNextPanel(bossPanel, neutralPanel);

    controller.setPlayerInPanel(player, neutralPanel);
    player.setState(new MoveState(player));

  }

  //En caso de haber más de un posible camino, al utilizar el método move del controlador
  //se va a saber porque la cantida9d de movimientos es menor a la pedida. Si tenemos esto,
  //se puede usar el método setWay para hacer que se mueva en una dirección.

  @Test
  public void moverseInputFirstWay() throws InvalidStateOperationException {
    assertTrue(neutralPanel.getJugadores().contains(player));
    int move = controller.move(player, 4, false, false);
    assertEquals(1, move);
    assertEquals(MoveState.class, player.getState().getClass());
    assertTrue(neutralPanel1.getJugadores().contains(player));

    controller.setWay(player, 1);
    assertTrue(!neutralPanel1.getJugadores().contains(player));
    assertTrue(homePanel.getJugadores().contains(player));
    controller.move(player, 2, false, false);
    assertTrue(bossPanel.getJugadores().contains(player));

  }

  @Test
  public void moverseInputSecondWay() throws InvalidStateOperationException {
    assertTrue(neutralPanel.getJugadores().contains(player));
    controller.move(player, 4, false, false);
    controller.setWay(player, 0);

    assertTrue(!neutralPanel.getJugadores().contains(player));
    assertTrue(drawPanel.getJugadores().contains(player));
    controller.move(player, 2, false, false);
    assertTrue(neutralPanel.getJugadores().contains(player));
  }
}
