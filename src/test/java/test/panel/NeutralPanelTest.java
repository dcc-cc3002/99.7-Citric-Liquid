package test.panel;

import static org.junit.jupiter.api.Assertions.assertEquals;

import model.paneles.types.NeutralPanel;
import model.personaje.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class for test the neutral panel.
 *
 */
public class NeutralPanelTest {
  private NeutralPanel neutralTest;
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
    neutralTest = new NeutralPanel();
  }

  @Test
  public void constructorTest() {
    assertEquals(0, neutralTest.getJugadores().size());
    assertEquals(0, neutralTest.getNextPanels().size());
  }

  @Test
  public void activatedTest() {
    neutralTest.addPlayer(suguri1);
    neutralTest.activate(suguri1);
    assertEquals(1, neutralTest.getJugadores().size());
    var expected = new Player(PLAYER_NAM, BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD);
    assertEquals(expected, suguri1);
  }

  @Test
  public void playersTest() {
    neutralTest.addPlayer(suguri1);
    assertEquals(1, neutralTest.getJugadores().size());
    neutralTest.addPlayer(suguri2);
    assertEquals(2, neutralTest.getJugadores().size());
    neutralTest.sacarJugador(suguri1);
    assertEquals(1, neutralTest.getJugadores().size());
    neutralTest.sacarJugador(suguri2);
    assertEquals(0, neutralTest.getJugadores().size());
  }
}
