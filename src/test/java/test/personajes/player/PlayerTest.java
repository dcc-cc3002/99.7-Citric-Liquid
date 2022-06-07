package test.personajes.player;

import java.util.Random;
import model.personaje.player.Player;
import model.personaje.player.SubirCon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

/**
 * Class for test the player's methods.
 *
 */
public class PlayerTest {
  private static final String PLAYER_NAME = "Suguri";
  private Player suguri;

  /**
   * create elements for all the test.
   *
   */
  @BeforeEach
  public void setUp() {
    suguri = new Player(PLAYER_NAME, 4, 1, -1, 2);
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
  public void copyTest() {
    final var expectedSuguri = new Player(PLAYER_NAME, 4, 1, -1, 2);
    final var actualSuguri = suguri.copy();
    // Checks that the copied player have the same parameters as the original
    Assertions.assertEquals(expectedSuguri, actualSuguri);
    // Checks that the copied player doesn't reference the same object
    Assertions.assertNotSame(expectedSuguri, actualSuguri);
  }


  /**
   * test the consistency of hp.
   *
   */
  @RepeatedTest(100)
  public void hitPointsConsistencyTest() {
    final long testSeed = new Random().nextLong();
    // We're gonna try and set random hit points in [-maxHP * 2, maxHP * 2]
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
   * test the consistency of norma clear.
   *
   */
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


  /**
   * test the consistency of roll.
   *
   */
  @RepeatedTest(100)
  public void rollConsistencyTest() {
    final long testSeed = new Random().nextLong();
    suguri.setSeed(testSeed);
    final int roll = suguri.roll();
    Assertions.assertTrue(roll >= 1 && roll <= 6,
            roll + "is not in [1, 6]" + System.lineSeparator()
                    + "Test failed with random seed: " + testSeed);
  }

  @Test
  public  void  starsTest() {
    //me aseguro que inicie en cero
    Assertions.assertEquals(0, suguri.getStars());
    //ahora agrego estrellas
    suguri.increaseStarsBy(10);
    Assertions.assertEquals(10, suguri.getStars());
    //Quito estrellas y resultado positivo
    suguri.reduceStarsBy(5);
    Assertions.assertEquals(5, suguri.getStars());
    //quito cantidad que deba dar cero
    suguri.reduceStarsBy(50);
    Assertions.assertEquals(0, suguri.getStars());
  }

  @Test
  public  void winsTest() {
    //inicia en subir con stars
    Assertions.assertEquals(0, suguri.getWins());
    //agregando
    suguri.increaseWinsBy(10);
    Assertions.assertEquals(10, suguri.getWins());
  }

  @Test
  public  void starWins() {
    //inicia en subir con stars
    Assertions.assertEquals(SubirCon.STARS, suguri.getStar_or_wins());
    //agregando
    suguri.subir_wins();
    Assertions.assertEquals(SubirCon.WINS, suguri.getStar_or_wins());
    suguri.subir_star();
    Assertions.assertEquals(SubirCon.STARS, suguri.getStar_or_wins());
  }

}
