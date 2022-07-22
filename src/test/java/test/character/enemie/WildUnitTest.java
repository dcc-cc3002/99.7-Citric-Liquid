package test.character.enemie;

import static org.junit.jupiter.api.Assertions.assertFalse;

import cl.uchile.dcc.citricliquid.model.personaje.enemies.wild.WildType;
import cl.uchile.dcc.citricliquid.model.personaje.enemies.wild.WildUnit;
import java.util.Objects;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;




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
   * Corresponds to the 3 types of wild unit.
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
    Assertions.assertEquals(3, wildChikenTest.getMaxHp());
    Assertions.assertEquals(-1, wildChikenTest.getAtk());
    Assertions.assertEquals(-1, wildChikenTest.getDef());
    Assertions.assertEquals(1, wildChikenTest.getEvd());
    Assertions.assertEquals("Chicken", wildChikenTest.getName());
    Assertions.assertEquals(WildType.CHICKEN, wildChikenTest.getTipo());
    Assertions.assertEquals(wildChikenTest.getMaxHp(), wildChikenTest.getCurrentHp());
    Assertions.assertEquals(0, wildChikenTest.getStars());
  }

  @Test
  public void robo_ballconstructorTest() {
    Assertions.assertEquals(2, wildRoboballTest.getMaxHp());
    Assertions.assertEquals(-1, wildRoboballTest.getAtk());
    Assertions.assertEquals(1, wildRoboballTest.getDef());
    Assertions.assertEquals(-1, wildRoboballTest.getEvd());
    Assertions.assertEquals("Robo ball", wildRoboballTest.getName());
    Assertions.assertEquals(WildType.ROBO_BALL, wildRoboballTest.getTipo());
    Assertions.assertEquals(wildRoboballTest.getMaxHp(), wildRoboballTest.getCurrentHp());
    Assertions.assertEquals(0, wildRoboballTest.getStars());
  }

  @Test
  public void seadullconstructorTest() {
    Assertions.assertEquals(3, wildSeadullTest.getMaxHp());
    Assertions.assertEquals(1, wildSeadullTest.getAtk());
    Assertions.assertEquals(-1, wildSeadullTest.getDef());
    Assertions.assertEquals(-1, wildSeadullTest.getEvd());
    Assertions.assertEquals("Seagull", wildSeadullTest.getName());
    Assertions.assertEquals(WildType.SEAGULL, wildSeadullTest.getTipo());
    Assertions.assertEquals(wildSeadullTest.getMaxHp(), wildSeadullTest.getCurrentHp());
    Assertions.assertEquals(0, wildSeadullTest.getStars());
  }

  //se prueba que se pueda incrementar y reducir stars.
  @Test
  public void starsTest() {
    wildChikenTest.increaseStarsBy(4);
    Assertions.assertEquals(4, wildChikenTest.getStars());
    wildChikenTest.increaseStarsBy(2);
    Assertions.assertEquals(6, wildChikenTest.getStars());
    wildChikenTest.reduceStarsBy(1);
    Assertions.assertEquals(5, wildChikenTest.getStars());
    wildChikenTest.reduceStarsBy(10);
    Assertions.assertEquals(0, wildChikenTest.getStars());
  }

  //se prueba que se pueda cambiar el hp.
  @Test
  public void hpTest() {
    wildChikenTest.setCurrentHp(1);
    Assertions.assertEquals(1, wildChikenTest.getCurrentHp());
    wildChikenTest.setCurrentHp(wildChikenTest.getMaxHp() + 1);
    Assertions.assertEquals(wildChikenTest.getMaxHp(), wildChikenTest.getCurrentHp());
  }

  /**
   * Repeated test for proof random characteristic.
   * We create aleatory a wild unit.
   *
   */
  @RepeatedTest(50)
  public void aleatoryCreation() {

    String enemigoName = (WildUnit.create_random_Wild_Rival().getName());
    assertFalse((!Objects.equals(enemigoName, "Chicken")
        && !Objects.equals(enemigoName, "Robo ball")
        && !Objects.equals(enemigoName, "Seagull")));
  }

  //Revisamos que equals esté bien.
  @Test
  public void equalsTest() {
    var expected1 = new WildUnit(WildType.CHICKEN);
    var expected2 = new WildUnit(WildType.SEAGULL);
    var expected3 = new WildUnit(WildType.ROBO_BALL);
    Assertions.assertEquals(expected1, wildChikenTest);
    Assertions.assertEquals(expected2, wildSeadullTest);
    Assertions.assertEquals(expected3, wildRoboballTest);
    Assertions.assertEquals(expected1, wildChikenTest);

    Assertions.assertNotEquals(expected1, expected2);
    Assertions.assertNotEquals(expected2, expected3);
    Assertions.assertNotEquals(expected3, expected1);
  }

}
