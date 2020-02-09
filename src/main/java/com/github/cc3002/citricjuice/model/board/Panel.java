package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.Player;

import java.util.HashSet;
import java.util.Set;

/**
 * Class that represents a panel in the board of the game.
 *
 * @author <a href="mailto:ignacio.slater@ug.uchile.cl">Ignacio Slater Muñoz</a>.
 * @version 1.0.2.2
 * @since 1.0
 */
public class Panel {
  private String type;
  private Set<Panel> nextPanels = new HashSet<>();

  /**
   * Creates a new panel.
   *
   * @param type
   *     the type of the panel.
   */
  public Panel(String type) {
    this.type = type;
  }

  /**
   * @return the type of this panel
   */
  public String getType() {
    return type;
  }

  /**
   * @return a copy of this panel's next ones.
   */
  public Set<Panel> getNextPanels() {
    return Set.copyOf(nextPanels);
  }

  /**
   * Adds a new adjacent panel to this one.
   *
   * @param panel
   *     the panel to be added.
   */
  public void addNextPanel(final Panel panel) {
    nextPanels.add(panel);
  }

  public void activatedBy(final Player testPlayer) {

  }
}
