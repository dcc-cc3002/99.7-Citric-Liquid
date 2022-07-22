package test.panels.enemiepanels;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import cl.uchile.dcc.citricliquid.model.exeptions.InvalidStateOperationException;
import cl.uchile.dcc.citricliquid.model.paneles.types.enemies.BossPanel;
import cl.uchile.dcc.citricliquid.model.personaje.enemies.boss.BossType;
import cl.uchile.dcc.citricliquid.model.personaje.enemies.boss.BossUnit;
import cl.uchile.dcc.citricliquid.model.personaje.player.Player;
import cl.uchile.dcc.citricliquid.model.state.states.turnstate.EffectPanelState;
import java.util.Objects;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;


/**
 * Class for test the boss panel.
 *
 */
public class BossPanelTest {
  BossPanel bossPanel;

  private static final String PLAYER_NAM = "Suguri";
  private static final int BASE_HP = 4;
  private static final int BASE_ATK = 1;
  private static final int BASE_DEF = -1;
  private static final int BASE_EVD = 2;
  private Player suguri1;
  private Player suguri2;

  /**
   * create elements for all the test.
   *
   */
  @BeforeEach
  public void setUp() {
    suguri1 = new Player(PLAYER_NAM, BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD);
    suguri2 = new Player(PLAYER_NAM, BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD);
    bossPanel = new BossPanel();
    suguri1.setState(new EffectPanelState(suguri1));
    suguri2.setState(new EffectPanelState(suguri2));
  }

  //Se revisa que se construyan bien los paneles.
  @Test
  public void constructorTest() {
    assertEquals(0, bossPanel.getJugadores().size());
    assertEquals(0, bossPanel.getNextPanels().size());
    Assertions.assertTrue(bossPanel.isBossPanel());
    Assertions.assertFalse(bossPanel.isEncounterPanel());

  }

  /**
   * Repeated test for proof random characteristic.
   * In this case, prove the type of enemy in the panel when it is activated.
   */
  @RepeatedTest(50)
  public void rivalTypeBossPanelTest2() throws InvalidStateOperationException {
    assertNull(bossPanel.getRival());
    bossPanel.addPlayer(suguri1);
    bossPanel.activate(suguri1);
    assertNotEquals(null, bossPanel.getRival());
    assertNotEquals(null, bossPanel.getRival().getName());
    String enemigoName = (bossPanel.getRival().getName());
    assertFalse((!Objects.equals(enemigoName, "Chicken")
        && !Objects.equals(enemigoName, "Robo ball")
        && !Objects.equals(enemigoName, "Seagull")));
  }


  //Prove add a player in the panel.
  @Test
  public void playersTest() {
    bossPanel.addPlayer(suguri1);
    assertEquals(1, bossPanel.getJugadores().size());
    bossPanel.addPlayer(suguri2);
    assertEquals(2, bossPanel.getJugadores().size());
    bossPanel.sacarJugador(suguri1);
    assertEquals(1, bossPanel.getJugadores().size());
    bossPanel.sacarJugador(suguri2);
    assertEquals(0, bossPanel.getJugadores().size());
  }

  @Test
  public void setRivalTest() {
    assertNull(bossPanel.getRival());
    bossPanel.setRival((new BossUnit(BossType.SHIFU_ROBOT)));
    assertEquals(new BossUnit(BossType.SHIFU_ROBOT), bossPanel.getRival());
  }


  @Test
  public void toStringTest() {
    assertEquals("BossPanel", bossPanel.toString());
  }


}
