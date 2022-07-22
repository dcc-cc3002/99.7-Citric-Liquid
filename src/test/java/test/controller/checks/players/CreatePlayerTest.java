package test.controller.checks.players;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cl.uchile.dcc.citricliquid.model.controller.Controller;
import cl.uchile.dcc.citricliquid.model.paneles.AbstracPanel;
import cl.uchile.dcc.citricliquid.model.personaje.enemies.boss.BossType;
import cl.uchile.dcc.citricliquid.model.personaje.enemies.boss.BossUnit;
import cl.uchile.dcc.citricliquid.model.personaje.enemies.wild.WildType;
import cl.uchile.dcc.citricliquid.model.personaje.enemies.wild.WildUnit;
import cl.uchile.dcc.citricliquid.model.personaje.player.Player;
import cl.uchile.dcc.citricliquid.model.personaje.player.SubirCon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



/**
 * test controller methods relatives to players.
 *
 */
public class CreatePlayerTest {
  Controller controller;
  Player player;
  WildUnit wildUnit;
  BossUnit bossUnit;

  /**
   * create elements for all the test.
   *
   */
  @BeforeEach
  public void set_up() {
    controller = new Controller();

    player = controller.createPlayer("juan", 2, 3, 2, 4);
    bossUnit = controller.createBossUnit(BossType.SHIFU_ROBOT);
    wildUnit = controller.createWillUnit(WildType.ROBO_BALL);

  }

  @Test
  public void constructorTest() {
    assertEquals(new  Player("juan", 2, 3, 2, 4), player);
    assertEquals(new  WildUnit(WildType.ROBO_BALL), wildUnit);
    assertEquals(new BossUnit(BossType.SHIFU_ROBOT), bossUnit);
  }



  @Test
  public void addPlayerTest() {
    assertEquals(1, controller.getPlayers().size());
    assertTrue(controller.getPlayers().contains(player));
    Player p2 = controller.createPlayer("Joseda", 2, 2, 2, 2);
    assertEquals(2, controller.getPlayers().size());
    assertTrue(controller.getPlayers().contains(p2));
  }


  @Test
  public void possibleBattleTest() {
    Player p2 = controller.createPlayer("Joseda", 2, 2, 2, 2);
    AbstracPanel panel = controller.createNeutralPanel();
    controller.setPlayerInPanel(player, panel);
    assertEquals(null, controller.possibleBattle(player));
    controller.setPlayerInPanel(p2, panel);
    assertEquals(p2, controller.possibleBattle(player));

  }

  @Test
  public void starsorwinsTest() {
    controller.setstardOrWins(player, SubirCon.WINS);
    assertEquals(SubirCon.WINS, player.getStar_or_wins());
    controller.setstardOrWins(player, SubirCon.STARS);
    assertEquals(SubirCon.STARS, player.getStar_or_wins());
  }


}
