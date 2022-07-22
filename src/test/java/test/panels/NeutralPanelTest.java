package test.panels;

import cl.uchile.dcc.citricliquid.model.exeptions.InvalidStateOperationException;
import cl.uchile.dcc.citricliquid.model.paneles.types.NeutralPanel;
import cl.uchile.dcc.citricliquid.model.personaje.player.Player;
import cl.uchile.dcc.citricliquid.model.state.states.turnstate.EffectPanelState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Class for test the neutral panel.
 *
 */
public class NeutralPanelTest {
  private NeutralPanel neutralTest;
  private static final String PLAYER_NAM = "Suguri";
  private static final int BASE_HP = 4;
  private static final int BASE_ATK = 1;
  private static final int BASE_DEF = -1;
  private static final int BASE_EVD = 2;
  private Player suguri1;
  private Player suguri2;

  /**
   * create elements for all the test.
   *
   */
  @BeforeEach
  public void setUp() {
    suguri1 = new Player(PLAYER_NAM, BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD);
    suguri2 = new Player(PLAYER_NAM, BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD);
    neutralTest = new NeutralPanel();
    suguri1.setState(new EffectPanelState(suguri1));
    suguri2.setState(new EffectPanelState(suguri2));
  }

  @Test
  public void constructorTest() {
    Assertions.assertEquals(0, neutralTest.getJugadores().size());
    Assertions.assertEquals(0, neutralTest.getNextPanels().size());
    Assertions.assertTrue(neutralTest.isNeutralPanel());
    Assertions.assertFalse(neutralTest.isDrawPanel());
  }

  @Test
  public void activatedTest() throws InvalidStateOperationException {
    neutralTest.addPlayer(suguri1);
    neutralTest.activate(suguri1);
    Assertions.assertEquals(1, neutralTest.getJugadores().size());
    var expected = new Player(PLAYER_NAM, BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD);
    Assertions.assertEquals(expected, suguri1);
  }

  @Test
  public void playersTest() {
    neutralTest.addPlayer(suguri1);
    Assertions.assertEquals(1, neutralTest.getJugadores().size());
    neutralTest.addPlayer(suguri2);
    Assertions.assertEquals(2, neutralTest.getJugadores().size());
    neutralTest.sacarJugador(suguri1);
    Assertions.assertEquals(1, neutralTest.getJugadores().size());
    neutralTest.sacarJugador(suguri2);
    Assertions.assertEquals(0, neutralTest.getJugadores().size());
  }

  @Test
  public void toStringTest() {
    Assertions.assertEquals("NeutralPanel", neutralTest.toString());
  }


}
