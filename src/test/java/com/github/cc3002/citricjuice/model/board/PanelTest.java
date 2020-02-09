package com.github.cc3002.citricjuice.model.board;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author <a href="mailto:ignacio.slater@ug.cuhile.cl">Ignacio Slater Muñoz</a>.
 * @version 1.0.2.2
 * @since 1.0
 */
class PanelTest {
  private Panel testHomePanel;
  private Panel testNeutralPanel;
  private Panel testBonusPanel;
  private Panel testDropPanel;
  private Panel testEncounterPanel;
  private Panel testBossPanel;

  @BeforeEach
  public void setUp() {
    testBonusPanel = new Panel("Bonus");
    testBossPanel = new Panel("Boss");
    testDropPanel = new Panel("Drop");
    testEncounterPanel = new Panel("Encounter");
    testHomePanel = new Panel("Home");
    testNeutralPanel = new Panel("Neutral");
  }

  @Test
  public void constructorTest() {
    assertEquals("Bonus", testBonusPanel.getType());
    assertEquals("Boss", testBossPanel.getType());
    assertEquals("Drop", testDropPanel.getType());
    assertEquals("Encounter", testEncounterPanel.getType());
    assertEquals("Home", testHomePanel.getType());
    assertEquals("Neutral", testNeutralPanel.getType());
  }

  @Test
  public void nextPanelTest() {
    assertTrue(testNeutralPanel.getNextPanels().isEmpty());
    var expectedPanel1 = new Panel("");
    var expectedPanel2 = new Panel("");

    testNeutralPanel.addNextPanel(expectedPanel1);
    assertEquals(1, testNeutralPanel.getNextPanels().size());

    testNeutralPanel.addNextPanel(expectedPanel2);
    assertEquals(2, testNeutralPanel.getNextPanels().size());

    testNeutralPanel.addNextPanel(expectedPanel2);
    assertEquals(2, testNeutralPanel.getNextPanels().size());

    assertEquals(Set.of(expectedPanel1, expectedPanel2),
                 testNeutralPanel.getNextPanels());
  }
}