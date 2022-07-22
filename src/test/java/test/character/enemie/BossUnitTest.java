package test.character.enemie;

import static org.junit.jupiter.api.Assertions.assertFalse;

import cl.uchile.dcc.citricliquid.model.personaje.enemies.boss.BossType;
import cl.uchile.dcc.citricliquid.model.personaje.enemies.boss.BossUnit;
import java.util.Objects;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;


/**
 * Class for test the boss unit.
 *
 */
public class BossUnitTest {
  private BossUnit storemanagertest;
  private BossUnit shifurobotTest;
  private BossUnit flyingcastleTest;

  /**
   * create elements for all the test.
   *
   */
  @BeforeEach
  public void setUp() {
    storemanagertest = new BossUnit(BossType.STORE_MANAGER);
    shifurobotTest = new BossUnit(BossType.SHIFU_ROBOT);
    flyingcastleTest = new BossUnit(BossType.FLYING_CASTLE);
  }

  @Test
  public void storeonstructorTest() {

    Assertions.assertEquals(8, storemanagertest.getMaxHp());
    Assertions.assertEquals(3, storemanagertest.getAtk());
    Assertions.assertEquals(2, storemanagertest.getDef());
    Assertions.assertEquals(-1, storemanagertest.getEvd());
    Assertions.assertEquals("Store manager", storemanagertest.getName());
    Assertions.assertEquals(BossType.STORE_MANAGER, storemanagertest.getTipo());
    Assertions.assertEquals(storemanagertest.getMaxHp(), storemanagertest.getCurrentHp());
    Assertions.assertEquals(0, storemanagertest.getStars());
  }

  @Test
  public void flyingeconstructorTest() {
    Assertions.assertEquals(10, flyingcastleTest.getMaxHp());
    Assertions.assertEquals(2, flyingcastleTest.getAtk());
    Assertions.assertEquals(1, flyingcastleTest.getDef());
    Assertions.assertEquals(-3, flyingcastleTest.getEvd());
    Assertions.assertEquals("Flying castle", flyingcastleTest.getName());
    Assertions.assertEquals(BossType.FLYING_CASTLE, flyingcastleTest.getTipo());
    Assertions.assertEquals(flyingcastleTest.getMaxHp(), flyingcastleTest.getCurrentHp());
    Assertions.assertEquals(0, storemanagertest.getStars());
  }

  @Test
  public void shifuonstructorTest() {
    Assertions.assertEquals(7, shifurobotTest.getMaxHp());
    Assertions.assertEquals(2, shifurobotTest.getAtk());
    Assertions.assertEquals(3, shifurobotTest.getDef());
    Assertions.assertEquals(-2, shifurobotTest.getEvd());
    Assertions.assertEquals("Shifu robot", shifurobotTest.getName());
    Assertions.assertEquals(BossType.SHIFU_ROBOT, shifurobotTest.getTipo());
    Assertions.assertEquals(shifurobotTest.getMaxHp(), shifurobotTest.getCurrentHp());
    Assertions.assertEquals(0, storemanagertest.getStars());
  }

  @Test
  public void starsTest() {
    storemanagertest.increaseStarsBy(4);
    Assertions.assertEquals(4, storemanagertest.getStars());
    storemanagertest.increaseStarsBy(2);
    Assertions.assertEquals(6, storemanagertest.getStars());
    storemanagertest.reduceStarsBy(1);
    Assertions.assertEquals(5, storemanagertest.getStars());
    storemanagertest.reduceStarsBy(10);
    Assertions.assertEquals(0, storemanagertest.getStars());
  }
 
  @Test
  public void hpTest() {
    storemanagertest.setCurrentHp(1);
    Assertions.assertEquals(1, storemanagertest.getCurrentHp());
    storemanagertest.setCurrentHp(storemanagertest.getMaxHp() + 1);
    Assertions.assertEquals(storemanagertest.getMaxHp(), storemanagertest.getCurrentHp());
  }


  /**
   * Repeated test for proof random characteristic.
   *This is prove that can generate a random boss unit, using a
   * static method that do this.
   *
   */
  @RepeatedTest(50)
  public void aleatoryCreation() {
    String enemigoName = (BossUnit.create_random_Boss_Rival()).getName();
    assertFalse((!Objects.equals(enemigoName, "Store manager")
        && !Objects.equals(enemigoName, "Shifu robot")
        && !Objects.equals(enemigoName, "Flying castle")));
  }


  //Reviso que se puedan diferenciar distintos tipos de boss.
  @Test
  public void equalsTest() {
    var expected1 = new BossUnit(BossType.STORE_MANAGER);
    var expected2 = new BossUnit(BossType.SHIFU_ROBOT);
    var expected3 = new BossUnit(BossType.FLYING_CASTLE);
    Assertions.assertEquals(expected1, storemanagertest);
    Assertions.assertEquals(expected2, shifurobotTest);
    Assertions.assertEquals(expected3, flyingcastleTest);

    Assertions.assertNotEquals(expected1, expected2);
    Assertions.assertNotEquals(expected2, expected3);
    Assertions.assertNotEquals(expected3, expected1);
  }
}


