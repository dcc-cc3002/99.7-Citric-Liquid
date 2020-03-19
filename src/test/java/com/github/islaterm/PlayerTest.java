package com.github.islaterm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test suite for the players of the game.
 *
 * @author <a href="mailto:ignacio.slater@ug.uchile.cl">Ignacio Slater Muñoz</a>.
 * @version 1.0.5-b3
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
  }

  @Test
  public void normaClearTest() {
    assertEquals(1, suguri.getNorma());
    suguri.normaClear();
    assertEquals(2, suguri.getNorma());
    suguri.normaClear();
    int iterations = new Random(testSeed).nextInt(4);
    int expectedNorma = suguri.getNorma() + iterations;
    for (int it = 0; it < iterations; it++) {
      suguri.normaClear();
    }
    assertEquals(expectedNorma, suguri.getNorma(),
                 "Test failed with random seed: " + testSeed);
  }
}
