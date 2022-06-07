package test.panel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import model.paneles.types.HomePanel;
import model.personaje.player.Player;
import model.personaje.player.SubirCon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Class for test the home panel.
 *
 */
public class HomePanelTest {
  HomePanel homePanel;

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
    homePanel = new HomePanel(suguri1);
  }

  @Test
  public void constructorTest() {
    assertEquals(0, homePanel.getJugadores().size());
    assertEquals(0, homePanel.getNextPanels().size());
  }

  @Test
  public void normaCheckWins() {
    //el jugador dueño del panel está en el panel
    homePanel.addPlayer(suguri1);
    int i;
    for (i = 1; i < 20; i++) {
      suguri1.increaseWinsBy(1);
      homePanel.activate(suguri1);
      if (i == 1) {
        suguri1.normaClear();
        suguri1.subir_wins();
      }
      if (i >= 2  && i < 5) {
        Assertions.assertEquals(3, suguri1.getNormaLevel());
      }
      if (i >= 5 && i < 9) {
        Assertions.assertEquals(4, suguri1.getNormaLevel());
      }
      if (i >= 9 && i < 14) {
        Assertions.assertEquals(5, suguri1.getNormaLevel());
      }
      if (i >= 14) {
        Assertions.assertEquals(6, suguri1.getNormaLevel());
      }
    }
  }

  @Test
  public void normaCheckStars() {
    //se pone  suguri en el panel
    homePanel.addPlayer(suguri1);
    int i;
    for (i = 1; i < 220; i++) {
      suguri1.increaseStarsBy(1);
      Assertions.assertEquals(SubirCon.STARS, suguri1.getStar_or_wins());
      homePanel.activate(suguri1);
      if (i < 10) {
        Assertions.assertEquals(1, suguri1.getNormaLevel());
      }
      if (i >= 10 && i < 30) {
        Assertions.assertEquals(2, suguri1.getNormaLevel());
      }
      if (i >= 30 && i < 70) {
        Assertions.assertEquals(3, suguri1.getNormaLevel());
      }
      if (i >= 70 && i < 120) {
        Assertions.assertEquals(4, suguri1.getNormaLevel());
      }
      if (i >= 120 && i < 200) {
        Assertions.assertEquals(5, suguri1.getNormaLevel());
      }
      if (i >= 200) {
        Assertions.assertEquals(6, suguri1.getNormaLevel());
      }
    }
  }

  @Test
  public void normaCheckMix() {
    //se pone  suguri en el panel
    homePanel.addPlayer(suguri1);
    suguri1.increaseStarsBy(10);
    homePanel.activate(suguri1);
    Assertions.assertEquals(2, suguri1.getNormaLevel());
    suguri1.increaseWinsBy(3);
    homePanel.activate(suguri1);
    assertNotEquals(3, suguri1.getNormaLevel());
    suguri1.subir_wins();
    homePanel.activate(suguri1);
    Assertions.assertEquals(3, suguri1.getNormaLevel());
    suguri1.increaseStarsBy(69);
    homePanel.activate(suguri1);
    Assertions.assertEquals(3, suguri1.getNormaLevel());
    suguri1.subir_star();
    homePanel.activate(suguri1);
    Assertions.assertEquals(4, suguri1.getNormaLevel());
  }

  @Test
  public void healTest() {
    assertEquals(suguri1.getMaxHp(), suguri1.getCurrentHp());
    suguri1.setCurrentHp(suguri1.getMaxHp() - 1);
    homePanel.addPlayer(suguri1);
    homePanel.activate(suguri1);
    assertEquals(suguri1.getMaxHp(), suguri1.getCurrentHp());
    suguri1.setCurrentHp(suguri1.getMaxHp() - 2);
    homePanel.activate(suguri1);
    assertEquals(suguri1.getMaxHp() - 1, suguri1.getCurrentHp());
  }

  @Test
  public void playersTest() {
    homePanel.addPlayer(suguri1);
    assertEquals(1, homePanel.getJugadores().size());
    homePanel.addPlayer(suguri2);
    assertEquals(2, homePanel.getJugadores().size());
    homePanel.sacarJugador(suguri1);
    assertEquals(1, homePanel.getJugadores().size());
    homePanel.sacarJugador(suguri2);
    assertEquals(0, homePanel.getJugadores().size());
  }
}
