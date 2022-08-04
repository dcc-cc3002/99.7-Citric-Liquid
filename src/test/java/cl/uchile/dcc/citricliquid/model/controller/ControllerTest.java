package cl.uchile.dcc.citricliquid.model.controller;

import cl.uchile.dcc.citricliquid.model.board.*;
import cl.uchile.dcc.citricliquid.model.unit.BossUnit;
import cl.uchile.dcc.citricliquid.model.unit.Player;
import cl.uchile.dcc.citricliquid.model.unit.WildUnit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cl.uchile.dcc.citricliquid.phases.*;
import cl.uchile.dcc.citricliquid.controller.GameController;

public class ControllerTest {
    protected GameController controller;
    private final static String PLAYER_NAME = "Suguri";
    private Player suguri;
    private final static String WILD_NAME_1 = "Chicken";
    private WildUnit chicken;
    private final static String BOSS_NAME_1 = "Store Manager";
    private BossUnit storeManager;


    private Panel testHomePanel;
    private Panel testNeutralPanel;
    private Panel testBonusPanel;
    private Panel testDropPanel;
    private Panel testEncounterPanel;
    private Panel testBossPanel;
    private long testSeed;


    @BeforeEach
    public void setUp() {
        controller = new GameController();
        suguri = new Player(PLAYER_NAME, 4, 1, -1, 2);
        chicken = new WildUnit(WILD_NAME_1, 3, -1, -1, 1);
        storeManager = new BossUnit(BOSS_NAME_1, 8, 3, 2, -1);

        testBonusPanel = new BonusPanel(1);
        testBossPanel = new BossPanel(2);
        testDropPanel = new DropPanel(3);
        testEncounterPanel = new EncounterPanel(2);
        testHomePanel = new HomePanel(1);
        testNeutralPanel = new NeutralPanel(3);
        testSeed = new Random().nextLong();
    }

    @Test
    public void createUnitTest() {
        Player createdP = controller.createPlayer(PLAYER_NAME, 4, 1, -1, 2);
        assertEquals(createdP, suguri);

        WildUnit createdW = controller.createWildUnit(WILD_NAME_1, 3, -1, -1, 1);
        assertEquals(createdW, chicken);

        BossUnit createdB = controller.createBossUnit(BOSS_NAME_1, 8, 3, 2, -1);
        assertEquals(createdB, storeManager);
    }

    @Test
    public void createPanelTest() {
        Panel createdBonP = controller.createBonusPanel(1);
        assertEquals(createdBonP, testBonusPanel);
        Panel createdBossP = controller.createBossPanel(2);
        assertEquals(createdBossP, testBossPanel);
        Panel createdDP = controller.createDropPanel(3);
        assertEquals(createdDP, testDropPanel);
        Panel createdEP = controller.createEncounterPanel(2);
        assertEquals(createdEP, testEncounterPanel);
        Panel createdHP = controller.createHomePanel(1);
        assertEquals(createdHP, testHomePanel);
        Panel createdNP = controller.createNeutralPanel(3);
        assertEquals(createdNP, testNeutralPanel);
    }

    @Test
    public void playerPositionTest() {
        HomePanel newHome = new HomePanel(6);
        controller.setHomePanel(suguri, newHome);
        assertEquals(suguri.getHomePanel(), newHome);

        suguri.setCurrentPanel(testNeutralPanel);

        Panel newCurrent = new NeutralPanel(4);
        controller.setCurrentPanel(suguri, newCurrent);
        assertEquals(suguri.getPanel(), newCurrent);

    }

    @Test
    public void battleTest() {

    }

}
