package com.github.cc3002.citricjuice.model.board;

import com.github.cc3002.citricjuice.model.Player;

import java.util.HashSet;
import java.util.Set;

/**
 * Class that represents a panel in the board of the game.
 *
 * @author <a href="mailto:ignacio.slater@ug.uchile.cl">Ignacio Slater
 *     Muñoz</a>.
 * @version 1.0.6-b.4
 * @since 1.0
 */
public class Panel {
  private PanelType type;
  private Set<Panel> nextPanels = new HashSet<>();

  /**
   * Creates a new panel.
   *
   * @param type
   *     the type of the panel.
   */
  public Panel(PanelType type) {
    this.type = type;
  }

  /**
   * @return the type of this panel
   */
  public PanelType getType() {
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

  /**
   * Executes the appropriate action to the player according to this panel's
   * type.
   */
  public void activatedBy(final Player player) {
    switch (type) {
      case BONUS:
        applyBonusTo(player);
        break;
      case DROP:
        applyDropTo(player);
    }
  }

  private void applyDropTo(final Player player) {

  }

  private void applyBonusTo(final Player player) {
    player.increaseStarsBy(player.roll() * Math.min(player.getNormaLevel(), 3));
  }
}
