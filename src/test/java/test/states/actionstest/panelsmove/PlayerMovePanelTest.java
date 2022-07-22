package test.states.actionstest.panelsmove;

import cl.uchile.dcc.citricliquid.model.paneles.types.NeutralPanel;
import cl.uchile.dcc.citricliquid.model.paneles.types.star.BonusPanel;
import cl.uchile.dcc.citricliquid.model.personaje.player.Player;
import cl.uchile.dcc.citricliquid.model.state.states.turnstate.MoveState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *  test for move state.
 *
 */
public class PlayerMovePanelTest {
  private Player suguri;
  private BonusPanel bonusPanel;

  /**
   * create elements for all the test.
   *
   */
  @BeforeEach
  public void setUp() {
    suguri = new Player("Javi", 4, 1, -1, 2);
    suguri.setState(new MoveState(suguri));
    NeutralPanel neutralPanel = new NeutralPanel();
    bonusPanel = new BonusPanel();
    neutralPanel.addNextPanel(bonusPanel);
    neutralPanel.addPlayer(suguri);
    suguri.setActualPanel(neutralPanel);
  }

  @Test
  public void moveOnePanelTest() {
    Assertions.assertFalse(bonusPanel.getJugadores().contains(suguri));
    int move = suguri.mover_un_panel();
    Assertions.assertEquals(0, move);
    Assertions.assertTrue(bonusPanel.getJugadores().contains(suguri));

  }
}
