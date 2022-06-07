package model.paneles.types;


import java.util.Objects;
import java.util.Random;
import model.paneles.AbstracPanel;
import model.personaje.enemies.wild.WildType;
import model.personaje.enemies.wild.WildUnit;
import model.personaje.player.Player;

/**
 * Class for create a encounter Panel.
 *
 */
public class EncounterPanel extends AbstracPanel {
  WildUnit rival;
  private final Random random;

  public EncounterPanel() {
    rival = null;
    random = new Random();
  }

  /**
   * Returns the rival.
   *
   * @return Planel's rival.
   */
  public WildUnit getRival() {
    return  rival;
  }

  /**
   * Aleatore number for generate enemies.
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
  private WildUnit create_random_Rival() {
    int rand = roll();
    WildUnit rival;
    if (rand == 1) {
      rival = new WildUnit(WildType.CHICKEN);
    } else if (rand == 2) {
      rival = new WildUnit(WildType.ROBO_BALL);
    } else {
      rival = new WildUnit(WildType.SEAGULL);
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
   * @param player  who activated the panel.
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
    if (o instanceof EncounterPanel) {
      if (((EncounterPanel) o).getRival() == null || this.getRival() == null) {
        if (((EncounterPanel) o).getRival() == null && this.getRival() == null) {
          return ((EncounterPanel) o).getJugadores().equals(this.getJugadores())
                  && ((EncounterPanel) o).getNextPanels().equals(this.getNextPanels());
        }
        return false;
      }
      return ((EncounterPanel) o).getJugadores().equals(this.getJugadores())
            && ((EncounterPanel) o).getNextPanels().equals(this.getNextPanels())
            && ((EncounterPanel) o).getRival().equals(this.getRival());
    } else {
      return  false;
    }
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(EncounterPanel.class);
  }
}