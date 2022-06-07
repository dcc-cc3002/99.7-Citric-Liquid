package cl.uchile.dcc.citricliquid.model.Entity;


import cl.uchile.dcc.citricliquid.model.Entities.Player;
import cl.uchile.dcc.citricliquid.model.Panel.EncounterPanel;
import cl.uchile.dcc.citricliquid.model.Panel.Panel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;


/**
 * Manages the test for the player class.
 */
public class PlayerTest {
  private static final String PLAYER_NAME = "Suguri";
  private Player suguri;

  private Player suguri1;

  @BeforeEach
  public void setUp() {
    suguri = new Player("Suguri", 4, 1, - 1, 2);
    suguri1 = new Player("Suguri1", 4, 2, 1, 2);
  }

  @Test
  public void constructorTest() {
    final var expectedSuguri = new Player(PLAYER_NAME, 4, 1, - 1, 2);
    Assertions.assertEquals(expectedSuguri, suguri);
  }

  @Test
  public void increaseStarsByTest() {
    final int expectedStars = 4;
    suguri.increaseStarsBy(4);
    Assertions.assertEquals(expectedStars, suguri.getStars());
  }

  @Test
  public void setCurrentHpTest() {
    suguri.setCurrentHp(3);
    Assertions.assertEquals(3, suguri.getCurrentHp());
    suguri.setCurrentHp(- 1);
    Assertions.assertEquals(0, suguri.getCurrentHp());
    suguri.setCurrentHp(suguri.getMaxHp() + 1);
    int maxHp = suguri.getMaxHp();
    Assertions.assertEquals(maxHp, suguri.getCurrentHp());
  }

  @Test
  public void testEquals() {
    final var obj = new Object();
    Assertions.assertNotEquals(suguri, obj);
    Assertions.assertEquals(suguri, suguri);
    final var expectedSuguri = new Player(PLAYER_NAME, 4, 1, - 1, 2);
    Assertions.assertEquals(expectedSuguri, suguri);
  }

  @Test
  public void copyTest() {
    final var expectedSuguri = new Player(PLAYER_NAME, 4, 1, - 1, 2);
    final var actualSuguri = new Player(PLAYER_NAME, 4, 1, - 1, 2);
    //Checks that the copied player have the same parameters as the original(not the same reference)
    Assertions.assertEquals(expectedSuguri, actualSuguri);
    //Checks that the copied player doesn't have the same reference (same memory location)
    Assertions.assertNotSame(expectedSuguri, actualSuguri);
  }

  @Test
  public void reduceStarsByTest() {

    final int expectedStars = 2;
    suguri.increaseStarsBy(4);
    suguri.reduceStarsBy(2);
    Assertions.assertEquals(expectedStars, suguri.getStars());

    final int expectedStars1 = 0;
    suguri.reduceStarsBy(10);
    Assertions.assertEquals(expectedStars1, suguri.getStars());
  }

  /**
   * Roll consistency test.
   */
  @RepeatedTest(100)
  public void rollTest() {
    final long testSeed = new Random().nextLong();
    suguri.setSeed(testSeed);
    final int roll = suguri.roll();
    Assertions.assertTrue(roll >= 1 && roll <= 6, roll
            + "no esta entre [1 y 6]" + System.lineSeparator()
            + "Test fallado con la semilla aleatoria." + testSeed);

  }

  /**
   * Hit points consistency test.
   */
  @RepeatedTest(100)
  public void hitPointsConsistencyTest() {
    final long testSeed = new Random().nextLong();
    // We're going to try and set random hit points in [-maxHP * 2, maxHP * 2]
    final int testHp = new Random(testSeed).nextInt(4 * suguri.getMaxHp() + 1)
            - 2 * suguri.getMaxHp();
    suguri.setCurrentHp(testHp);
    Assertions.assertTrue(0 <= suguri.getCurrentHp()
                    && suguri.getCurrentHp() <= suguri.getMaxHp(),
            suguri.getCurrentHp() + "is not a valid HP value"
                    + System.lineSeparator() + "Test failed with random seed: "
                    + testSeed);
  }

  /**
   * Norma clear consistency test.
   */
  @RepeatedTest(100)
  public void normaClearConsistencyTest() {
    final long testSeed = new Random().nextLong();
    // We're going to test for 0 to 5 norma clears
    final int iterations = Math.abs(new Random(testSeed).nextInt(6));
    final int expectedNorma = suguri.getNormaLevel() + iterations;
    for (int it = 0; it < iterations; it++) {
      suguri.normaClear();
    }
    Assertions.assertEquals(expectedNorma, suguri.getNormaLevel(),
            "Test failed with random seed: " + testSeed);
  }

  @Test
  public void increaseWinsTest() {
    final int expectedWins = 2;
    suguri.setWins(0);
    Assertions.assertEquals(0, suguri.getWins());
    suguri.increaseWins(2);
    Assertions.assertEquals(expectedWins, suguri.getWins());

  }

  @Test

  public void setLocationTest() {
    Panel panel = new EncounterPanel(0, 0, new int[]{0, 0, 0, 0});
    panel.addPlayer(suguri);
    Assertions.assertTrue(panel.isPlayerInPanel(suguri));
    Assertions.assertEquals(suguri.getPlayerLocation()[0], panel.getPanelLocation()[0]);
    Assertions.assertEquals(suguri.getPlayerLocation()[1], panel.getPanelLocation()[1]);
  }

  /**
   * Damage test.
   */
  @RepeatedTest(5)
  public void damageTest() {
    System.out.println("A fight has begun");
    while (! suguri.isKo()) {
      suguri.evade(suguri1);
      System.out.println("---------------");

    }
    System.out.println("---------------");
    System.out.println("A fight has begun");
    suguri1.setCurrentHp(5);
    while (! suguri.isKo()) {
      suguri1.defend(suguri1);
      System.out.println("---------------");
    }
    System.out.println("---------------");
  }

  /**
   * damage test to another player.
   */
  @RepeatedTest(5)
  public void damageTest1() {
    System.out.println("A fight has begun");
    while (! suguri1.isKo()) {
      suguri1.evade(suguri);
      System.out.println("---------------");

    }
    System.out.println("---------------");
    System.out.println("A fight has begun");
    suguri1.setCurrentHp(5);
    while (! suguri1.isKo()) {
      suguri1.defend(suguri);
      System.out.println("---------------");
    }
    System.out.println("---------------");
  }

  @Test
  public void testNormaClear() {
    suguri.setWins(0);
    suguri.increaseStarsBy(10);
    suguri.normaCheck();
    Assertions.assertEquals(2, suguri.getNormaLevel());


    suguri.setWins(2);
    suguri.increaseStarsBy(20);
    suguri.normaCheck();
    Assertions.assertEquals(3, suguri.getNormaLevel());


    suguri.setWins(5);
    suguri.increaseStarsBy(40);
    suguri.normaCheck();
    Assertions.assertEquals(4, suguri.getNormaLevel());

    suguri.setWins(9);
    suguri.increaseStarsBy(50);
    suguri.normaCheck();
    Assertions.assertEquals(5, suguri.getNormaLevel());


    suguri.setWins(14);
    suguri.increaseStarsBy(80);
    suguri.normaCheck();
    Assertions.assertEquals(6, suguri.getNormaLevel());


  }


}



