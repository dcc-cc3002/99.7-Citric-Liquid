package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author <a href="mailto:ignacio.slater@ug.cuhile.cl">Ignacio Slater
 *     Muñoz</a>.
 * @version 1.0.6-b.3
 * @since 1.0
 */
class PanelTest {
  private Panel testHomePanel;
  private Panel testNeutralPanel;
  private Panel testBonusPanel;
  private Panel testDropPanel;
  private Panel testEncounterPanel;
  private Panel testBossPanel;
  private Player suguri;
  private long testSeed;

  @BeforeEach
  public void setUp() {
    testBonusPanel = new Panel(PanelType.BONUS);
    testBossPanel = new Panel(PanelType.BOSS);
    testDropPanel = new Panel(PanelType.DROP);
    testEncounterPanel = new Panel(PanelType.ENCOUNTER);
    testHomePanel = new Panel(PanelType.HOME);
    testNeutralPanel = new Panel(PanelType.NEUTRAL);
    testSeed = new Random().nextLong();
    suguri = new Player("Suguri", 4, 1, -1, 2);
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
    var expectedPanel1 = new Panel(PanelType.NEUTRAL);
    var expectedPanel2 = new Panel(PanelType.NEUTRAL);

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
  public void bonusPanelActivateTest() {
    int expectedStars = 0;
    assertEquals(expectedStars, suguri.getStars());
    var testRandom = new Random(testSeed);
    suguri.setSeed(testSeed);
    for (int normaLvl = 1; normaLvl <= 6; normaLvl++) {
      int roll = testRandom.nextInt(6) + 1;
      testBonusPanel.activatedBy(suguri);
      expectedStars += roll * Math.min(3, normaLvl);
      assertEquals(expectedStars, suguri.getStars(),
                   "Test failed with seed: " + testSeed);
      suguri.normaClear();
    }
  }

  // region : Consistency tests
  @RepeatedTest(100)
  public void bonusPanelConsistencyTest() {
    bonusPanelActivateTest();
  }
  // endregion
}