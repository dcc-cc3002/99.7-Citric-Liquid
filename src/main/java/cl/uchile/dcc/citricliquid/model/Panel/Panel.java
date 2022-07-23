package cl.uchile.dcc.citricliquid.model.Panel;

import cl.uchile.dcc.citricliquid.model.Entities.BossUnit.FlyingCastle;
import cl.uchile.dcc.citricliquid.model.Entities.BossUnit.ShifuRobot;
import cl.uchile.dcc.citricliquid.model.Entities.BossUnit.StoreManager;
import cl.uchile.dcc.citricliquid.model.Entities.Entity;
import cl.uchile.dcc.citricliquid.model.Entities.Player;
import cl.uchile.dcc.citricliquid.model.Entities.Wild_Unit.Chicken;
import cl.uchile.dcc.citricliquid.model.Entities.Wild_Unit.Robo_Ball;
import cl.uchile.dcc.citricliquid.model.Entities.Wild_Unit.Seagull;
import org.jetbrains.annotations.NotNull;

import java.util.Random;


/**
 * Abstract class which handles all the types of panels.
 * It implements the interface with the corresponding methods.
 */
public abstract class Panel implements PanelInterface {
  private final PanelType type;
  /**
   * Here we create a tuple of 2 coordinates which represents the local position
   * of the panel in the world.
   */
  private final int x_coord;

  private final int y_coord;
  private final int[] paths;
  private Entity panelEntity;

  private Player player1;

  private Player player2;


  private Player player_home;


  /**
   * x and y are the coordinates of the panel in the world
   * type is the panel type according to PanelType
   * Also, every panel has the possible directions that could use the player.
   * The paths are mapped like this:
   * left = [1,0,0,0]
   * right = [0,1,0,0]
   * up = [0,0,1,0]
   * down = [0,0,0,1]
   */

  public Panel(final int x, final int y, final PanelType type, final int[] path) {
    this.x_coord = x;
    this.y_coord = y;
    this.type = type;
    this.paths = path;
  }

  /**
   * Restores player's HP in 1.
   */

  public void setPlayerHome(Player player) {
    if (player_home==null){
      this.player_home = player;
    }
  }

  public Player getPlayer_home() {
    return this.player_home;
  }


  /**
   * Applies bonuses according to the formula min{roll*norma,3*roll}.
   */


  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (! (o instanceof final Panel panel)) {
      return false;
    }
    return getPanelLocation()[0] == panel.getPanelLocation()[0]
            && getPanelLocation()[1] == panel.getPanelLocation()[1]
            && getType() == panel.getType()
            && getPaths()[0] == panel.getPaths()[0]
            && getPaths()[1] == panel.getPaths()[1];


  }

  /**
   * This function add a player to the array of players.
   * There be a max of 2 players in a panel.
   * When to players get into a fight, one of them will lose and will be removed from the panel.
   *
   * @param player Player to be added.
   */
  public void addPlayer(Player player) {
    if (player1 == null) {
      this.player1 = player;
      player.setLocation(this);
    } else if ((player1 != null) && (player2 == null)) {
      this.player2 = player;
      player.setLocation(this);
    } else {
      System.out.println("Cant add more players. Panel is full of players.");
    }
  }


  /**
   * Removes the specified player from the players that are in the panel.
   *
   * @param player the player that will be removed.
   */
  public void removePlayer(@NotNull Player player) {
    if (player.equals(player1)) {
      this.player1 = null;
    }
    if (player.equals(player2)) {
      this.player2 = null;
    }

  }

  /**
   * Get the players that are in the panel.
   *
   * @return an array of the players present in the panel
   */
  public Player[] getPlayers() {
    return new Player[]{player1, player2};
  }


  /**
   * Adds an entity.
   *
   * @param entity Adds an entity to the entity field.
   *               There be always only one Entity at a time in a panel.
   */
  public void addEntity(Entity entity) {
    if (this.panelEntity == null) {
      this.panelEntity = entity;
    }

  }

  /**
   * Removes an entity from the entity field.
   */
  public void removeEntity() {

    if (this.panelEntity != null) {
      this.panelEntity = null;
    }
  }

  /**
   * Clears a panel.
   */
  public void clearPanel() {
    this.player1 = null;
    this.player2 = null;

    this.panelEntity = null;
  }

  /**
   * A boolean that returns true if the player is in the panel.
   *
   * @param player Returns true if the player is in the panel.
   * @return the boolean
   */
  public boolean isPlayerInPanel(Player player) {

    return (player1 != null && (player1.equals(player)))
            || (player2 != null && player2.equals(player));
  }

  /**
   * A boolean that returns true if the entity is in the panel.
   *
   * @param entity Returns true if the entity is in the panel.
   * @return the boolean
   */
  public boolean isEntityInPanel(Entity entity) {
    return this.panelEntity == entity;
  }

  /**
   * Gets the entity in the entity field.
   *
   * @return the entity on the panel.
   */
  public Entity getEntity() {
    return this.panelEntity;
  }

  /**
   * Method which returns the paths that the players can follow from this panel.
   *
   * @return the array of possible paths.
   */
  public int[] getPaths() {
    return this.paths;
  }

  /**
   * Returns the type of the panel.
   *
   * @return the type of the panel.
   */
  public PanelType getType() {
    return type;
  }

  /**
   * Gets the panel location.
   *
   * @return a tuple containing the x and y coordinates.
   */
  public int[] getPanelLocation() {
    int[] coordinates = new int[2];
    coordinates[0] = this.x_coord;
    coordinates[1] = this.y_coord;
    return coordinates;
  }


  /**
   * Stars a battle.
   *
   * @param player a player.
   * @param enemy  the enemy.
   */
  public void setBattleAgainstAi(@NotNull Player player, @NotNull Entity enemy) {
    System.out.println("A battle has begun between "
            +
            player.getName() + " and " + enemy.getName());

    player.attack(enemy);
  }


  public void setBattleAgainstPlayer(Player player) {
    if (player==player1){

    }
    if (player==player2){

    }

    System.out.println("A battle has begun between "
            + player1.getName() + " and " + player2.getName());
    player.attack(player2);
  }

  /**
   * Generates a random boss.
   * See that if the panel already has an entity, the new entity will not be generated,
   * but the fight will happen no matter what.
   *
   * @param player the player that falls in the panel and trigger the boss
   */
  public void setBattleAgainstBossUnit(Player player) {


    Random rand = new Random();
    int n_entity = rand.nextInt(3);
    if (n_entity == 0) {

      this.addEntity(new FlyingCastle(this));
    }
    if (n_entity == 1) {
      this.addEntity(new ShifuRobot(this));
    }
    if (n_entity == 2) {
      this.addEntity(new StoreManager(this));
    }

    setBattleAgainstAi(player, panelEntity);

  }

  /**
   * Generates a random wild unit.
   * See that if the panel already has an entity, the new entity will not be generated,
   * but the fight will happen no matter what.
   *
   * @param player the player that triggers the wild units
   */

  public void setBattleAgainstWildUnit(Player player) {


    Random rand = new Random();
    int n_entity = rand.nextInt(3);
    if (n_entity == 0) {

      addEntity(new Chicken(this));
    }
    if (n_entity == 1) {
      addEntity(new Robo_Ball(this));
    }
    if (n_entity == 2) {
      addEntity(new Seagull(this));
    }

    setBattleAgainstAi(player, panelEntity);


  }

  /**
   * Activates a boss panel
   */
  public void setActive() {
  }


  public boolean isHomePanel(Player player) {
    return (this.getType() == PanelType.HOME) && (this.player_home.equals(player));
  }
}
