package test.panels.enemiepanels;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cl.uchile.dcc.citricliquid.model.exeptions.InvalidStateOperationException;
import cl.uchile.dcc.citricliquid.model.paneles.types.enemies.EncounterPanel;
import cl.uchile.dcc.citricliquid.model.personaje.enemies.AbstractEnemies;
import cl.uchile.dcc.citricliquid.model.personaje.enemies.wild.WildType;
import cl.uchile.dcc.citricliquid.model.personaje.enemies.wild.WildUnit;
import cl.uchile.dcc.citricliquid.model.personaje.player.Player;
import cl.uchile.dcc.citricliquid.model.state.states.turnstate.EffectPanelState;
import java.util.Objects;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;


/**
 * Class for test the encounter panel.
 *
 */
public class EncounterPanelTest {
  EncounterPanel encounterPanel;
  private static final String PLAYER_NAM = "Suguri";
  private static final int BASE_HP = 4;
  private static final int BASE_ATK = 1;
  private static final int BASE_DEF = -1;
  private static final int BASE_EVD = 2;
  private Player suguri1;
  private Player suguri2;
  long testSeed;

  /**
   * create elements for all the test.
   *
   */
  @BeforeEach
  public void set_up() {
    encounterPanel = new EncounterPanel();
    suguri1 = new Player(PLAYER_NAM, BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD);
    suguri2 = new Player(PLAYER_NAM, BASE_HP, BASE_ATK, BASE_DEF, BASE_EVD);
    suguri1.setState(new EffectPanelState(suguri1));
    suguri2.setState(new EffectPanelState(suguri2));
  }

  @Test
  public void constructorTest() {
    assertEquals(0, encounterPanel.getJugadores().size());
    assertEquals(0, encounterPanel.getNextPanels().size());
    Assertions.assertTrue(encounterPanel.isEncounterPanel());
    Assertions.assertFalse(encounterPanel.isBossPanel());

  }


  /**
   * Repeated test for proof random characteristic.
   *
   */
  @RepeatedTest(100)
  public void rivalTypeEncounterPanelTest() throws InvalidStateOperationException {
    assertNull(encounterPanel.getRival());
    encounterPanel.addPlayer(suguri1);
    encounterPanel.activate(suguri1);
    assertNotEquals(null, encounterPanel.getRival());
    assertNotEquals(null, encounterPanel.getRival().getName());
    String enemigoName = (encounterPanel.getRival().getName());
    //aseguramos que los enemigos generados sean de los tres permitidos.
    assertFalse((!Objects.equals(enemigoName, "Chicken")
            && !Objects.equals(enemigoName, "Robo ball")
            && !Objects.equals(enemigoName, "Seagull")));

  }

  //COMO FALTA IMPLEMENTAR BATALLAS ESTA ES UNA PRUEBA TENTATIVA.
  @Test
  public void encoun_Enemie_Creation() {
    assertNull(encounterPanel.getRival());
    encounterPanel.addPlayer(suguri1);
    encounterPanel.setRival(new WildUnit(WildType.ROBO_BALL));

    AbstractEnemies enemigo = encounterPanel.getRival();
    Assertions.assertFalse(encounterPanel.rival_dead());
    assertNotEquals(null, enemigo);

    enemigo.setCurrentHp(0);
    assertTrue(encounterPanel.rival_dead());
    assertNull(encounterPanel.getRival());
  }

  @Test
  public void playersTest() {
    encounterPanel.addPlayer(suguri1);
    assertEquals(1, encounterPanel.getJugadores().size());
    encounterPanel.addPlayer(suguri2);
    assertEquals(2, encounterPanel.getJugadores().size());
    encounterPanel.sacarJugador(suguri1);
    assertEquals(1, encounterPanel.getJugadores().size());
    encounterPanel.sacarJugador(suguri2);
    assertEquals(0, encounterPanel.getJugadores().size());
  }


  @Test
  public void setRivalTest() {
    assertNull(encounterPanel.getRival());
    encounterPanel.setRival(new WildUnit(WildType.ROBO_BALL));
    assertEquals(new WildUnit(WildType.ROBO_BALL), encounterPanel.getRival());

  }

  @Test
  public void toStringTest() {
    assertEquals("EncounterPanel", encounterPanel.toString());
  }
}
