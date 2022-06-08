package cl.uchile.dcc.citricliquid.model.board;

import cl.uchile.dcc.citricliquid.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author <a href="mailto:ignacio.slater@ug.uchile.cl">Ignacio Slater M.</a>.
 * @version 1.0.6-rc.1
 * @since 1.0
 */
class PanelTest {
  private final static String PLAYER_NAME = "Suguri";
  private final static int BASE_HP = 4;
  private final static int BASE_ATK = 1;
  private final static int BASE_DEF = -1;
  private final static int BASE_EVD = 2;
  private HomePanel testHomePanel;
  private NeutralPanel testNeutralPanel;
  private BonusPanel testBonusPanel;
  private DropPanel testDropPanel;
  private EncounterPanel testEncounterPanel;
  private BossPanel testBossPanel;
  private Player suguri;
  private long testSeed;

  @BeforeEach
  public void setUp() {
    testBonusPanel = new BonusPanel();
    testBossPanel = new BossPanel();
    testDropPanel = new DropPanel();
    testEncounterPanel = new EncounterPanel();
    testHomePanel = new HomePanel();
    testNeutralPanel = new NeutralPanel();
    testSeed = new Random().nextLong();
    suguri = new Player(PLAYER_NAME, BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD);

  }

  @Test
  public void constructorTest() {
    assertEquals(PanelType.BONUS, testBonusPanel.getType());
    assertEquals(PanelType.BOSS, testBossPanel.getType());
    assertEquals(PanelType.DROP, testDropPanel.getType());
    assertEquals(PanelType.ENCOUNTER, testEncounterPanel.getType());
    assertEquals(PanelType.HOME, testHomePanel.getType());
    assertEquals(PanelType.NEUTRAL, testNeutralPanel.getType());
  }

  @Test
  public void nextPanelTest() {
    assertTrue(testNeutralPanel.getNextPanels().isEmpty());
    final var expectedPanel1 = new NeutralPanel();
    final var expectedPanel2 = new NeutralPanel();

    testNeutralPanel.addNextPanel(expectedPanel1);
    assertEquals(1, testNeutralPanel.getNextPanels().size());

    testNeutralPanel.addNextPanel(expectedPanel2);
    assertEquals(2, testNeutralPanel.getNextPanels().size());

    testNeutralPanel.addNextPanel(expectedPanel2);
    assertEquals(2, testNeutralPanel.getNextPanels().size());

    assertEquals(Set.of(expectedPanel1, expectedPanel2),
            testNeutralPanel.getNextPanels());
  }

  @Test
  public void homePanelTest() {
    assertEquals(suguri.getMaxHp(), suguri.getCurrentHp());
    testHomePanel.activatedBy(suguri);
    assertEquals(suguri.getMaxHp(), suguri.getCurrentHp());

    suguri.setCurrentHp(1);
    testHomePanel.activatedBy(suguri);
    assertEquals(2, suguri.getCurrentHp());
    assertEquals(1, suguri.getNormaLevel());

    suguri.increaseStarsBy(10);
    suguri.increaseWinsBy(2);
    testHomePanel.activatedBy(suguri);
    assertEquals(2, suguri.getNormaLevel());
    testHomePanel.activatedBy(suguri);
    assertEquals(2, suguri.getNormaLevel());

    suguri.increaseStarsBy(22);
    testHomePanel.activatedBy(suguri);
    assertEquals(3, suguri.getNormaLevel());
    testHomePanel.activatedBy(suguri);
    assertEquals(3, suguri.getNormaLevel());

    suguri.increaseStarsBy(53);
    suguri.increaseWinsBy(4);
    testHomePanel.activatedBy(suguri);
    assertEquals(4, suguri.getNormaLevel());
    testHomePanel.activatedBy(suguri);
    assertEquals(4, suguri.getNormaLevel());

    suguri.increaseStarsBy(50);
    suguri.increaseWinsBy(2);
    testHomePanel.activatedBy(suguri);
    assertEquals(4, suguri.getNormaLevel());
    suguri.increaseWinsBy(2);
    testHomePanel.activatedBy(suguri);
    assertEquals(5, suguri.getNormaLevel());

    suguri.increaseStarsBy(83);
    suguri.increaseWinsBy(4);
    testHomePanel.activatedBy(suguri);
    assertEquals(6, suguri.getNormaLevel());
    testHomePanel.activatedBy(suguri);
    assertEquals(6, suguri.getNormaLevel());

  }

  @Test
  public void neutralPanelTest() {
    final var expectedSuguri = suguri.copy();
    testNeutralPanel.activatedBy(suguri);
    assertEquals(expectedSuguri, suguri);
  }

  // region : Consistency tests
  @RepeatedTest(100)
  public void bonusPanelConsistencyTest() {
    int expectedStars = 0;
    assertEquals(expectedStars, suguri.getStars());
    final var testRandom = new Random(testSeed);
    suguri.setSeed(testSeed);
    for (int normaLvl = 1; normaLvl <= 6; normaLvl++) {
      final int roll = testRandom.nextInt(6) + 1;
      testBonusPanel.activatedBy(suguri);
      expectedStars += roll * Math.min(3, normaLvl);
      assertEquals(expectedStars, suguri.getStars(),
              "Test failed with seed: " + testSeed);
      suguri.normaClear();
    }
  }

  @RepeatedTest(100)
  public void dropPanelConsistencyTest() {
    int expectedStars = 30;
    suguri.increaseStarsBy(30);
    assertEquals(expectedStars, suguri.getStars());
    final var testRandom = new Random(testSeed);
    suguri.setSeed(testSeed);
    for (int normaLvl = 1; normaLvl <= 6; normaLvl++) {
      final int roll = testRandom.nextInt(6) + 1;
      testDropPanel.activatedBy(suguri);
      expectedStars = Math.max(expectedStars - roll * normaLvl, 0);
      assertEquals(expectedStars, suguri.getStars(),
              "Test failed with seed: " + testSeed);
      suguri.normaClear();
    }
  }
  // endregion
}