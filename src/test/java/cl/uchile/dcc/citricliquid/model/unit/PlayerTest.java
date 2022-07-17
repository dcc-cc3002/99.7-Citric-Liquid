package cl.uchile.dcc.citricliquid.model.unit;

import cl.uchile.dcc.citricliquid.model.NormaGoal;
import cl.uchile.dcc.citricliquid.model.unit.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

/**
 * Test suite for the players of the game.
 *
 * @author <a href="mailto:ignacio.slater@ug.uchile.cl">Ignacio Slater M.</a>.
 * @version 1.0.6-rc.1
 * @since 1.0
 */
public class PlayerTest {
  private final static String PLAYER_NAME = "Suguri";
  private Player suguri;
  private final static String WILD_NAME_1 = "Chicken";
  private WildUnit chicken;
  private final static String BOSS_NAME_1 = "Store Manager";
  private BossUnit storeManager;

  @BeforeEach
  public void setUp() {
    suguri = new Player(PLAYER_NAME, 4, 1, -1, 2);
    chicken = new WildUnit(WILD_NAME_1, 3, -1, -1, 1);
    storeManager = new BossUnit(BOSS_NAME_1, 8, 3, 2, -1);
  }

  @Test
  public void constructorTest() {
    final var expectedSuguri = new Player(PLAYER_NAME, 4, 1, -1, 2);
    Assertions.assertEquals(expectedSuguri, suguri);
  }

  @Test
  public void testEquals() {
    final var o = new Object();
    Assertions.assertNotEquals(suguri, o);
    Assertions.assertEquals(suguri, suguri);
    final var expectedSuguri = new Player(PLAYER_NAME, 4, 1, -1, 2);
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

  @Test
  public void normaClearTest() {
    suguri.normaClear();
    Assertions.assertEquals(2, suguri.getNormaLevel());
  }

  @Test
  public void normaGoalTest() {
    suguri.setNormaGoal(NormaGoal.STARS);
    Assertions.assertEquals(NormaGoal.STARS, suguri.getNormaGoal());
  }

  @Test
  public void setSpecsTest() {
    suguri.setCurrentHp(2);
    Assertions.assertEquals(2, suguri.getCurrentHp());
    suguri.setAtk(3);
    Assertions.assertEquals(3, suguri.getAtk());
    suguri.setDef(1);
    Assertions.assertEquals(1, suguri.getDef());
    suguri.setEvd(5);
    Assertions.assertEquals(5, suguri.getEvd());
  }

  @Test
  public void winsTest() {
    suguri.setWins(5);
    Assertions.assertEquals(5, suguri.getWins());
  }

  @Test
  public void copyTest() {
    final var expectedSuguri = new Player(PLAYER_NAME, 4, 1, -1, 2);
    final var actualSuguri = suguri.copy();
    // Checks that the copied player have the same parameters as the original
    Assertions.assertEquals(expectedSuguri, actualSuguri);
    // Checks that the copied player doesn't reference the same object
    Assertions.assertNotSame(expectedSuguri, actualSuguri);
  }

  @Test
  public void getNameTest() {
    Assertions.assertEquals(suguri.getName(), PLAYER_NAME);
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
  public void normaClearConsistencyTest() {
    final long testSeed = new Random().nextLong();
    // We're gonna test for 0 to 5 norma clears
    final int iterations = Math.abs(new Random(testSeed).nextInt(6));
    final int expectedNorma = suguri.getNormaLevel() + iterations;
    for (int it = 0; it < iterations; it++) {
      suguri.normaClear();
    }
    Assertions.assertEquals(expectedNorma, suguri.getNormaLevel(),
                            "Test failed with random seed: " + testSeed);
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

  // region : battle tests

  @Test
  public void increaseWinsTests() {
    int dice = suguri.roll();
    suguri.increaseStarsBy(chicken);
    Assertions.assertEquals(suguri.getStars(), suguri.getWins());
    suguri.setWins(5);
    Assertions.assertEquals(5, suguri.getWins());
  }
}
