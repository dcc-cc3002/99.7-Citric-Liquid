package cl.uchile.dcc.citricliquid.model.board;

import cl.uchile.dcc.citricliquid.model.Player;
import cl.uchile.dcc.citricliquid.model.PlayersInPanel;

import java.util.HashSet;
import java.util.Set;

/**
 * Class that represents a panel in the board of the game.
 *
 * @author <a href="mailto:ignacio.slater@ug.uchile.cl">Ignacio Slater Muñoz</a>.
 * @version 1.1.222804
 * @since 1.0
 */
public abstract class Panel {
  private final PanelType type;
  private final Set<Panel> nextPanels;
  private final PlayersInPanel players_in_panel;

  /**
   * Creates a new panel.
   */
  public Panel(PanelType type) {
    nextPanels = new HashSet<>();
    players_in_panel = new PlayersInPanel();
    this.type = type;
  }

  /**
   * Returns a copy of this panel's next ones.
   */
  public Set<Panel> getNextPanels() {
    return Set.copyOf(nextPanels);
  }

  /**
   * Returns the players in panel
   */
  public PlayersInPanel getPlayers() {
    return players_in_panel;
  }

  /**
   * Adds a new adjacent panel to this one.
   *
   * @param panel the panel to be added.
   */
  public void addNextPanel(final Panel panel) {
    nextPanels.add(panel);
  }

  /**
   * Returns the panel's type.
   */
  public PanelType getType() {
    return type;
  }

  public abstract void activatedBy(Player player);

}
