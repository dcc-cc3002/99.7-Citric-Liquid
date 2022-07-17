package cl.uchile.dcc.citricliquid.model.board;

import cl.uchile.dcc.citricliquid.model.unit.*;

import java.util.*;

import org.jetbrains.annotations.NotNull;

/**
 * Class that represents a panel in the board of the game.
 *
 * @author <a href="mailto:ignacio.slater@ug.uchile.cl">Ignacio Slater Muñoz</a>.
 * @version 1.1.222804
 * @since 1.0
 */
public abstract class AbstractPanel implements Panel{
  private final int id;
  private final Set<Panel> nextPanels;
  private final List<Player> players;
  private Panel left;
  private Panel right;
  private Panel up;
  private Panel down;

  /**
   * Creates a new panel.
   *
   * @param id the identification code of the panel.
   */

  public AbstractPanel(int id) {
    this.id = id;
    nextPanels = new HashSet<>();
    players = new ArrayList<>();
  }

  /**
   * Returns this panel's id.
   */
  public int getID() {
    return id;
  }

  /**
   * Returns a list of the players on this panel.
   */
  public List<Player> getPlayers() {
    return players;
  }

  /**
   * Returns the panel located left side to the current one.
   */
  public Panel getLeft() {
    return left;
  }

  /**
   * Returns the panel located right side to the current one.
   */
  public Panel getRight() {
    return right;
  }

  /**
   * Returns the panel located above the current one.
   */
  public Panel getUp() {
    return up;
  }

  /**
   * Returns the panel located under the current one.
   */
  public Panel getDown() {
    return down;
  }

  /**
   * Sets this panel's left neighbour panel.
   */
  public void setLeft(Panel left) { this.left = left; }

  /**
   * Sets this panel's right neighbour panel.
   */
  public void setRight(Panel right) { this.right = right; }

  /**
   * Sets this panel's up neighbour panel.
   */
  public void setUp(Panel up) { this.up = up; }

  /**
   * Sets this panel's down neighbour panel.
   */
  public void setDown(Panel down) { this.down = down; }

  /**
   * Returns a copy of this panel's next ones.
   */
  public Set<Panel> getNextPanels() {
    return Set.copyOf(nextPanels);
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
   * Adds a new player to this panel.
   *
   * @param player the player to be added.
   */

  public void addPlayer(Player player) {
    players.add(player);
  }

  /**
   * Removes a player from this panel.
   *
   * @param player the player to be removed.
   */
  public void removePlayer(Player player) {
    players.remove(player);
  }

  /**
   * Executes the appropriate action to the player according to this panel's type.
   */
  @Override
  public abstract void activatedBy(final Player player);

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof AbstractPanel)) return false;
    AbstractPanel that = (AbstractPanel) o;
    return id == that.id &&
            Objects.equals(getNextPanels(),
                    that.getNextPanels()) &&
            Objects.equals(getPlayers(),
                    that.getPlayers()) &&
            Objects.equals(getLeft(),
                    that.getLeft()) &&
            Objects.equals(getRight(),
                    that.getRight()) &&
            Objects.equals(getUp(),
                    that.getUp()) &&
            Objects.equals(getDown(),
                    that.getDown());
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, getNextPanels(), getPlayers(), getLeft(), getRight(), getUp(), getDown());
  }
}
