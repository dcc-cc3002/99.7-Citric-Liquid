package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test suite for the players of the game.
 *
 * @author <a href="mailto:ignacio.slater@ug.uchile.cl">Ignacio Slater M.</a>.
 * @version 1.0.6-b.4
 * @since 1.0
 */
public class PlayerTest {
  private Player suguri;

  @BeforeEach
  public void setUp() {
    suguri = new Player("Suguri", 4, 1, -1, 2);
  }

  @Test
  public void constructorTest() {
    assertEquals("Suguri", suguri.getName());
    assertEquals(4, suguri.getMaxHP());
    assertEquals(1, suguri.getAtk());
    assertEquals(-1, suguri.getDef());
    assertEquals(2, suguri.getEvd());
    assertEquals(1, suguri.getNormaLevel());
    assertEquals(suguri.getMaxHP(), suguri.getCurrentHP());
  }

  @Test
  public void hitPointsTest() {
    assertEquals(suguri.getMaxHP(), suguri.getCurrentHP());
    suguri.setCurrentHP(2);
    assertEquals(2, suguri.getCurrentHP());
    suguri.setCurrentHP(-1);
    assertEquals(0, suguri.getCurrentHP());
    suguri.setCurrentHP(5);
    assertEquals(4, suguri.getCurrentHP());
  }

  @Test
  public void normaClearTest() {
    suguri.normaClear();
    assertEquals(2, suguri.getNormaLevel());
  }


  // region : consistency tests
  @RepeatedTest(100)
  public void hitPointsConsistencyTest() {
    long testSeed = new Random().nextLong();
    // We're gonna try and set random hit points in [-maxHP * 2, maxHP * 2]
    int testHP = new Random(testSeed).nextInt(4 * suguri.getMaxHP() + 1)
                 - 2 * suguri.getMaxHP();
    suguri.setCurrentHP(testHP);
    assertTrue(0 <= suguri.getCurrentHP()
               && suguri.getCurrentHP() <= suguri.getMaxHP(),
               suguri.getCurrentHP() + "is not a valid HP value"
               + System.lineSeparator() + "Test failed with random seed: "
               + testSeed);
  }

  @RepeatedTest(100)
  public void normaClearConsistencyTest() {
    long testSeed = new Random().nextLong();
    // We're gonna test for 0 to 5 norma clears
    int iterations = Math.abs(new Random(testSeed).nextInt(6));
    int expectedNorma = suguri.getNormaLevel() + iterations;
    for (int it = 0; it < iterations; it++) {
      suguri.normaClear();
    }
    assertEquals(expectedNorma, suguri.getNormaLevel(),
                 "Test failed with random seed: " + testSeed);
  }

  @RepeatedTest(100)
  public void rollConsistencyTest() {
    long testSeed = new Random().nextLong();
    suguri.setSeed(testSeed);
    int roll = suguri.roll();
    assertTrue(roll >= 1 && roll <= 6,
               roll + "is not in [1, 6]" + System.lineSeparator()
               + "Test failed with random seed: " + testSeed);
  }
  // endregion
}
