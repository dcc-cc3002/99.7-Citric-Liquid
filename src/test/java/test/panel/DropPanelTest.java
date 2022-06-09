package test.panel;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;
import model.paneles.types.DropPanel;
import model.personaje.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

/**
 * Class for test the drop panel.
 *
 */
public class DropPanelTest {
  DropPanel dropPanel;
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
  public void set_up() {
    dropPanel = new DropPanel();
    suguri1 = new Player(PLAYER_NAM, BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD);
    suguri2 = new Player(PLAYER_NAM, BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD);
  }

  @Test
  public void constructorTest() {
    assertEquals(0, dropPanel.getJugadores().size());
    assertEquals(0, dropPanel.getNextPanels().size());
  }

  /**
   * test the consistency of this panel.
   *
   */
  @RepeatedTest(100)
  public void dropPanelConsistencyTest() {

    dropPanel.addPlayer(suguri1);
    int expectedStars = 30;
    suguri1.increaseStarsBy(30);
    assertEquals(expectedStars, suguri1.getStars());
    final var testRandom = new Random(testSeed);
    suguri1.setSeed(testSeed);
    for (int normaLvl = 1; normaLvl <= 6; normaLvl++) {
      final int roll = testRandom.nextInt(6) + 1;
      dropPanel.activate(suguri1);
      expectedStars = Math.max(expectedStars - roll * normaLvl, 0);
      assertEquals(expectedStars, suguri1.getStars(),
             "Test failed with seed: " + testSeed);
      suguri1.normaClear();
    }
  }

  @Test
  public void playersTest() {
    dropPanel.addPlayer(suguri1);
    assertEquals(1, dropPanel.getJugadores().size());
    dropPanel.addPlayer(suguri2);
    assertEquals(2, dropPanel.getJugadores().size());
    dropPanel.sacarJugador(suguri1);
    assertEquals(1, dropPanel.getJugadores().size());
    dropPanel.sacarJugador(suguri2);
    assertEquals(0, dropPanel.getJugadores().size());
  }

}
