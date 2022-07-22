package test.controller.checks.panels;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import cl.uchile.dcc.citricliquid.model.controller.Controller;
import cl.uchile.dcc.citricliquid.model.paneles.types.DrawPanel;
import cl.uchile.dcc.citricliquid.model.paneles.types.HomePanel;
import cl.uchile.dcc.citricliquid.model.paneles.types.NeutralPanel;
import cl.uchile.dcc.citricliquid.model.paneles.types.enemies.BossPanel;
import cl.uchile.dcc.citricliquid.model.paneles.types.enemies.EncounterPanel;
import cl.uchile.dcc.citricliquid.model.paneles.types.star.BonusPanel;
import cl.uchile.dcc.citricliquid.model.paneles.types.star.DropPanel;
import cl.uchile.dcc.citricliquid.model.personaje.enemies.wild.WildType;
import cl.uchile.dcc.citricliquid.model.personaje.enemies.wild.WildUnit;
import cl.uchile.dcc.citricliquid.model.personaje.player.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * This class test panels creates in the controller.
 *
 */
public class CreatePanelTest {
  private Controller controller;
  private Player player;

  DrawPanel drawPanel;
  HomePanel homePanel;
  NeutralPanel neutralPanel;
  BossPanel bossPanel;
  BonusPanel bonusPanel;
  DropPanel dropPanel;
  EncounterPanel encounterPanel;

  /**
   * create elements for all the test.
   *
   */
  @BeforeEach
  public void set_up() {
    controller = new Controller();
    player = controller.createPlayer("Juan", 4, 1, -1, 2);
    drawPanel = controller.createDrawPanel();
    homePanel =  controller.createHomePanel(player);
    neutralPanel = controller.createNeutralPanel();
    bonusPanel = controller.createBonusPanel();
    dropPanel = controller.createDropPanel();
    bossPanel = controller.createBossPanel();
    encounterPanel = controller.createEncounterPanel();
  }

  @Test
  public void constructorsPanelTest() {
    Assertions.assertTrue(drawPanel.isDrawPanel());
    Assertions.assertTrue(homePanel.isHomePanel());
    Assertions.assertTrue(neutralPanel.isNeutralPanel());
    Assertions.assertTrue(bonusPanel.isBonusPanel());
    Assertions.assertTrue(dropPanel.isDropPanel());
    Assertions.assertTrue(bossPanel.isBossPanel());
    Assertions.assertTrue(encounterPanel.isEncounterPanel());
  }

  @Test
  public void addnextpanelsTest() {
    controller.addNextPanel(neutralPanel, bonusPanel);
    assertEquals(1, neutralPanel.getNextPanels().size());
    controller.addNextPanel(neutralPanel, dropPanel);
    assertEquals(2, neutralPanel.getNextPanels().size());
  }

  @Test
  public void addPanelsTest() {
    assertEquals(7, controller.getPaneles().size());
    controller.createNeutralPanel();
    assertEquals(8, controller.getPaneles().size());
  }

  @Test
  public void setplayerinpanelTest() {
    assertEquals(neutralPanel.getJugadores().size(), 0);
    controller.setPlayerInPanel(player, neutralPanel);
    assertEquals(neutralPanel.getJugadores().size(), 1);
  }

  @Test
  public void setbossEnemy() {
    WildUnit wildUnit = new WildUnit(WildType.SEAGULL);
    BossPanel bossPanel1 = controller.createBossPanel();
    assertNull(bossPanel.getRival());
    assertNull(bossPanel1.getRival());
    controller.setEnemyToBossPanel(wildUnit);
    assertEquals(wildUnit, bossPanel1.getRival());
    assertEquals(wildUnit, bossPanel.getRival());
  }





}