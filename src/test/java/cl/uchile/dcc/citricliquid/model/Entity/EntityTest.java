package cl.uchile.dcc.citricliquid.model.Entity;


import cl.uchile.dcc.citricliquid.model.Entities.BossUnit.FlyingCastle;
import cl.uchile.dcc.citricliquid.model.Entities.BossUnit.ShifuRobot;
import cl.uchile.dcc.citricliquid.model.Entities.BossUnit.StoreManager;
import cl.uchile.dcc.citricliquid.model.Entities.Entity;
import cl.uchile.dcc.citricliquid.model.Entities.Wild_Unit.Chicken;
import cl.uchile.dcc.citricliquid.model.Entities.Wild_Unit.Robo_Ball;
import cl.uchile.dcc.citricliquid.model.Entities.Wild_Unit.Seagull;
import cl.uchile.dcc.citricliquid.model.Panel.EncounterPanel;
import cl.uchile.dcc.citricliquid.model.Panel.Panel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class that test entity methods.
 */
public class EntityTest {

  private Entity chicken;
  private Entity seagull;
  private Entity roboBall;

  private Entity fc;

  private Entity sm;

  private Entity sr;

  private Panel testEncounterPanel;

  private Panel testEncounterPanel1;

  private Panel testEncounterPanel2;

  private Panel testEncounterPanel3;

  private Panel testEncounterPanel4;

  private Panel testEncounterPanel5;

  /**
   * Setup the variables.
   */
  @BeforeEach
  public void setUp() {


    testEncounterPanel = new EncounterPanel(0, 0, new int[]{0, 1, 0, 0});
    testEncounterPanel1 = new EncounterPanel(1, 0, new int[]{0, 1, 0, 0});
    testEncounterPanel2 = new EncounterPanel(2, 0, new int[]{0, 1, 0, 0});
    testEncounterPanel3 = new EncounterPanel(3, 0, new int[]{0, 1, 0, 0});
    testEncounterPanel4 = new EncounterPanel(4, 0, new int[]{0, 1, 0, 0});
    testEncounterPanel5 = new EncounterPanel(5, 0, new int[]{0, 1, 0, 0});

    chicken = new Chicken(testEncounterPanel);
    seagull = new Seagull(testEncounterPanel1);
    roboBall = new Robo_Ball(testEncounterPanel2);
    fc = new FlyingCastle(testEncounterPanel3);
    sm = new StoreManager(testEncounterPanel4);
    sr = new ShifuRobot(testEncounterPanel5);

  }

  @Test

  public void constructorTest() {

    // We check that the positions match
    Assertions.assertEquals(chicken.getPanel(), testEncounterPanel);
    Assertions.assertEquals(seagull.getPanel(), testEncounterPanel1);
    Assertions.assertEquals(roboBall.getPanel(), testEncounterPanel2);
    Assertions.assertEquals(fc.getPanel(), testEncounterPanel3);
    Assertions.assertEquals(sm.getPanel(), testEncounterPanel4);
    Assertions.assertEquals(sr.getPanel(), testEncounterPanel5);
    Assertions.assertTrue(testEncounterPanel.equals(testEncounterPanel));

  }


  @Test
  public void setStarsTest() {
    chicken.increaseStarsBy(4);
    Assertions.assertEquals(4, chicken.getStars());
    chicken.increaseStarsBy(0);
    Assertions.assertEquals(4, chicken.getStars());
    chicken.increaseStarsBy(3);
    Assertions.assertEquals(7, chicken.getStars());

  }

  @Test
  public void reduceStarsByTest() {
    chicken.increaseStarsBy(10);
    chicken.reduceStarsBy(2);
    Assertions.assertEquals(8, chicken.getStars());
    chicken.reduceStarsBy(0);
    Assertions.assertEquals(8, chicken.getStars());
    chicken.reduceStarsBy(9);
    Assertions.assertEquals(0, chicken.getStars());
  }



}
