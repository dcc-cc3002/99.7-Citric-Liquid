package cl.uchile.dcc.citricliquid.model.paneles;

import cl.uchile.dcc.citricliquid.model.exeptions.InvalidStateOperationException;
import cl.uchile.dcc.citricliquid.model.personaje.player.Player;
import java.util.ArrayList;
import java.util.List;


/**
 * Abstrac class that is father of all the panels .
 *
 */
public abstract class AbstracPanel implements InterPanel {
  protected final List<AbstracPanel> nextPanels = new ArrayList<>();
  protected List<Player> jugadores = new ArrayList<>();


  /**
  * Abstrac constructor.
  *
  */
  protected AbstracPanel() {
  }

  @Override
  public void addNextPanel(final AbstracPanel panel) {
    nextPanels.add(panel);
  }

  @Override
  public void addPlayer(final Player player) {
    jugadores.add(player);
  }

  @Override
  public  List<Player> getJugadores() {
    return jugadores;
  }

  @Override
  public List<AbstracPanel> getNextPanels() {
    return List.copyOf(nextPanels);
  }

  @Override
  public  void sacarJugador(final Player player) {
    jugadores.remove(player);
  }


  @Override
  public void mover_un_panel(Player player, int dir) {
    if (this.getJugadores().contains(player)) {
      AbstracPanel nextP = this.getNextPanels().get(dir);
      this.sacarJugador(player);
      nextP.addPlayer(player);
      player.setActualPanel(nextP);
    }
  }

  @Override
  public boolean isBossPanel() {
    return false;
  }

  @Override
  public boolean isEncounterPanel() {
    return false;
  }

  @Override
  public boolean isBonusPanel() {
    return false;
  }

  @Override
  public boolean isDropPanel() {
    return false;
  }

  @Override
  public boolean isDrawPanel() {
    return false;
  }

  @Override
  public boolean isHomePanel() {
    return false;
  }

  @Override
  public boolean isNeutralPanel() {
    return false;
  }

  /**
   * Returns the panel's name as string.
   *
   */
  @Override
  public abstract String toString();


}
