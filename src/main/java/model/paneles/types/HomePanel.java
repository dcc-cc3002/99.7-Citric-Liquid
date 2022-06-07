package model.paneles.types;

import java.util.Objects;
import model.paneles.AbstracPanel;
import model.personaje.player.Player;
import model.personaje.player.SubirCon;
import org.jetbrains.annotations.NotNull;

//Este panel tiene un parámeto extra que es el dueño del panel o owner.
// Al activarlo se recupera un punto
//de salud y también se hace normacheck.

/**
 * Class for create a home Panel.
 *
 */
public class HomePanel extends AbstracPanel {
  Player owner;

  /**
   * get the panel's owner.
   *
   * @param owner owner of the home panel.
   */
  public  HomePanel(Player owner) {
    this.owner = owner;
  }

  /**
   * get the panel's owner.
   *
   * @return panel's owner.
   */
  public Player getOwner() {
    return owner;
  }

  /**
   * NormaCheck using stars.
   *
   * @param player who has normacheck.
   */
  private  static void normaCheckStar(final  Player player) {
    if ((player.getNormaLevel() == 1 && (player.getStars() >= 10))
            || (player.getNormaLevel() == 2 && player.getStars() > 29)
            || (player.getNormaLevel() == 3 && player.getStars() > 69)
            || (player.getNormaLevel() == 4 && player.getStars() > 119)
            || (player.getNormaLevel() == 5 && player.getStars() > 199)) {
      player.normaClear();
    }
  }

  /**
   * NormaCheck using wins.
   *
   * @param player  who has normacheck.
   */
  private  static void normaCheckWins(final Player player) {
    if ((player.getNormaLevel() == 2 && player.getWins() > 1)
          || (player.getNormaLevel() == 3 &&  player.getWins() > 4)
          || (player.getNormaLevel() == 4 &&  player.getWins() > 8)
          || (player.getNormaLevel() == 5 && player.getWins() > 13)) {
      player.normaClear();
    }
  }

  /**
   * normacheck to the player.
   *
   * @param player  who has normacheck.
   */
  private   void makeNormaCheck(final @NotNull Player player) {
    if (player.getStar_or_wins() == SubirCon.WINS) {
      normaCheckWins(player);
    }
    if (player.getStar_or_wins() == SubirCon.STARS) {
      normaCheckStar(player);
    }

  }

  /**
   * The player's currentHp increments in 1.
   *
   * @param player  who recive heal.
   */
  private static void applyHealTo(final  Player player) {
    player.setCurrentHp(player.getCurrentHp() + 1);
  }

  /**
   * Panel is activated by player.
   *
   * @param player  who activated the panel.
   */
  @Override
  public void activate(final  Player player) {
    if (getJugadores().contains(player)) {
      makeNormaCheck(player);
      applyHealTo(player);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof HomePanel) {
      return ((HomePanel) o).getJugadores().equals(this.getJugadores())
              && ((HomePanel) o).getNextPanels().equals(this.getNextPanels())
              && ((HomePanel) o).getOwner().equals(this.getOwner());
    } else {
      return  false;
    }
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(HomePanel.class);
  }





}
