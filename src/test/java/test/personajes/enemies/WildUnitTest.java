package test.personajes.enemies;

import model.personaje.enemies.wild.WildType;
import model.personaje.enemies.wild.WildUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Class for test the wild unit.
 *
 */
public class WildUnitTest {
  private WildUnit wildChikenTest;
  private WildUnit wildSeadullTest;
  private WildUnit wildRoboballTest;

  /**
   * create elements for all the test.
   *
   */
  @BeforeEach
  public void setUp() {
    wildChikenTest = new WildUnit(WildType.CHICKEN);
    wildSeadullTest = new WildUnit(WildType.SEAGULL);
    wildRoboballTest = new WildUnit(WildType.ROBO_BALL);
  }

  @Test
  public void chikenconstructorTest() {
    assertEquals(3, wildChikenTest.getMaxHp());
    assertEquals(-1, wildChikenTest.getAtk());
    assertEquals(-1, wildChikenTest.getDef());
    assertEquals(1, wildChikenTest.getEvd());
    assertEquals("Chicken", wildChikenTest.getName());
    assertEquals(WildType.CHICKEN, wildChikenTest.getTipo());
    assertEquals(wildChikenTest.getMaxHp(), wildChikenTest.getCurrentHp());
    assertEquals(0, wildChikenTest.getStars());
  }

  @Test
  public void robo_ballconstructorTest() {
    assertEquals(2, wildRoboballTest.getMaxHp());
    assertEquals(-1, wildRoboballTest.getAtk());
    assertEquals(1, wildRoboballTest.getDef());
    assertEquals(-1, wildRoboballTest.getEvd());
    assertEquals("Robo ball", wildRoboballTest.getName());
    assertEquals(WildType.ROBO_BALL, wildRoboballTest.getTipo());
    assertEquals(wildRoboballTest.getMaxHp(), wildRoboballTest.getCurrentHp());
    assertEquals(0, wildRoboballTest.getStars());
  }

  @Test
  public void seadullconstructorTest() {
    assertEquals(3, wildSeadullTest.getMaxHp());
    assertEquals(1, wildSeadullTest.getAtk());
    assertEquals(-1, wildSeadullTest.getDef());
    assertEquals(-1, wildSeadullTest.getEvd());
    assertEquals("Seagull", wildSeadullTest.getName());
    assertEquals(WildType.SEAGULL, wildSeadullTest.getTipo());
    assertEquals(wildSeadullTest.getMaxHp(), wildSeadullTest.getCurrentHp());
    assertEquals(0, wildSeadullTest.getStars());
  }

  @Test
  public void starsTest() {
    wildChikenTest.increase_star_by(4);
    assertEquals(4, wildChikenTest.getStars());
    wildChikenTest.increase_star_by(2);
    assertEquals(6, wildChikenTest.getStars());
    wildChikenTest.reduce_star_by(1);
    assertEquals(5, wildChikenTest.getStars());
    wildChikenTest.reduce_star_by(10);
    assertEquals(0, wildChikenTest.getStars());
  }

  @Test
  public void hpTest() {
    wildChikenTest.setCurrentHp(1);
    assertEquals(1, wildChikenTest.getCurrentHp());
    wildChikenTest.setCurrentHp(wildChikenTest.getMaxHp() + 1);
    assertEquals(wildChikenTest.getMaxHp(), wildChikenTest.getCurrentHp());
  }

  @Test
  public void equalsTest() {
    var expected1 = new WildUnit(WildType.CHICKEN);
    var expected2 = new WildUnit(WildType.SEAGULL);
    var expected3 = new WildUnit(WildType.ROBO_BALL);
    assertEquals(expected1, wildChikenTest);
    assertEquals(expected2, wildSeadullTest);
    assertEquals(expected3, wildRoboballTest);
    assertEquals(expected1, wildChikenTest);

    assertNotEquals(expected1, expected2);
    assertNotEquals(expected2, expected3);
    assertNotEquals(expected3, expected1);
  }

}
