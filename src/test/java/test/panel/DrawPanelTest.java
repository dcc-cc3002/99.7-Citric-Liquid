package test.panel;

import static org.junit.jupiter.api.Assertions.assertEquals;

import model.paneles.types.DrawPanel;
import model.personaje.player.Player;
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
  }

  @Test
  public void constructorTest() {
    assertEquals(0, drawPanel.getJugadores().size());
    assertEquals(0, drawPanel.getNextPanels().size());
  }

  @Test
  public void activatesTest() {
    drawPanel.addPlayer(suguri1);
    drawPanel.activate(suguri1);
    var expected = new Player(PLAYER_NAM, BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD);
    assertEquals(1, drawPanel.getJugadores().size());
    assertEquals(expected, suguri1);
  }

  @Test
  public void playersTest() {
    drawPanel.addPlayer(suguri1);
    assertEquals(1, drawPanel.getJugadores().size());
    drawPanel.addPlayer(suguri2);
    assertEquals(2, drawPanel.getJugadores().size());
    drawPanel.sacarJugador(suguri1);
    assertEquals(1, drawPanel.getJugadores().size());
    drawPanel.sacarJugador(suguri2);
    assertEquals(0, drawPanel.getJugadores().size());
  }

}
