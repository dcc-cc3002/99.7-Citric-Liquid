package test.panels.starspanel;

import cl.uchile.dcc.citricliquid.model.exeptions.InvalidStateOperationException;
import cl.uchile.dcc.citricliquid.model.paneles.types.star.BonusPanel;
import cl.uchile.dcc.citricliquid.model.personaje.player.Player;
import cl.uchile.dcc.citricliquid.model.state.states.turnstate.EffectPanelState;
import java.util.Random;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Class for test the bonus panel.
 *
 */
public class BonusTest {
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
    suguri1.setState(new EffectPanelState(suguri1));
    suguri2.setState(new EffectPanelState(suguri2));
  }

  @Test
  public void constructorTest() {
    Assertions.assertEquals(0, bonusPanel.getJugadores().size());
    Assertions.assertEquals(0, bonusPanel.getNextPanels().size());
    Assertions.assertTrue(bonusPanel.isBonusPanel());
    Assertions.assertFalse(bonusPanel.isDropPanel());
  }

  @Test
  public void bonuspaneltest() throws InvalidStateOperationException {
    bonusPanel.addPlayer(suguri1);
    int expectedStars = 0;
    Assertions.assertEquals(expectedStars, suguri1.getStars());
    final var testRandom = new Random(testSeed);
    suguri1.setSeed(testSeed);
    for (int normaLvl = 1; normaLvl <= 6; normaLvl++) {
      final int roll = testRandom.nextInt(6) + 1;
      suguri1.setState(new EffectPanelState(suguri1));
      expectedStars += roll * Math.min(3, normaLvl);
      bonusPanel.activate(suguri1);
      Assertions.assertEquals(expectedStars, suguri1.getStars(),
          "Test failed with seed: " + testSeed);
      suguri1.normaClear();
    }
  }

  @Test
  public void playersTest() {
    bonusPanel.addPlayer(suguri1);
    Assertions.assertEquals(1, bonusPanel.getJugadores().size());
    bonusPanel.addPlayer(suguri2);
    Assertions.assertEquals(2, bonusPanel.getJugadores().size());
    bonusPanel.sacarJugador(suguri1);
    Assertions.assertEquals(1, bonusPanel.getJugadores().size());
    bonusPanel.sacarJugador(suguri2);
    Assertions.assertEquals(0, bonusPanel.getJugadores().size());
  }

  @Test
  public void toStringTest() {
    Assertions.assertEquals("BonusPanel", bonusPanel.toString());
  }

}
