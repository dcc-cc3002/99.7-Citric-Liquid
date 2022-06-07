package test.panel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import model.paneles.types.BossPanel;
import model.personaje.enemies.boss.BossUnit;
import model.personaje.player.Player;
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
  long testSeed;


  /**
   * create elements for all the test.
   *
   */
  @BeforeEach
  public void setUp() {
    suguri1 = new Player(PLAYER_NAM, BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD);
    suguri2 = new Player(PLAYER_NAM, BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD);
    bossPanel = new BossPanel();
  }

  @Test
  public void constructorTest() {
    assertEquals(0, bossPanel.getJugadores().size());
    assertEquals(0, bossPanel.getNextPanels().size());
  }

  /**
   * Repeated test for proof random characteristic.
   *
   */
  @RepeatedTest(100)
  public void bossRandomTest() {
    int rand = bossPanel.roll();
    assertTrue(rand >= 1 && rand <= 3,
        rand + "is not in [1, 6]" + System.lineSeparator()
                 + "Test failed with random seed: " + testSeed);
  }

  /**
   * Repeated test for proof random characteristic.
   *
   */
  @RepeatedTest(100)
  public void rivalTypeBossPanelTest() {
    assertNull(bossPanel.getRival());
    bossPanel.addPlayer(suguri1);
    bossPanel.activate(suguri1);
    assertNotEquals(null, bossPanel.getRival());
    assertNotEquals(null, bossPanel.getRival().getName());
    String enemigoName = (bossPanel.getRival().getName());
    assertFalse((!enemigoName.equals("Store manager")
            && !enemigoName.equals("Flying castle")
            && !enemigoName.equals("Shifu robot")));
  }

  //COMO FALTA IMPLEMENTAR BATALLAS ESTA ES UNA PRUEBA TENTATIVA.
  @Test
  public void boss_Enemie_Creation() {
    assertNull(bossPanel.getRival());
    bossPanel.addPlayer(suguri1);
    bossPanel.activate(suguri1);
    BossUnit enemigo = (bossPanel.getRival());
    assertFalse(bossPanel.rival_dead());

    assertNotEquals(null, enemigo);

    enemigo.setCurrentHp(0);
    assertTrue(bossPanel.rival_dead());
    assertNull(bossPanel.getRival());

  }

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
}
