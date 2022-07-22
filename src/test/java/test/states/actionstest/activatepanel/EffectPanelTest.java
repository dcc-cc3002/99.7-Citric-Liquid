package test.states.actionstest.activatepanel;

import cl.uchile.dcc.citricliquid.model.exeptions.InvalidStateOperationException;
import cl.uchile.dcc.citricliquid.model.paneles.types.HomePanel;
import cl.uchile.dcc.citricliquid.model.paneles.types.NeutralPanel;
import cl.uchile.dcc.citricliquid.model.paneles.types.enemies.BossPanel;
import cl.uchile.dcc.citricliquid.model.paneles.types.enemies.EncounterPanel;
import cl.uchile.dcc.citricliquid.model.paneles.types.star.BonusPanel;
import cl.uchile.dcc.citricliquid.model.paneles.types.star.DropPanel;
import cl.uchile.dcc.citricliquid.model.personaje.enemies.wild.WildType;
import cl.uchile.dcc.citricliquid.model.personaje.enemies.wild.WildUnit;
import cl.uchile.dcc.citricliquid.model.personaje.player.Player;
import cl.uchile.dcc.citricliquid.model.state.states.turnstate.EffectPanelState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;


/**
 * Test all efect panel possible.
 *
 */
public class EffectPanelTest {
  private Player suguri;
  private NeutralPanel neutralPanel;

  private HomePanel homePanel;
  private BonusPanel bonusPanel;

  private DropPanel dropPanel;

  private BossPanel bossPanel;

  private EncounterPanel encounterPanel;


  /**
   * create elements for all the test.
   *
   */
  @BeforeEach
  public void setUp() {
    suguri = new Player("Javi", 1, 1, -1, 2);

    neutralPanel = new NeutralPanel();
    homePanel = new HomePanel(suguri);
    bonusPanel = new BonusPanel();
    dropPanel = new DropPanel();
    bossPanel = new BossPanel();
    bossPanel.setRival(new WildUnit(WildType.ROBO_BALL));
    encounterPanel = new EncounterPanel();
    encounterPanel.setRival(new WildUnit(WildType.ROBO_BALL));

    suguri.setState(new EffectPanelState(suguri));
  }


  /**
   * Testing something with aleatory factors.
   * In this case, a battle ocurrs in the panel.
   *
   */
  @RepeatedTest(20)
  public void bossPanelTest() throws InvalidStateOperationException {
    bossPanel.addPlayer(suguri);
    suguri.setActualPanel(bossPanel);
    int i = suguri.runContextAction();
    Assertions.assertEquals(0, i);
    Assertions.assertTrue(suguri.isInactive());
  }

  /**
   * Testing something with aleatory factors.
   * In this case, a battle ocurrs in the panel.
   *
   */
  @RepeatedTest(20)
  public void encounterPanelTest() throws InvalidStateOperationException {
    encounterPanel.addPlayer(suguri);
    suguri.setActualPanel(encounterPanel);
    int i = suguri.runContextAction();
    Assertions.assertEquals(0, i);
    Assertions.assertTrue(suguri.isInactive());
  }

  @Test
  public void neutralPanelTest() throws InvalidStateOperationException {
    neutralPanel.addPlayer(suguri);
    suguri.setActualPanel(neutralPanel);
    int i = suguri.runContextAction();
    Assertions.assertEquals(0, i);
    Assertions.assertTrue(suguri.isInactive());
  }

  @Test
  public void homePanelPanelTest() throws InvalidStateOperationException {
    homePanel.addPlayer(suguri);
    suguri.setActualPanel(homePanel);
    suguri.increaseStarsBy(11);
    Assertions.assertEquals(1, suguri.getNormaLevel());
    int i = suguri.runContextAction();
    Assertions.assertEquals(0, i);
    Assertions.assertEquals(2, suguri.getNormaLevel());
    Assertions.assertTrue(suguri.isInactive());
  }

  @Test
  public void bonusPanelTest() throws InvalidStateOperationException {
    bonusPanel.addPlayer(suguri);
    suguri.setActualPanel(bonusPanel);
    int old = suguri.getStars();
    int i = suguri.runContextAction();
    Assertions.assertEquals(0, i);
    Assertions.assertTrue(suguri.getStars() > old);
    Assertions.assertTrue(suguri.isInactive());
  }

  @Test
  public void dropPanelTest() throws InvalidStateOperationException {
    dropPanel.addPlayer(suguri);
    suguri.setActualPanel(dropPanel);
    suguri.increaseStarsBy(10);
    int old = suguri.getStars();
    int i = suguri.runContextAction();
    Assertions.assertEquals(0, i);
    Assertions.assertTrue(suguri.getStars() < old);
    Assertions.assertTrue(suguri.isInactive());
  }


}
