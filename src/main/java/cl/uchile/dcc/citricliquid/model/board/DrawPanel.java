package cl.uchile.dcc.citricliquid.model.board;

import cl.uchile.dcc.citricliquid.model.Player;

public class DrawPanel extends Panel {

  public DrawPanel() {
    super(PanelType.DRAW);
  }

  static void draw(Player player) {
    System.out.println("El jugador " + player.getName() + " ha sacado una carta");
  }

  @Override
  public void activatedBy(Player player) {
    draw(player);
  }
}
