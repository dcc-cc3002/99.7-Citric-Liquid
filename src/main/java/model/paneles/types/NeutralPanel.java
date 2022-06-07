package model.paneles.types;


import java.util.Objects;
import model.paneles.AbstracPanel;

/**
 * Class for create a neutral Panel.
 *
 */
public class NeutralPanel extends AbstracPanel {
  public  NeutralPanel() {
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof NeutralPanel) {
      return ((NeutralPanel) o).getJugadores().equals(this.getJugadores())
              && ((NeutralPanel) o).getNextPanels().equals(this.getNextPanels());
    } else {
      return  false;
    }
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(NeutralPanel.class);
  }

}
