package cl.uchile.dcc.citricliquid.model.board;

import cl.uchile.dcc.citricliquid.model.Player;

public class NeutralPanel extends Panel {
  public NeutralPanel() {
    super(PanelType.NEUTRAL);
  }

  @Override
  public void activatedBy(Player player) {
  }
}
