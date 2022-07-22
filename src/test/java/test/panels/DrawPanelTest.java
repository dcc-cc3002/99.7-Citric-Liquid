package test.panels;

import cl.uchile.dcc.citricliquid.model.exeptions.InvalidStateOperationException;
import cl.uchile.dcc.citricliquid.model.paneles.types.DrawPanel;
import cl.uchile.dcc.citricliquid.model.personaje.player.Player;
import cl.uchile.dcc.citricliquid.model.state.states.turnstate.EffectPanelState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Class for test the draw panel.
 *
 */
public class DrawPanelTest {

  private static final String PLAYER_NAM = "Suguri";
  private static final int BASE_HP = 4;
  private static final int BASE_ATK = 1;
  private static final int BASE_DEF = -1;
  private static final int BASE_EVD = 2;
  DrawPanel drawPanel;
  private Player suguri1;
  private Player suguri2;

  /**
   * create elements for all the test.
   *
   */
  @BeforeEach
  public void set_up() {
    drawPanel = new DrawPanel();
    suguri1 = new Player(PLAYER_NAM, BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD);
    suguri2 = new Player(PLAYER_NAM, BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD);
    suguri1.setState(new EffectPanelState(suguri1));
    suguri2.setState(new EffectPanelState(suguri2));
  }

  @Test
  public void constructorTest() {
    Assertions.assertEquals(0, drawPanel.getJugadores().size());
    Assertions.assertEquals(0, drawPanel.getNextPanels().size());
    Assertions.assertTrue(drawPanel.isDrawPanel());
    Assertions.assertFalse(drawPanel.isHomePanel());
  }

  @Test
  public void activatesTest() throws InvalidStateOperationException {
    drawPanel.addPlayer(suguri1);
    drawPanel.activate(suguri1);
    var expected = new Player(PLAYER_NAM, BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD);
    Assertions.assertEquals(1, drawPanel.getJugadores().size());
    Assertions.assertEquals(expected, suguri1);
  }

  @Test
  public void playersTest() {
    drawPanel.addPlayer(suguri1);
    Assertions.assertEquals(1, drawPanel.getJugadores().size());
    drawPanel.addPlayer(suguri2);
    Assertions.assertEquals(2, drawPanel.getJugadores().size());
    drawPanel.sacarJugador(suguri1);
    Assertions.assertEquals(1, drawPanel.getJugadores().size());
    drawPanel.sacarJugador(suguri2);
    Assertions.assertEquals(0, drawPanel.getJugadores().size());
  }


  @Test
  public void toStringTest() {
    Assertions.assertEquals("DrawPanel", drawPanel.toString());
  }

}
