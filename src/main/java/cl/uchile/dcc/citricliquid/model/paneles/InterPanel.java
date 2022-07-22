package cl.uchile.dcc.citricliquid.model.paneles;


import cl.uchile.dcc.citricliquid.model.exeptions.InvalidStateOperationException;
import cl.uchile.dcc.citricliquid.model.personaje.player.Player;
import java.util.List;
//Se tiene la interfaz que contiene los métodos
// que todos los paneles tienen en común.

/**
 * Interface that contains all commune methods for panels.
 *
 */
public interface InterPanel {
  /**
   * Get next panel.
   *
   * @return copy of this panel's next ones.
   */
  List<AbstracPanel> getNextPanels();

  /**
   * Returns a  panel's players .
   *
   * @return Players in the panel.
   */
  List<Player> getJugadores();

  /**
   * Add a new player to the panel .
   *
   * @param player  who is added to the panel.
   */
  void addPlayer(Player player);

  /**
   * take out a player from the panel .
   *
   * @param player  who is out of the panel.
   */
  void sacarJugador(Player player);

  /**
   * Adds a new adjacent panel to this one.
   *
   * @param panel the panel to be added.
   */
  void addNextPanel(AbstracPanel panel);

  /**
  * Panel is activated by player.
  *
  * @param player  who activated the panel.
  */
  void activate(Player player) throws InvalidStateOperationException;

  /**
   * player move one panel in direction dir.
   *
   * @param player  who is in the panel.
   * @param dir direction.
   */
  void mover_un_panel(Player player, int dir);

  /**
   * Return true if it is boss panel.Else, return false.
   *
   */
  boolean isBossPanel();

  /**
   * Return true if it is encounter panel.Else, return false.
   *
   */
  boolean isEncounterPanel();

  /**
   * Return true if it is bonus panel.Else, return false.
   *
   */
  boolean isBonusPanel();

  /**
   * Return true if it is drop panel.Else, return false.
   *
   */
  boolean isDropPanel();

  /**
   * Return true if it is draw panel.Else, return false.
   *
   */
  boolean isDrawPanel();

  /**
   * Return true if it is home panel.Else, return false.
   *
   */
  boolean isHomePanel();

  /**
   * Return true if it is neutral panel.Else, return false.
   *
   */
  boolean isNeutralPanel();
}
