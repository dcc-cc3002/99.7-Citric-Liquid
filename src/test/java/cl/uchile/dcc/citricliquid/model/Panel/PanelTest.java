package cl.uchile.dcc.citricliquid.model.Panel;

import cl.uchile.dcc.citricliquid.model.Entities.BossUnit.ShifuRobot;
import cl.uchile.dcc.citricliquid.model.Entities.BossUnit.StoreManager;
import cl.uchile.dcc.citricliquid.model.Entities.Entity;
import cl.uchile.dcc.citricliquid.model.Entities.Player;
import cl.uchile.dcc.citricliquid.model.Entities.Wild_Unit.Chicken;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;


class PanelTest {
  private static final String PLAYER_NAME = "Suguri";
  private static final int BASE_HP = 4;
  private static final int BASE_ATK = 1;
  private static final int BASE_DEF = - 1;
  private static final int BASE_EVD = 2;

  private Panel testHomePanel;
  private Panel testNeutralPanel;
  private Panel testBonusPanel;
  private Panel testDropPanel;
  private Panel testEncounterPanel;
  private Panel testBossPanel;

  private Panel testBonusPanel1;

  private Panel testNeutralPanel1;

  private Panel testDrawPanel;
  private Player suguri;

  private Player suguri1;

  private Player suguri2;

  private Player suguri3;
  private long testSeed;

  private Entity StoreManager;


  @BeforeEach
  public void setUp() {


    testBonusPanel = new BonusPanel(0, 0, new int[]{0, 1, 0, 0});
    testBonusPanel1 = new BonusPanel(1, 0, new int[]{0, 1, 0, 0});
    testBossPanel = new BossPanel(2, 0, new int[]{0, 1, 1, 0}, false);
    testDropPanel = new DropPanel(2, 1, new int[]{0, 1, 0, 0});
    testEncounterPanel = new EncounterPanel(3, 0, new int[]{0, 1, 0, 0});
    testHomePanel = new HomePanel(3, 1, new int[]{0, 0, 0, 1});
    testNeutralPanel = new NeutralPanel(4, 0, new int[]{0, 0, 1, 0});
    testNeutralPanel1 = new NeutralPanel(4, 1, new int[]{1, 0, 0, 0});
    testDrawPanel = new DrawPanel(5, 0, new int[]{0, 0, 0, 0});
    testSeed = new Random().nextLong();
    suguri = new Player(PLAYER_NAME, BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD);
    suguri1 = new Player("Suguri1", BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD);
    suguri2 = new Player("Suguri2", BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD);
    suguri3 = new Player("Suguri3", BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD);
    StoreManager = new StoreManager(testBonusPanel);
  }

  @RepeatedTest(20)
  public void testSetBattleAgainstWildUnit() {
    testEncounterPanel.activatedBy(suguri);
  }

  @RepeatedTest(20)
  public void testSetBattleAgainstBossUnit() {
    testBossPanel.activatedBy(suguri);
  }

  /**
   * We create a board. (! MEANS ARROW DOWN AND ¡ MEANS ARROW UP)
   * X
   * 0              1                  2               3                    4
   * 1                                         |DROP PANEL| -> |HOME PANEL|     <-  |NEUTRAL PANEL 1 |
   * ¡               !                     ¡
   * y     0     |BONUS PANEL| -> |BONUS PANEL 1| -> |BOSS PANEL| -> |ENCOUNTER PANEL| -> |NEUTRAL PANEL| -> |DRAW PANEL|
   * <p>
   * -1
   */
  @Test
  public void move1Test() {
    final Panel[] panels = new Panel[]{testBonusPanel, testBonusPanel1, testBossPanel,
            testDropPanel, testEncounterPanel,
            testNeutralPanel, testHomePanel, testNeutralPanel1, testDrawPanel};

    //Clearing Panels.
    testBonusPanel.clearPanel();
    testBossPanel.clearPanel();
    testEncounterPanel.clearPanel();
    testNeutralPanel.clearPanel();
    testHomePanel.clearPanel();
    testNeutralPanel1.clearPanel();
    testBonusPanel1.clearPanel();
    testDropPanel.clearPanel();
    testDrawPanel.clearPanel();

    // OUR GOAL IS TO GET TO THE ENCOUNTER PANEL USING THE TWO DIFFERENT PATHS


    int[] left = new int[]{1, 0, 0, 0};
    int[] right = new int[]{0, 1, 0, 0};
    int[] up = new int[]{0, 0, 1, 0};
    int[] down = new int[]{0, 0, 0, 1};

    //BOUNDARY CASES:

    //HERE WE SHOULD RECEIVE (CANT TRAVEL TO THE LEFT
    testBonusPanel.addPlayer(suguri);
    suguri.move1space(panels, left);
    assertEquals(testBonusPanel, suguri.getPlayerPanel());
    testBonusPanel.clearPanel();

    //HERE WE SHOULD RECEIVE YOU CANT TRAVEL TO THE RIGHT
    testDrawPanel.addPlayer(suguri1);
    suguri1.move1space(panels, right);
    assertEquals(testDrawPanel, suguri1.getPlayerPanel());
    testDrawPanel.clearPanel();

    //HERE WE SHOULD RECEIVE YOU CANT TRAVEL UP
    testBonusPanel.addPlayer(suguri2);
    suguri2.move1space(panels, up);
    assertEquals(testBonusPanel, suguri2.getPlayerPanel());

    testBonusPanel.clearPanel();

    //HERE WE SHOULD RECEIVE YOU CANT TRAVEL DOWN
    testBonusPanel.addPlayer(suguri3);
    suguri3.move1space(panels, down);
    assertEquals(testBonusPanel, suguri3.getPlayerPanel());
    testBonusPanel.clearPanel();


    // NOW WE WANT TO MOVE AND CHECK IF THE PLAYER MOVES CORRECTLY


    testBonusPanel.clearPanel();

    System.out.println("Moving test 1");
    testHomePanel.addPlayer(suguri);
    System.out.println("Moving down");
    suguri.move1space(panels, down);
    assertEquals(testEncounterPanel, suguri.getPlayerPanel());
    testEncounterPanel.clearPanel();

    System.out.println("Moving test 2");
    Player[] players = testBonusPanel.getPlayers();
    testBonusPanel.addPlayer(suguri);
    System.out.println("Moving right");
    suguri.move1space(panels, right);
    assertEquals(testBonusPanel1, suguri.getPlayerPanel());


    System.out.println("Moving test 3");
    testBossPanel.addPlayer(suguri);
    System.out.println("Moving up");
    suguri.move1space(panels, up);
    assertEquals(testDropPanel, suguri.getPlayerPanel());

    System.out.println("Moving Test 4");
    testNeutralPanel1.addPlayer(suguri3);
    System.out.println("Moving Left");
    suguri3.move1space(panels, left);
    assertEquals(testHomePanel, suguri3.getPlayerPanel());

    System.out.println("All single moving test passed succesfully");
    System.out.println("Begining move to the encounter using diferent paths");

    testBonusPanel.clearPanel();
    testBossPanel.clearPanel();
    testEncounterPanel.clearPanel();
    testNeutralPanel.clearPanel();
    testHomePanel.clearPanel();
    testNeutralPanel1.clearPanel();
    testBonusPanel1.clearPanel();
    testDropPanel.clearPanel();
    System.out.println("Moving suguri");
    testBonusPanel.addPlayer(suguri);

    suguri.move1space(panels, right);
    suguri.move1space(panels, right);
    suguri.move1space(panels, up);
    suguri.move1space(panels, right);
    suguri.move1space(panels, down);
    assertEquals(testEncounterPanel, suguri.getPlayerPanel());
    System.out.println("Moving suguri1");
    testBonusPanel.addPlayer(suguri1);

    suguri1.move1space(panels, right);
    suguri1.move1space(panels, right);
    suguri1.move1space(panels, right);
    suguri1.move1space(panels, right);
    suguri1.move1space(panels, up);
    suguri1.move1space(panels, left);
    suguri1.move1space(panels, down);
    assertEquals(testEncounterPanel, suguri1.getPlayerPanel());

    System.out.println("All moving test passed succesfully");

  }

  @Test
  public void addPlayerTest() {
    testHomePanel.clearPanel();

    testHomePanel.addPlayer(suguri);
    assertEquals(testHomePanel, suguri.getPlayerPanel());
    testHomePanel.addPlayer(suguri1);
    assertEquals(testHomePanel, suguri1.getPlayerPanel());
    testHomePanel.addPlayer(suguri2);
    assertEquals(null, suguri2.getPlayerPanel());
  }


  @Test
  public void constructorTest() {
    final Panel expected = new BossPanel(2, 0, new int[]{0, 1, 0, 0}, false);
    assertEquals(expected, testBossPanel);
    assertNotEquals(expected, testBonusPanel1);

  }

  @Test
  public void testActivatedBy() {
    testHomePanel.addPlayer(suguri);
    testHomePanel.activatedBy(suguri);

    testBonusPanel1.addPlayer(suguri);
    testBonusPanel.activatedBy(suguri);

    testDropPanel.addPlayer(suguri);
    testDropPanel.activatedBy(suguri);

    testEncounterPanel.addPlayer(suguri);
    testEncounterPanel.activatedBy(suguri);

    testBossPanel.setActive();
    testBossPanel.activatedBy(suguri);

    testDrawPanel.addPlayer(suguri);
    testDrawPanel.activatedBy(suguri);
  }

  @Test
  public void testPvp() {
    testDropPanel.addPlayer(suguri);
    testDropPanel.addPlayer(suguri1);
    suguri.checksForEnemyPlayer();
  }

  @Test
  public void testEquals() {
    Panel expectedPanel = new BonusPanel(testBonusPanel.getPanelLocation()[0],
            testBonusPanel.getPanelLocation()[1],
            new int[]{testBonusPanel.getPaths()[0],
                    testBonusPanel.getPaths()[1]});
    assertEquals(expectedPanel, testBonusPanel);
    assertNotEquals(testBonusPanel, testBossPanel);
    assertNotEquals(testBonusPanel, testBonusPanel1);

  }

  @Test
  public void getLocationTest() {
    suguri.setLocation(testBossPanel);
    int[] coordinates = new int[]{2, 0};
    assertEquals(coordinates[0], suguri.getPlayerLocation()[0]);
    assertEquals(coordinates[1], suguri.getPlayerLocation()[1]);

    suguri.setLocation(testBonusPanel);
    int[] coordinates1 = new int[]{1, 0};
    assertNotEquals(coordinates1[0], suguri.getPlayerLocation()[0]);
    assertEquals(coordinates1[1], suguri.getPlayerLocation()[1]);


  }


  @Test
  public void homePanelTest() {
    suguri.setCurrentHp(3);
    testHomePanel.setPlayerHome(suguri);
    testHomePanel.activatedBy(suguri);
    assertEquals(4, suguri.getCurrentHp());
    testHomePanel.activatedBy(suguri);
    assertEquals(suguri.getMaxHp(), suguri.getCurrentHp());

    testHomePanel.activatedBy(suguri);
    assertEquals(suguri.getMaxHp(), suguri.getCurrentHp());

  }

  @Test
  public void neutralPanelTest() {
    final var expectedSuguri = new Player(PLAYER_NAME, BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD);
    testNeutralPanel.activatedBy(suguri);
    assertEquals(expectedSuguri, suguri);
  }

  @Test
  public void isPlayerInPanelTest() {
    testNeutralPanel.addPlayer(suguri);
    testNeutralPanel.addPlayer(suguri1);
    testNeutralPanel.addPlayer(suguri2);
    assertTrue(testNeutralPanel.isPlayerInPanel(suguri)
            &&
            testNeutralPanel.isPlayerInPanel(suguri1));
    assertFalse(testNeutralPanel.isPlayerInPanel(suguri2));
    testBossPanel.addPlayer(suguri);
    assertFalse(testBossPanel.isPlayerInPanel(suguri1));
    assertFalse(testDropPanel.isPlayerInPanel(suguri));
  }

  @Test
  public void removePlayerTest() {
    testDropPanel.addPlayer(suguri);
    assertTrue(testDropPanel.isPlayerInPanel(suguri));
    testDropPanel.removePlayer(suguri);
    assertFalse(testDropPanel.isPlayerInPanel(suguri));
  }

  @Test
  public void testEntity() {
    Entity shifuRobot = new ShifuRobot(testEncounterPanel);
    assertTrue(testEncounterPanel.isEntityInPanel(shifuRobot));
    Entity chicken = new Chicken(testBonusPanel);
    assertFalse(testBonusPanel.isEntityInPanel(chicken));
  }

  @Test
  public void removeEntityTest() {
    StoreManager.setLocation(testEncounterPanel);
    assertTrue(testEncounterPanel.isEntityInPanel(StoreManager));
    testEncounterPanel.removeEntity();
    assertFalse(testEncounterPanel.isEntityInPanel(StoreManager));
  }


  // region : Consistency tests
  @RepeatedTest(100)
  public void bonusPanelConsistencyTest() {
    int expectedStars = 0;
    assertEquals(expectedStars, suguri.getStars());
    final var testRandom = new Random(testSeed);
    suguri.setSeed(testSeed);
    for (int normaLvl = 1; normaLvl <= 6; normaLvl++) {
      final int roll = testRandom.nextInt(6) + 1;
      testBonusPanel.activatedBy(suguri);
      expectedStars += roll * Math.min(3, normaLvl);
      assertEquals(expectedStars, suguri.getStars(),
              "Test failed with seed: " + testSeed);
      suguri.normaClear();
    }
  }

  @RepeatedTest(100)
  public void dropPanelConsistencyTest() {
    int expectedStars = 30;
    suguri.increaseStarsBy(30);
    assertEquals(expectedStars, suguri.getStars());
    final var testRandom = new Random(testSeed);
    suguri.setSeed(testSeed);
    for (int normaLvl = 1; normaLvl <= 6; normaLvl++) {
      final int roll = testRandom.nextInt(6) + 1;
      testDropPanel.activatedBy(suguri);
      expectedStars = Math.max(expectedStars - roll * normaLvl, 0);
      assertEquals(expectedStars, suguri.getStars(),
              "Test failed with seed: " + testSeed);
      suguri.normaClear();
    }
  }


  @Test
  public void testBossPanel() {
    suguri1.setLocation(testBossPanel);
    testBossPanel.removeEntity();
    suguri.setLocation(testBossPanel);
    testBossPanel.removePlayer(suguri);
    suguri.setLocation(testBossPanel);

  }


  // endregion
}
