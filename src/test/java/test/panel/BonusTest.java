package test.panel;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;
import model.paneles.types.BonusPanel;
import model.personaje.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Class for test the bonus panel.
 *
 */
public class BonusTest {
  private static final String PLAYER_NAME = "Suguri";
  private static final String PLAYER_NAM = "Suguri";
  private static final int BASE_HP = 4;
  private static final int BASE_ATK = 1;
  private static final int BASE_DEF = -1;
  private static final int BASE_EVD = 2;

  BonusPanel bonusPanel;
  private Player suguri1;
  private Player suguri2;
  private long testSeed;


  /**
   * create elements for all the test.
   *
   */
  @BeforeEach
  public void setUp() {
    suguri1 = new Player(PLAYER_NAM, BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD);
    suguri2 = new Player(PLAYER_NAM, BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD);
    bonusPanel = new BonusPanel();
  }

  @Test
  public void constructorTest() {
    assertEquals(0, bonusPanel.getJugadores().size());
    assertEquals(0, bonusPanel.getNextPanels().size());
  }

  @Test
  public void bonuspaneltest() {
    bonusPanel.addPlayer(suguri1);
    int expectedStars = 0;
    assertEquals(expectedStars, suguri1.getStars());
    final var testRandom = new Random(testSeed);
    suguri1.setSeed(testSeed);
    for (int normaLvl = 1; normaLvl <= 6; normaLvl++) {
      final int roll = testRandom.nextInt(6) + 1;
      expectedStars += roll * Math.min(3, normaLvl);
      bonusPanel.activate(suguri1);
      assertEquals(expectedStars, suguri1.getStars(),
          "Test failed with seed: " + testSeed);
      suguri1.normaClear();
    }
  }

  @Test
  public void playersTest() {
    bonusPanel.addPlayer(suguri1);
    assertEquals(1, bonusPanel.getJugadores().size());
    bonusPanel.addPlayer(suguri2);
    assertEquals(2, bonusPanel.getJugadores().size());
    bonusPanel.sacarJugador(suguri1);
    assertEquals(1, bonusPanel.getJugadores().size());
    bonusPanel.sacarJugador(suguri2);
    assertEquals(0, bonusPanel.getJugadores().size());
  }

}
