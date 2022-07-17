package cl.uchile.dcc.citricliquid.model.unit;

import cl.uchile.dcc.citricliquid.model.unit.BossUnit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BossUnitTest {
    private final static String BOSS_NAME_1 = "Store Manager";
    private final static String BOSS_NAME_2 = "Shifu Robot";
    private final static String BOSS_NAME_3 = "Flying Castle";
    private BossUnit storeManager;
    private BossUnit shifuRobot;
    private BossUnit flyingCastle;

    @BeforeEach
    public void setUp() {
        storeManager = new BossUnit(BOSS_NAME_1, 8, 3, 2, -1);
        shifuRobot = new BossUnit(BOSS_NAME_2, 7, 2, 3, -2);
        flyingCastle = new BossUnit(BOSS_NAME_3, 10, 2, 1, -3);
    }

    @Test
    public void constructorTest() {
        final var expectedManager = new BossUnit(BOSS_NAME_1, 8, 3, 2 , -1);
        final var expectedRobot = new BossUnit(BOSS_NAME_2, 7, 2, 3, -2);
        final var expectedCastle = new BossUnit(BOSS_NAME_3, 10, 2, 1, -3);
        Assertions.assertEquals(expectedManager, storeManager);
        Assertions.assertEquals(expectedRobot, shifuRobot);
        Assertions.assertEquals(expectedCastle, flyingCastle);
    }

    @Test
    public void testEquals() {
        final var o = new Object();
        Assertions.assertNotEquals(storeManager, o);
        Assertions.assertEquals(storeManager, storeManager);
        final var expectedBoss = new BossUnit(BOSS_NAME_1, 8, 3, 2, -1);
        Assertions.assertEquals(expectedBoss, storeManager);
    }

    @Test
    public void hitPointsTest() {
        assertEquals(storeManager.getMaxHp(), storeManager.getCurrentHp());
        storeManager.setCurrentHp(2);
        assertEquals(2, storeManager.getCurrentHp());
        storeManager.setCurrentHp(-5);
        assertEquals(0, storeManager.getCurrentHp());
        storeManager.setCurrentHp(10);
        assertEquals(8, storeManager.getCurrentHp());
    }

    @Test
    public void copyTest() {
        final var expectedBoss = new BossUnit(BOSS_NAME_1, 8, 3, 2, -1);
        final var actualBoss = storeManager.copy();
        // Checks that the copied boss have the same parameters as the original
        Assertions.assertEquals(expectedBoss, actualBoss);
        // Checks that the copied boss doesn't reference the same object
        Assertions.assertNotSame(expectedBoss, actualBoss);
    }

    @Test
    public void getNameTest() {
        Assertions.assertEquals(storeManager.getName(), BOSS_NAME_1);
        Assertions.assertEquals(shifuRobot.getName(), BOSS_NAME_2);
        Assertions.assertEquals(flyingCastle.getName(), BOSS_NAME_3);
    }

    // region : consistency tests
    @RepeatedTest(100)
    public void hitPointsConsistencyTest() {
        final long testSeed = new Random().nextLong();
        // We're gonna try and set random hit points in [-maxHP * 2, maxHP * 2]
        final int testHP = new Random(testSeed).nextInt(4 * storeManager.getMaxHp() + 1)
                - 2 * storeManager.getMaxHp();
        storeManager.setCurrentHp(testHP);
        assertTrue(0 <= storeManager.getCurrentHp()
                        && storeManager.getCurrentHp() <= storeManager.getMaxHp(),
                storeManager.getCurrentHp() + "is not a valid HP value"
                        + System.lineSeparator() + "Test failed with random seed: "
                        + testSeed);
    }
}
