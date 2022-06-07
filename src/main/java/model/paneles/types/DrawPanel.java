package model.paneles.types;

import java.util.Objects;
import model.paneles.AbstracPanel;
import model.personaje.player.Player;


/**
 * Class for create a draw Panel.
 *
 */
public class DrawPanel extends AbstracPanel {
  //No se tienen cartas así que no se va a implementar para esta tarea

  /**
   * constructor for draw panel. No parameter.
   *
   */
  public  DrawPanel() {
  }

  /**
   * Panel is activated by player.
   *
   * @param player activated the panel.
   */
  @Override
  public void activate(final Player player){
    //Acá irá lo que pase cuando se active
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof DrawPanel) {
      return ((DrawPanel) o).getJugadores().equals(this.getJugadores())
              && ((DrawPanel) o).getNextPanels().equals(this.getNextPanels());
    } else {
      return  false;
    }
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(DrawPanel.class);
  }
}
