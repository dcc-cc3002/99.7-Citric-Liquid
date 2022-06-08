package cl.uchile.dcc.citricliquid.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class BossTest {
  private final static String BOSS_NAME = "Suguri Boss";
  private Boss suguri;

  @BeforeEach
  public void setUp() {
    suguri = new Boss(BOSS_NAME, 4, 1, -1, 2);
  }

  @Test
  public void constructorTest() {
    final var expectedSuguri = new Boss(BOSS_NAME, 4, 1, -1, 2);
    Assertions.assertEquals(expectedSuguri, suguri);
  }

  @Test
  public void testStars() {
    int stars = 0;
    Assertions.assertEquals(stars, suguri.getStars());
    suguri.increaseStarsBy(15);
    stars += 15;
    Assertions.assertEquals(stars, suguri.getStars());
    suguri.increaseStarsBy(12);
    stars += 12;
    Assertions.assertEquals(stars, suguri.getStars());
  }

  @Test
  public void testWins() {
    int wins = 0;
    Assertions.assertEquals(wins, suguri.getStars());
    suguri.increaseStarsBy(7);
    wins += 7;
    Assertions.assertEquals(wins, suguri.getStars());
    suguri.increaseStarsBy(8);
    wins += 8;
    Assertions.assertEquals(wins, suguri.getStars());
  }

  @Test
  public void testEquals() {
    final var o = new Object();
    Assertions.assertNotEquals(suguri, o);
    Assertions.assertEquals(suguri, suguri);
    final var notExpectedSuguri = new Player(BOSS_NAME, 4, 1, -1, 2);
    Assertions.assertNotEquals(notExpectedSuguri, suguri);
    final var expectedSuguri = new Boss(BOSS_NAME, 4, 1, -1, 2);
    Assertions.assertEquals(expectedSuguri, suguri);
  }

  @Test
  public void hitPointsTest() {
    Assertions.assertEquals(suguri.getMaxHp(), suguri.getCurrentHp());
    suguri.setCurrentHp(2);
    Assertions.assertEquals(2, suguri.getCurrentHp());
    suguri.setCurrentHp(-1);
    Assertions.assertEquals(0, suguri.getCurrentHp());
    suguri.setCurrentHp(5);
    Assertions.assertEquals(4, suguri.getCurrentHp());
  }

  // region : consistency tests
  @RepeatedTest(100)
  public void hitPointsConsistencyTest() {
    final long testSeed = new Random().nextLong();
    // We're gonna try and set random hit points in [-maxHP * 2, maxHP * 2]
    final int testHP = new Random(testSeed).nextInt(4 * suguri.getMaxHp() + 1)
            - 2 * suguri.getMaxHp();
    suguri.setCurrentHp(testHP);
    Assertions.assertTrue(0 <= suguri.getCurrentHp()
                    && suguri.getCurrentHp() <= suguri.getMaxHp(),
            suguri.getCurrentHp() + "is not a valid HP value"
                    + System.lineSeparator() + "Test failed with random seed: "
                    + testSeed);
  }

  @RepeatedTest(100)
  public void rollConsistencyTest() {
    final long testSeed = new Random().nextLong();
    suguri.setSeed(testSeed);
    final int roll = suguri.roll();
    Assertions.assertTrue(roll >= 1 && roll <= 6,
            roll + "is not in [1, 6]" + System.lineSeparator()
                    + "Test failed with random seed: " + testSeed);
  }
  // endregion
}
