package cl.uchile.dcc.citricliquid.model.board;

import cl.uchile.dcc.citricliquid.model.Player;
import java.util.HashSet;
import java.util.Set;
import org.jetbrains.annotations.NotNull;

public abstract class Panel {
  private PanelType type;
  private final Set<Panel> nextPanels = new HashSet<>();

  public Panel(PanelType type) {
    this.type = type;
  }


  public PanelType getType() {
    return type;
  }

  public Set<Panel> getNextPanels() {
    return Set.copyOf(nextPanels);
  }

  public void addNextPanel(final Panel panel) {
    nextPanels.add(panel);
  }


  public abstract void activatedBy(Player player);
}
