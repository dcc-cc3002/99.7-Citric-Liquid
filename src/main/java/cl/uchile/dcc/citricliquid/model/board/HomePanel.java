package cl.uchile.dcc.citricliquid.model.board;

import cl.uchile.dcc.citricliquid.model.Player;
import org.jetbrains.annotations.NotNull;

public class HomePanel extends Panel {

  public HomePanel() {
    super(PanelType.HOME);
  }

  /**
   * Restores a player's HP in 1.
   */
  private static void applyHealTo(final @NotNull Player player) {
    player.setCurrentHp(player.getCurrentHp() + 1);
  }

  /**
   * Verifica que cumpla las condiciones de NormaCheck
   */
  private static void normaCheck(final @NotNull Player player) {
    switch (player.getNormaLevel()) {
      case 1:
        if (player.getStars() >= 10) {
          player.normaClear();
        }
        break;
      case 2:
        if (player.getStars() >= 30 && player.getWins() >= 2) {
          player.normaClear();
        }
        break;
      case 3:
        if (player.getStars() >= 70 && player.getWins() >= 5) {
          player.normaClear();
        }
        break;
      case 4:
        if (player.getStars() >= 120 && player.getWins() >= 9) {
          player.normaClear();
        }
        break;
      case 5:
        if (player.getStars() >= 200 && player.getWins() >= 14) {
          player.normaClear();
        }
        break;
      default:
        break;
    }
  }

  @Override
  public void activatedBy(Player player) {
    applyHealTo(player);
    normaCheck(player);
  }
}
