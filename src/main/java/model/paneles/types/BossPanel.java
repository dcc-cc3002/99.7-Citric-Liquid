package model.paneles.types;

import java.util.Objects;
import java.util.Random;
import model.paneles.AbstracPanel;
import model.personaje.enemies.boss.BossType;
import model.personaje.enemies.boss.BossUnit;
import model.personaje.player.Player;


/**
 * Class for create a BossPanel.
 *
 */
public class BossPanel extends AbstracPanel {
  BossUnit rival;
  private final Random random;

  /**
   * constructor for boss panel. No parameter.
   *
   */
  public BossPanel() {
    rival = null;
    random = new Random();
  }

  /**
   * get the rival in the panel.
   *
   * @return the rival.
   */
  public BossUnit getRival() {
    return  rival;
  }

  /**
   * Act like a dice.
   *
   * @return a uniformly distributed random value in [1, 3].
   */
  public int roll() {
    return random.nextInt(3) + 1;
  }

  /**
   * Create a random enemy.
   *
   * @return a random enemy.
   */
  private BossUnit create_random_Rival() {
    int rand = roll();
    BossUnit rival;
    if (rand == 1) {
      rival = new BossUnit(BossType.STORE_MANAGER);
    } else if (rand == 2) {
      rival = new BossUnit(BossType.SHIFU_ROBOT);
    } else {
      rival = new BossUnit(BossType.FLYING_CASTLE);
    }
    return rival;

  }

  /**
   * if the rival is dead, errase it.
   *
   * @return true if the rival is dead.
   */
  public Boolean rival_dead() {
    if (rival.getCurrentHp() == 0) {
      rival = null;
      return true;
    }
    return false;
  }


  /**
   * Panel is activated by player.
   *
   * @param player player who activated the panel.
   */
  @Override
  public void activate(final Player player) {
    if (getJugadores().contains(player)) {
      if (rival == null) {
        rival = create_random_Rival();
      }
      //SE INICIA LA BATALLA CON RIVAL
    }
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof BossPanel) {
      if (((BossPanel) o).getRival() == null || this.getRival() == null) {
        if (((BossPanel) o).getRival() == null && this.getRival() == null) {
          return ((BossPanel) o).getJugadores().equals(this.getJugadores())
                  && ((BossPanel) o).getNextPanels().equals(this.getNextPanels());
        }
        return false;
      }
      return ((BossPanel) o).getJugadores().equals(this.getJugadores())
            && ((BossPanel) o).getNextPanels().equals(this.getNextPanels())
            && ((BossPanel) o).getRival().equals(this.getRival());
    } else {
      return  false;
    }
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(BossPanel.class);
  }


}
