package test.panels;

import cl.uchile.dcc.citricliquid.model.paneles.types.DrawPanel;
import cl.uchile.dcc.citricliquid.model.paneles.types.enemies.BossPanel;
import cl.uchile.dcc.citricliquid.model.paneles.types.enemies.EncounterPanel;
import cl.uchile.dcc.citricliquid.model.paneles.types.star.BonusPanel;
import cl.uchile.dcc.citricliquid.model.personaje.player.Player;
import cl.uchile.dcc.citricliquid.model.state.states.turnstate.EffectPanelState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



//Esta es una clase que prueba interacciones entre paneles, es decir, next panel y equals.
//las características propias para cada panel es probado en su respectivo archivo.


/**
 * Class for test interaction within panels.
 *
 */
public class InteractionPanelsTest {
  private static final String PLAYER_NAME = "Suguri";
  private static final int BASE_HP = 4;
  private static final int BASE_ATK = 1;
  private static final int BASE_DEF = -1;
  private static final int BASE_EVD = 2;
  private BonusPanel bonusPanel;
  private EncounterPanel encounterPanel;
  private BossPanel bossPanel;
  private DrawPanel drawPanel;


  /**
   * create elements for all the test.
   *
   */
  @BeforeEach
  public void setUp() {
    Player suguri1 = new Player(PLAYER_NAME, BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD);
    suguri1.setState(new EffectPanelState(suguri1));
    bossPanel = new BossPanel();
    bonusPanel = new BonusPanel();
    encounterPanel = new EncounterPanel();
    drawPanel = new DrawPanel();
  }

  @Test
  public void nextPanelTest() {
    var b1 = new BossPanel();
    var d1 = new DrawPanel();
    bonusPanel.addNextPanel(b1);
    Assertions.assertEquals(1, bonusPanel.getNextPanels().size());
    bonusPanel.addNextPanel(d1);
    Assertions.assertEquals(2, bonusPanel.getNextPanels().size());
  }

  @Test
  public void notHomePanel() {
    Assertions.assertFalse(bonusPanel.isHomePanel());
    Assertions.assertFalse(bossPanel.isHomePanel());
    Assertions.assertFalse(encounterPanel.isHomePanel());
    Assertions.assertFalse(drawPanel.isHomePanel());
  }

}
