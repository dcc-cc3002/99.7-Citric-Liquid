package test.panel;

//Este es un test que prueba interacciones entre paneles.

import model.paneles.types.BonusPanel;
import model.paneles.types.BossPanel;
import model.paneles.types.DrawPanel;
import model.paneles.types.EncounterPanel;
import model.paneles.types.HomePanel;
import model.personaje.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
  private HomePanel homePanel;
  private BonusPanel bonusPanel;
  private EncounterPanel encounterPanel;
  private BossPanel bossPanel;
  private Player suguri1;


  /**
   * create elements for all the test.
   *
   */
  @BeforeEach
  public void setUp() {
    suguri1 = new Player(PLAYER_NAME, BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD);
    bossPanel = new BossPanel();
    bonusPanel = new BonusPanel();
    homePanel = new HomePanel(suguri1);
    encounterPanel = new EncounterPanel();
  }

  @Test
  public void nextPanelTest() {
    var b1 = new BossPanel();
    var d1 = new DrawPanel();
    //Añade siguiente. Debe aumentar.
    bonusPanel.addNextPanel(b1);
    assertEquals(1, bonusPanel.getNextPanels().size());
    //Añadimos segundo. Debe aumentar
    bonusPanel.addNextPanel(d1);
    assertEquals(2, bonusPanel.getNextPanels().size());
    //Añadimos otro, pero es igual al anterior así que no aumenta tamaño.
    bonusPanel.addNextPanel(d1);
    assertEquals(2, bonusPanel.getNextPanels().size());
    //Se intenta agregar otra igual en atributos pero diferente en memoria. No debe aumentar
    var d2 = new DrawPanel();
    bonusPanel.addNextPanel(d2);
    assertEquals(2, bonusPanel.getNextPanels().size());
    //Se cambia un atributo de D2 para que ahora sea diferente a D1. Se agrega y si debe aumentar.
    d2.addPlayer(suguri1);
    bonusPanel.addNextPanel(d2);
    assertEquals(3, bonusPanel.getNextPanels().size());
  }

  @Test
  public void equalsTest() {
    var b1 = new BossPanel();
    var b2 = new BossPanel();

    //veo que sea true el equals
    assertEquals(bonusPanel, bonusPanel);

    assertEquals(bossPanel, b1);
    assertEquals(b1, b2);

    var e1 = new EncounterPanel();
    var e2 = new EncounterPanel();
    assertEquals(encounterPanel, e1);
    assertEquals(e1, e2);

    var h1 = new HomePanel(suguri1);
    var h2 = new HomePanel(suguri1);
    assertEquals(homePanel, h1);
    assertEquals(h2, h1);

    //Acá tienen que ser falsos
    b2.addNextPanel(e2);
    h1.addNextPanel(b1);

    assertNotEquals(h2, h1);
    assertNotEquals(b2, b1);
  }
}
