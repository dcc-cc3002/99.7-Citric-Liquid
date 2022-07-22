
package cl.uchile.dcc.citricliquid.model.personaje.player;

import cl.uchile.dcc.citricliquid.model.exeptions.InvalidStateOperationException;
import cl.uchile.dcc.citricliquid.model.paneles.AbstracPanel;
import cl.uchile.dcc.citricliquid.model.personaje.AbstractCharacter;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 *Class for create a player.
 *
 */
public class Player extends AbstractCharacter {
  private int normaLevel;

  private int chapters;
  private int wins;
  private SubirCon starorwins;

  private AbstracPanel actualPanel;

  //Para observar la norma.
  private final PropertyChangeSupport normaChange = new PropertyChangeSupport(this);
  private final PropertyChangeSupport runContextState = new PropertyChangeSupport(this);

  /**
   * constructor for the class player.
   *
   * @param evd   evasion points.
   * @param def   defense points.
   * @param atk   attack points.
   * @param maxHp max hit points.
   * @param name  playe's name.
   */
  public Player(String name, int atk, int def, int evd, int maxHp) {
    super(name, atk, def, evd, maxHp);
    normaLevel = 1;
    chapters = 0;
    winValue = 2;
    starorwins = SubirCon.STARS;
    actualPanel = null;
  }


  //METODOS RELATIVOS AL CONTRUCTOR DE PLAYER.

  /**
   * get player's chapter.
   *
   * @return this player's chapter.
   */
  public int getChapters() {
    return chapters;
  }

  /**
   * set a chapters.
   *
   * @param chap new Chapters.
   */
  public void setChapters(int chap) {
    chapters = chap;
  }


  /**
   * get player's wins count.
   *
   * @return this player's wins count.
   */
  public int getWins() {
    return wins;
  }

  /**
   * get player's actual panel.
   *
   * @return this player's actual panel.
   */
  public AbstracPanel getActualPanel() {
    return actualPanel;
  }

  /**
   * set player's new actual panel.
   *
   * @param newPanel new panel.
   */
  public void setActualPanel(AbstracPanel newPanel) {
    actualPanel = newPanel;
  }

  /**
   * get player's norma up preferences.
   *
   * @return the character's preferences for norma up.
   */
  public SubirCon getStar_or_wins() {
    return starorwins;
  }

  /**
   * get player's norma level.
   *
   * @return the current norma level.
   */
  public int getNormaLevel() {
    return normaLevel;
  }

  /**
   * Increases this player's wins count by an amount.
   *
   * @param amount numeric value for reduce player's wins.
   */
  public void increaseWinsBy(final int amount) {
    wins += amount;
  }

  /**
   * next norma check will be with stars.
   */
  public void subir_star() {
    starorwins = SubirCon.STARS;
  }

  /**
   * next norma check will be with wins.
   */
  public void subir_wins() {
    starorwins = SubirCon.WINS;
  }

  @Override
  public boolean equals(final Object o) {
    if (o instanceof Player) {
      return ((Player) o).getMaxHp() == (this.getMaxHp())
        && ((Player) o).getAtk() == this.getAtk()
        && ((Player) o).getDef() == this.getDef()
        && ((Player) o).getEvd() == this.getEvd()
        && ((Player) o).getNormaLevel() == this.getNormaLevel()
        && ((Player) o).getStars() == this.getStars()
        && ((Player) o).getWins() == this.getWins()
        && ((Player) o).getCurrentHp() == this.getCurrentHp()
        && ((Player) o).getName().equals(this.getName())
        && ((Player) o).getStar_or_wins().equals(this.getStar_or_wins());
    } else {
      return false;
    }
  }

  /**
   *  player's norma level up.
   *
   */
  public void normaClear() {
    int old = normaLevel;
    normaLevel++;
    normaChange.firePropertyChange("NORMA_UP", old, this.normaLevel);
  }




  //METODOS RELACIONADOS A LOS ESTADOS.
  //SON ESTADOS QUE SOLO UN JUGADOR PUEDE TENER.

  /**
   * change state to StartTurn state.
   */
  public void toStartTurn() throws InvalidStateOperationException {
    state.toStartTurn();
    runContextState.firePropertyChange("RunContextAction", true, this);
  }

  /**
   * change state to RecoveryState.
   */
  public void toRecoveryState() throws InvalidStateOperationException {
    state.toRecoveryState();
    runContextState.firePropertyChange("RunContextAction", true, this);
  }

  /**
   * change state to MoveState.
   */
  public void toMoveState() throws InvalidStateOperationException {
    state.toMoveState();
  }

  /**
   * change state to Detenido.
   */
  public void toDetenidoState() throws InvalidStateOperationException {
    state.toDetenidoState();
  }

  /**
   * change state to attack.
   */
  public void toAttack() throws InvalidStateOperationException {
    state.toAttack();
  }

  /**
   * change state to active.
   */
  public void toActiveState() throws InvalidStateOperationException {
    state.toActiveState();
    runContextState.firePropertyChange("RunContextAction", true, this);
  }

  /**
   * change state to Effect panel.
   */
  public void toEffectPanelState() throws InvalidStateOperationException {
    state.toEffectPanelState();
    runContextState.firePropertyChange("RunContextAction", true, this);
  }

  /**
   * change state to Counter Attacked state.
   */
  public void toCounterAttacked() throws InvalidStateOperationException {
    state.toCounterAttacked();
  }

  /**
   * change state to CounterAttackedDefender.
   */
  public void toCounterAttackedDefender() throws InvalidStateOperationException {
    state.toCounterAttackedDefender();
  }

  /**
   * change state to CounterAttackedEsquivar.
   */
  public void toCounterAttackedEsquivar() throws InvalidStateOperationException {
    state.toCounterAttackedEsquivar();
  }


  @Override
  public void toAttackedState() throws InvalidStateOperationException {
    state.toAttackedState();
  }

  /**
   * return 0 if you can move.
   */
  public int mover_un_panel() {
    return state.mover_un_panel();
  }

  /**
   * Run the action in the state.
   */
  public int runContextAction() throws InvalidStateOperationException {
    return state.runAction();
  }




  //MÉTODOS RELATIVOS AL HANDLER
  /**
   * Suscribe a Listener for the norma clear.
   */
  public void addNormaChangeNotification(PropertyChangeListener listener) {
    normaChange.addPropertyChangeListener(listener);
  }

  /**
   * Suscribe a Listener for the change of state with contect action.
   */
  public void addStateRunContextChange(PropertyChangeListener listener) {
    runContextState.addPropertyChangeListener(listener);
  }





  //METODO PARA SIMULAR UN INPUT DEL USUARIO. ES PARA FACILITAR
  //EL EJEMPLO DEL JUEGO.
  /**
   * Send a player to Defender or Esquive in a counterattack aleatory. This method is used because
   * we don't have any input.
   */
  public void aleatoryChose() throws InvalidStateOperationException {
    int dice = roll();
    if (dice % 2 == 0) {
      this.toCounterAttackedDefender();
    } else {
      this.toCounterAttackedEsquivar();
    }
  }

}