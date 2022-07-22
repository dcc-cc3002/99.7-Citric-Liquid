package cl.uchile.dcc.citricliquid.model.paneles.types.enemies;

import cl.uchile.dcc.citricliquid.model.exeptions.InvalidStateOperationException;
import cl.uchile.dcc.citricliquid.model.paneles.AbstracPanel;
import cl.uchile.dcc.citricliquid.model.personaje.enemies.AbstractEnemies;
import cl.uchile.dcc.citricliquid.model.personaje.enemies.wild.WildUnit;
import cl.uchile.dcc.citricliquid.model.personaje.player.Player;

/**
 * An Enemy Panel is a panel that contains an enemy.
 *
 */
public abstract class AbstractPanelEnemies extends AbstracPanel {
  AbstractEnemies rival;

  protected AbstractPanelEnemies() {
    super();
    rival = null;
  }

  /**
   * Returns the rival.
   *
   * @return Planel's rival.
   */
  public AbstractEnemies getRival() {
    return  rival;
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
   * Panel is activated by player. It will start a battle against a wild unit or boss unit.
   *
   * @param player player who activated the panel.
   */
  @Override
  public void activate(final Player player) throws InvalidStateOperationException {
    if (getJugadores().contains(player)) {
      if (rival == null) {
        rival = WildUnit.create_random_Wild_Rival();
      }
      //SE INICIA LA BATALLA CON RIVAL
      player.toAttack();
      player.battle(rival);
      if (rival.getCurrentHp() > 0) {
        player.aleatoryChose(); //simula un input
        rival.battle(player);
      }
    }
  }






}
