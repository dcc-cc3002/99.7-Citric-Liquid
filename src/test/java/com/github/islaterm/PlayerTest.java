package com.github.islaterm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test suite for the players of the game.
 *
 * @author <a href="mailto:ignacio.slater@ug.uchile.cl">Ignacio Slater
 *     Muñoz</a>.
 * @version 1.0.5-b4
 * @since 1.0
 */
public class PlayerTest {
  private Player suguri;
  private long testSeed;

  @BeforeEach
  public void setUp() {
    suguri = new Player("Suguri", 4, 1, -1, 2);
    testSeed = new Random().nextInt();
  }

  @Test
  public void constructorTest() {
    assertEquals("Suguri", suguri.getName());
    assertEquals(4, suguri.getMaxHP());
    assertEquals(1, suguri.getAtk());
    assertEquals(-1, suguri.getDef());
    assertEquals(2, suguri.getEvd());
    assertEquals(1, suguri.getNormaLevel());
  }

  @Test
  public void normaClearTest() {
    suguri.normaClear();
    assertEquals(2, suguri.getNormaLevel());
  }

  @RepeatedTest(100)
  public void normaClearConsistencyTest() {
    int iterations = Math.abs(new Random(testSeed).nextInt());
    int expectedNorma = suguri.getNormaLevel() + iterations;
    for (int it = 0; it < iterations; it++) {
      suguri.normaClear();
    }
    assertEquals(expectedNorma, suguri.getNormaLevel(),
                 "Test failed with random seed: " + testSeed);
  }
}
