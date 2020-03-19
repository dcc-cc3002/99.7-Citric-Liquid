package com.github.islaterm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test suite for the players of the game.
 *
 * @author <a href="mailto:ignacio.slater@ug.uchile.cl">Ignacio Slater Muñoz</a>.
 * @version 1.0.5-b2
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
  }
}
