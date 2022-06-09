package model.paneles;


import java.util.List;
import java.util.Set;
import model.personaje.player.Player;
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
  Set<InterPanel> getNextPanels();

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
  void addNextPanel(InterPanel panel);

  /**
  * Panel is activated by player.
  *
  * @param player  who activated the panel.
  */
  void activate(Player player);
}
