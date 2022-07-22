package cl.uchile.dcc.citricliquid.model.controller;

import cl.uchile.dcc.citricliquid.model.controller.handlers.NormaObserver;
import cl.uchile.dcc.citricliquid.model.controller.handlers.StateRunContextChange;
import cl.uchile.dcc.citricliquid.model.exeptions.InvalidStateOperationException;
import cl.uchile.dcc.citricliquid.model.paneles.AbstracPanel;
import cl.uchile.dcc.citricliquid.model.paneles.types.DrawPanel;
import cl.uchile.dcc.citricliquid.model.paneles.types.HomePanel;
import cl.uchile.dcc.citricliquid.model.paneles.types.NeutralPanel;
import cl.uchile.dcc.citricliquid.model.paneles.types.enemies.BossPanel;
import cl.uchile.dcc.citricliquid.model.paneles.types.enemies.EncounterPanel;
import cl.uchile.dcc.citricliquid.model.paneles.types.star.BonusPanel;
import cl.uchile.dcc.citricliquid.model.paneles.types.star.DropPanel;
import cl.uchile.dcc.citricliquid.model.personaje.enemies.AbstractEnemies;
import cl.uchile.dcc.citricliquid.model.personaje.enemies.boss.BossType;
import cl.uchile.dcc.citricliquid.model.personaje.enemies.boss.BossUnit;
import cl.uchile.dcc.citricliquid.model.personaje.enemies.wild.WildType;
import cl.uchile.dcc.citricliquid.model.personaje.enemies.wild.WildUnit;
import cl.uchile.dcc.citricliquid.model.personaje.player.Player;
import cl.uchile.dcc.citricliquid.model.personaje.player.SubirCon;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;

/**
 * Class for controll the game.
 *
 */
public class Controller {
  private final List<AbstracPanel> paneles = new ArrayList<>(); //Conocemos todos los paneles

  private final List<Player> players = new ArrayList<>(); //Conocemos todos los jugadores.

  private Player winner; //El ganador del juego.

  private int actualTurn; //Dueño del turno.

  private int chapter; //Chapter en que nos encontramos.

  private boolean boss; //Si el boss puede ser puesto en paneles.

  /**
   * Inicialize variables.
   *
   */
  public Controller() {
    actualTurn = 0;  //Se inicia con el primer jugador en la lista, indexado con 0.
    chapter = 1; //Se inicia en el chapter 1.
    winner = null; //No hay un ganador al comienzo
    boss = false; //Comenzando, no se puede tener un boss.
  }

  //METODOS RELATIVOS AL CONSTRUCTOR.

  public boolean isBoss() {
    return boss;
  }


  /**
   * Return winner.
   *
   */
  public Player getWinner() {
    return winner;
  }


  /**
   * Return the int that represent the actual turn.
   *
   */
  public int getActualTurn() {
    return actualTurn;
  }

  /**
   * Get the game actual chapter.
   *
   */
  public int getChapter() {
    return chapter;
  }

  /**
   * Increase the chapter in 1.
   *
   */
  public void increaseChapter() {
    chapter++;
  }


  /**
   * Add a new panel.
   *
   * @param panel is the new panel to add.
   */
  public void addPanel(AbstracPanel panel) {
    paneles.add(panel);
  }

  /**
   * Get all the panels in the board.
   *
   * @return all the panels in the board.
   */
  public List<AbstracPanel> getPaneles() {
    return paneles;
  }

  public void addPlayers(Player newPlayer) {
    players.add(newPlayer);
  }

  public List<Player> getPlayers() {
    return players;
  }

  //Como se asume que los turnos van en el orden que los player fueron creados
  //tenemos esto en nuestra lista que el controlador conoce. Así que se puede
  //ir directamente al siguiente turno.
  /**
   * active next turn.
   *
   */
  public void nextTurn() {
    if (players.size() == 4 && winner == null) {
      if (actualTurn == 3) {
        increaseChapter(); //Se aumenta el chapter
        setChapterplayers(this.getChapter()); //Todos los jugadores conocen su chapter nuevo.
      }
      actualTurn = (actualTurn + 1) % 4;
    }
  }
  // METODOS PARA LOS PANELES.
  //metodos para crear los paneles

  /**
   * Create a new DrawPanel.
   *
   * @return DrawPanel.
   */
  public DrawPanel createDrawPanel() {
    DrawPanel drawPanel = new DrawPanel();
    addPanel(drawPanel);
    return drawPanel;
  }

  /**
   * Create a new home panel.
   *
   * @param owner owner of the new home panel.
   * @return HomePanel.
   */
  public HomePanel createHomePanel(Player owner) {
    HomePanel homePanel = new HomePanel(owner);
    addPanel(homePanel);
    return homePanel;
  }

  /**
   * Create a new Neutral panel.
   *
   * @return NeutralPanel.
   */
  public NeutralPanel createNeutralPanel() {
    NeutralPanel neutralPanel = new NeutralPanel();
    addPanel(neutralPanel);
    return neutralPanel;
  }

  /**
   * Create a new bonus panel.
   *
   * @return BonusPanel.
   */
  public BonusPanel createBonusPanel() {
    BonusPanel bonusPanel = new BonusPanel();
    addPanel(bonusPanel);
    return bonusPanel;
  }

  /**
   * Create a new drop panel.
   *
   * @return DropPanel.
   */
  public DropPanel createDropPanel() {
    DropPanel dropPanel = new DropPanel();
    addPanel(dropPanel);
    return dropPanel;
  }

  /**
   * Create a new Boss panel.
   *
   * @return BossPanel.
   */
  public BossPanel createBossPanel() {
    BossPanel bossPanel = new BossPanel();
    addPanel(bossPanel);
    return bossPanel;
  }

  /**
   * Create a new Encounter panel.
   *
   * @return EncounterPanel.
   */
  public EncounterPanel createEncounterPanel() {
    EncounterPanel encounterPanel = new EncounterPanel();
    addPanel(encounterPanel);
    return encounterPanel;
  }


  //METODOS PARA EDITAR LOS PANELES.

  /**
   * Add a new next panel to a panel.
   *
   * @param panel is the panel that add a new next panel.
   * @param agregado is the panel added.
   */
  public void addNextPanel(@NotNull AbstracPanel panel, AbstracPanel agregado) {
    panel.addNextPanel(agregado);
  }

  /**
   *  Set a enemy in all the bossPanels. It is the same enemy.
   *
   * @param enemy enemy to set.
   */
  public void setEnemyToBossPanel(AbstractEnemies enemy) {
    List<AbstracPanel> pan = getPaneles();
    for (AbstracPanel pa : pan) {
      if (pa.getClass() == BossPanel.class) {
        ((BossPanel) pa).setRival(enemy);
      }
    }
  }

  //metodo para poner un jugador en un panel.
  /**
   *  Set a player in a panel.
   *
   * @param  panel is the  panel to add a player.
   * @param player is the player to add in the panel.
   */
  public void setPlayerInPanel(Player player, @NotNull AbstracPanel panel) {
    panel.addPlayer(player); //Que el panel sepa que tiene al jugador
    player.setActualPanel(panel); //Que el jugador sepa en que panele está.
  }


  //MÉTODOS PARA DETENERSE.

  /**
   *  Return true if the player can decide stop moving for a possible battle.
   *
   *  @param player is the player in the panel and in move.
   */
  public boolean askToStopforBattle(Player player) {
    AbstracPanel actual = player.getActualPanel();
    if (actual.getJugadores().size() > 1) {
      return true;
    }
    return false;
  }


  /**
   *  Return true if the player can decide stop moving for his homePanel.
   *
   *  @param player is the player in the panel and in move.
   */
  public boolean askToStopforHomePanel(Player player) {
    AbstracPanel actual = player.getActualPanel();
    if (actual.getClass() == HomePanel.class) {
      if (((HomePanel) actual).getOwner() == player) {
        return true;
      }
    }
    return false;
  }

  /**
   *  Return true if the player can decide stop moving for multiple ways.
   *
   *  @param player is the player in the panel and in move.
   */
  public boolean askforWay(Player player) {
    AbstracPanel actual = player.getActualPanel();
    if (actual.getNextPanels().size() > 1) {
      return true;
    }
    return false;
  }


  /**
   *  move a player in the board some moves. Player can decide if a possible battle
   *  stop him or if want to stop in his home panel. Only if stop for battle return
   *  a negative number.
   *
   *  @param player is the player in the panel and in move.
   * @param moves max moves possible.
   * @param home if you want to stop for his home panel.
   * @param battle if you want to stop for battle.
   * @return effective number of moves.
   *
   */
  public int move(Player player, int moves, boolean battle, boolean home)
      throws InvalidStateOperationException {
    AbstracPanel actual = player.getActualPanel();
    System.out.println("Panel inicial:" + actual.toString());
    int i = 0;
    while (i < moves) {
      i++;
      player.mover_un_panel();
      actual = player.getActualPanel();
      if ((battle && askToStopforBattle(player))) {
        player.toDetenidoState();
        return -1;
      }
      if (((home && askToStopforHomePanel(player)))) {
        break;
      }
      //si debe elegir camino, retorna los que pudo hacer de continuo
      //y permanece en estado move. Esta parte es tentativa, ya que al implementar
      //la opción de inputs se puede manejar de otra forma, distinta a interrumpir
      //el movimiento.
      if (askforWay(player)) {
        return i;
      }
    }
    player.toDetenidoState();
    System.out.println("Panel final:" + actual.toString());
    return i;
  }

  //METODOS RELATIVOS AL JUGADOR.

  /**
   * Create a new player.
   *
   * @param evd  evasion points.
   * @param def defense points.
   * @param atk attack points.
   * @param maxHp max hit points.
   * @param name player's name.
   */
  public Player createPlayer(String name, int atk, int def, int evd, int maxHp) {
    Player player = new Player(name, atk, def, evd, maxHp);
    addPlayers(player);
    player.setChapters(1);

    //Se agregan los listeners para saber cuando un player sube de norma o cambia de panel.
    player.addNormaChangeNotification(new NormaObserver(this));
    player.addStateRunContextChange(new StateRunContextChange(this));

    return player;
  }

  /**
   *  Change the player's choose for his next norma clear.
   *
   *  @param player is the player in the panel and in move.
   *  @param decision decision of the player.
   */
  public void setstardOrWins(Player player, SubirCon decision) {
    if (decision == SubirCon.STARS) {
      player.subir_star();
    }
    if (decision == SubirCon.WINS) {
      player.subir_wins();
    }
  }

  public BossUnit createBossUnit(BossType type) {
    return new BossUnit(type);
  }

  public WildUnit createWillUnit(WildType type) {
    return new WildUnit(type);
  }


  /**
   *  Set the chapter to all the players.
   *
   */
  public void setChapterplayers(int chapte) {
    for (Player pla : players) {
      pla.setChapters(chapte);
    }
  }



  //METODOS LLAMADOS POR LOS LISTENERS

  /**
   *  Set the winner if a player has norma 6.
   *
   */
  public void checkNorma6() {
    for (Player pla : players) {
      if (pla.getNormaLevel() == 6) {
        winner = pla;
      }
    }
  }

  /**
   *  All the bossPanel will have a Boss Unit like enemeie.
   *  and change the variable boss of the class to true.
   *
   */
  public void activeBoss() {
    setEnemyToBossPanel(BossUnit.create_random_Boss_Rival());
    this.boss = true;
  }





  //METODOS HECHOS SOLO PARA TESTEAR.
  /**
   *  method for test when we have more that one possible way.
   *
   *  @param player is the player in the panel and in move.
   *  @param i direction to follow.
   */
  public void setWay(Player player, int i) {
    AbstracPanel actual = player.getActualPanel();
    actual.mover_un_panel(player, i);
  }

  /**
   *  Simule a battle player vs player.
   *
   */
  public void battle(Player atacante, Player atacado) throws InvalidStateOperationException {
    System.out.println("HAY ATAQUE");
    atacante.battle(atacado);
    if (atacado.getCurrentHp() > 0) {
      System.out.println("HAY CONTRAATAQUE");
      atacante.aleatoryChose();
      atacado.battle(atacante);
    }
  }

  /**
   * Search a player in the same panel, so maybe can start a battle.
   *
   * @Param player player who want to know possible enemy.
   */
  public Player possibleBattle(Player player) {
    AbstracPanel actual = player.getActualPanel();
    for (Player pla : actual.getJugadores()) {
      if (!player.equals(pla)) {
        return pla;
      }
    }
    return null;
  }



}
