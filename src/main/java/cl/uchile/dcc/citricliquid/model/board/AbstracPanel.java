package cl.uchile.dcc.citricliquid.model.board;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import main.personaje.player.Player;

/**
 * Abstrac class that is father of all the panels .
 *
 */
public abstract class AbstracPanel implements InterPanel {
  protected final Set<InterPanel> nextPanels = new HashSet<>();
  protected List<Player> jugadores = new ArrayList<>();

  /**
  * Abstrac constructor.
  *
  */
  public AbstracPanel() {
  }

  public Set<InterPanel> getNextPanels() {
    return Set.copyOf(nextPanels);
  }

  public  List<Player> getJugadores() {
    return jugadores;
  }

  public void addPlayer(final Player player) {
    jugadores.add(player);
  }

  public  void sacarJugador(final Player player) {
    jugadores.remove(player);
  }

  public void addNextPanel(final InterPanel panel) {
    nextPanels.add(panel);
  }

  public void activate(final  Player player) {}


  @Override
  public boolean equals(Object o) {
    if (o instanceof  InterPanel) {
      return ((InterPanel) o).getJugadores().equals(this.getJugadores())
              && ((InterPanel) o).getNextPanels().equals(this.getNextPanels());
    } else {
      return  false;
    }
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(InterPanel.class);
  }

}
