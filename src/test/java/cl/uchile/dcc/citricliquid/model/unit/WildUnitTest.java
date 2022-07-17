package cl.uchile.dcc.citricliquid.model.unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WildUnitTest {
    private final static String WILD_NAME_1 = "Chicken";
    private final static String WILD_NAME_2 = "Robo Ball";
    private final static String WILD_NAME_3 = "Seagull";
    private WildUnit chicken;
    private WildUnit roboBall;
    private WildUnit seagull;

    @BeforeEach
    public void setUp() {
        chicken = new WildUnit(WILD_NAME_1, 3, -1, -1, 1);
        roboBall = new WildUnit(WILD_NAME_2, 3, -1, 1, -1);
        seagull = new WildUnit(WILD_NAME_3, 3, 1, -1, -1);
    }

    @Test
    public void constructorTest() {
        final var expectedChicken = new WildUnit(WILD_NAME_1, 3, -1, -1 , 1);
        final var expectedRobo = new WildUnit(WILD_NAME_2, 3, -1, 1, -1);
        final var expectedSeagull = new WildUnit(WILD_NAME_3, 3, 1, -1, -1);
        Assertions.assertEquals(expectedChicken, chicken);
        Assertions.assertEquals(expectedRobo, roboBall);
        Assertions.assertEquals(expectedSeagull, seagull);
    }

    @Test
    public void testEquals() {
        final var o = new Object();
        Assertions.assertNotEquals(chicken, o);
        Assertions.assertEquals(chicken, chicken);
        final var expectedChicken = new WildUnit(WILD_NAME_1, 3, -1, -1, 1);
        Assertions.assertEquals(expectedChicken, chicken);
    }

    @Test
    public void hitPointsTest() {
        assertEquals(chicken.getMaxHp(), chicken.getCurrentHp());
        chicken.setCurrentHp(2);
        assertEquals(2, chicken.getCurrentHp());
        chicken.setCurrentHp(-5);
        assertEquals(0, chicken.getCurrentHp());
        chicken.setCurrentHp(10);
        assertEquals(3, chicken.getCurrentHp());
    }

    @Test
    public void copyTest() {
        final var expectedChicken = new WildUnit(WILD_NAME_1, 3, -1, -1, 1);
        final var actualChicken = chicken.copy();
        // Checks that the copied boss have the same parameters as the original
        Assertions.assertEquals(expectedChicken, actualChicken);
        // Checks that the copied boss doesn't reference the same object
        Assertions.assertNotSame(expectedChicken, actualChicken);
    }

    // region : consistency tests
    @RepeatedTest(100)
    public void hitPointsConsistencyTest() {
        final long testSeed = new Random().nextLong();
        // We're going to try and set random hit points in [-maxHP * 2, maxHP * 2]
        final int testHP = new Random(testSeed).nextInt(4 * chicken.getMaxHp() + 1)
                - 2 * chicken.getMaxHp();
        chicken.setCurrentHp(testHP);
        assertTrue(0 <= chicken.getCurrentHp()
                        && chicken.getCurrentHp() <= chicken.getMaxHp(),
                chicken.getCurrentHp() + "is not a valid HP value"
                        + System.lineSeparator() + "Test failed with random seed: "
                        + testSeed);
    }
}
