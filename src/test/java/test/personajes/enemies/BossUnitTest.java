package test.personajes.enemies;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import model.personaje.enemies.boss.BossType;
import model.personaje.enemies.boss.BossUnit;
import org.junit.jupiter.api.BeforeEach;
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
    assertEquals(8, storemanagertest.getMaxHp());
    assertEquals(3, storemanagertest.getAtk());
    assertEquals(2, storemanagertest.getDef());
    assertEquals(-1, storemanagertest.getEvd());
    assertEquals("Store manager", storemanagertest.getName());
    assertEquals(BossType.STORE_MANAGER, storemanagertest.getTipo());
    assertEquals(storemanagertest.getMaxHp(), storemanagertest.getCurrentHp());
    assertEquals(0, storemanagertest.getStars());
  }

  @Test
  public void flyingeconstructorTest() {
    assertEquals(10, flyingcastleTest.getMaxHp());
    assertEquals(2, flyingcastleTest.getAtk());
    assertEquals(1, flyingcastleTest.getDef());
    assertEquals(-3, flyingcastleTest.getEvd());
    assertEquals("Flying castle", flyingcastleTest.getName());
    assertEquals(BossType.FLYING_CASTLE, flyingcastleTest.getTipo());
    assertEquals(flyingcastleTest.getMaxHp(), flyingcastleTest.getCurrentHp());
    assertEquals(0, storemanagertest.getStars());
  }

  @Test
  public void shifuonstructorTest() {
    assertEquals(7, shifurobotTest.getMaxHp());
    assertEquals(2, shifurobotTest.getAtk());
    assertEquals(3, shifurobotTest.getDef());
    assertEquals(-2, shifurobotTest.getEvd());
    assertEquals("Shifu robot", shifurobotTest.getName());
    assertEquals(BossType.SHIFU_ROBOT, shifurobotTest.getTipo());
    assertEquals(shifurobotTest.getMaxHp(), shifurobotTest.getCurrentHp());
    assertEquals(0, storemanagertest.getStars());
  }

  @Test
  public void starsTest() {
    storemanagertest.increase_star_by(4);
    assertEquals(4, storemanagertest.getStars());
    storemanagertest.increase_star_by(2);
    assertEquals(6, storemanagertest.getStars());
    storemanagertest.reduce_star_by(1);
    assertEquals(5, storemanagertest.getStars());
    storemanagertest.reduce_star_by(10);
    assertEquals(0, storemanagertest.getStars());
  }
 
  @Test
  public void hpTest() {
    storemanagertest.setCurrentHp(1);
    assertEquals(1, storemanagertest.getCurrentHp());
    storemanagertest.setCurrentHp(storemanagertest.getMaxHp() + 1);
    assertEquals(storemanagertest.getMaxHp(), storemanagertest.getCurrentHp());
  }

  @Test
  public void equalsTest() {
    var expected1 = new BossUnit(BossType.STORE_MANAGER);
    var expected2 = new BossUnit(BossType.SHIFU_ROBOT);
    var expected3 = new BossUnit(BossType.FLYING_CASTLE);
    assertEquals(expected1, storemanagertest);
    assertEquals(expected2, shifurobotTest);
    assertEquals(expected3, flyingcastleTest);

    assertNotEquals(expected1, expected2);
    assertNotEquals(expected2, expected3);
    assertNotEquals(expected3, expected1);
  }
}


