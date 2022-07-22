package cl.uchile.dcc.citricliquid.model.Entities;

import cl.uchile.dcc.citricliquid.model.Panel.Panel;
import javafx.beans.property.Property;
import org.jetbrains.annotations.NotNull;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Handles the player. It's a concrete class.
 */
public class Player extends Character {
  private int normaLevel;


  private int horizontalPositionPlayer;

  private int verticalPositionPlayer;

  private int wins;

  private Panel actualPanel;

  private final PropertyChangeSupport NormaObserver = new PropertyChangeSupport(this);


  public void addNormaChangeNotification(PropertyChangeListener listener){
    NormaObserver.addPropertyChangeListener(listener);
  }
  /**
   * Creates a new character.
   *
   * @param name the character's name.
   * @param hp   the initial (and max) hit points of the character.
   * @param atk  the base damage the character does.
   * @param def  the base defense of the character.
   * @param evd  the base evasion of the character.
   */
  public Player(final String name, final int hp, final int atk, final int def,
                final int evd) {
    super(name, hp, atk, def, evd);
    normaLevel = 1;
  }

  /**
   * Increase the wins by amount.
   *
   * @param amount the amount to increase.
   */
  public void increaseWins(int amount) {
    this.wins += amount;
  }

  /**
   * Get the wins from a player.
   *
   * @return the wins.
   */
  public int getWins() {
    return this.wins;
  }

  /**
   * Set the wins that a player has.
   *
   * @param wins the wins.
   */
  public void setWins(int wins) {
    this.wins = wins;
  }

  /**
   * Set the panel location of a player and the new coordinates.
   * Modifies the actual panel parameter and the coordinates.
   *
   * @param panel in which the player will be.
   */


  public void setLocation(@NotNull Panel panel) {


    this.horizontalPositionPlayer = panel.getPanelLocation()[0];
    this.verticalPositionPlayer = panel.getPanelLocation()[1];
    this.actualPanel = panel;


  }


  public void setNormaLevel(int NormaLevel){
    int oldNorma=getNormaLevel();
    this.normaLevel=NormaLevel;
    NormaObserver.firePropertyChange("NORMA CHANGE",oldNorma,getNormaLevel());
  }

  /**
   * Returns the panel in which the player is.
   *
   * @return a panel
   */
  public Panel getPlayerPanel() {
    return this.actualPanel;
  }

  /**
   * Gets the tuple of the position of the player.
   *
   * @return the array of the position of the player.
   */
  public int[] getPlayerLocation() {
    int[] coordinates = new int[2];
    coordinates[0] = this.horizontalPositionPlayer;
    coordinates[1] = this.verticalPositionPlayer;

    return coordinates;
  }


  /**
   * This function does one movement on the direction chosen by the player starting from a panel.
   *
   * @param panels all the panels of the game.
   * @param dir    the direction of the move chosen by the player.
   */
  public void move1space(final Panel @NotNull [] panels, int @NotNull [] dir) {

    int n = panels.length;
    Panel initPanel = this.actualPanel;
    //CASE LEFT
    if (dir[0] == 1) {
      //The player wants to move to the left. We need to check if there is a panel to the left.
      if (initPanel.getPaths()[0] == 1) {
        //There is a panel to the left, so we can move over there
        //Now we need to search the panel which has the coordinates [x-1,y]
        int[] newCoordinates = new int[]{this.horizontalPositionPlayer - 1,
                this.verticalPositionPlayer};

        int i = 0;
        while (i < n) {
          int horizontalPanel = panels[i].getPanelLocation()[0];
          int verticalPanel = panels[i].getPanelLocation()[1];

          if ((horizontalPanel == newCoordinates[0])
                  &&
                  verticalPanel == newCoordinates[1]) {

            panels[i].addPlayer(this);
            initPanel.removePlayer(this);
            break;
          }
          i++;
        }
      } else {
        System.out.println("You cant travel to the left");
      }
    }
    //CASE RIGHT
    if (dir[1] == 1) {
      //The player wants to move to the RIGHT. We need to check if there is a panel to the RIGHT.
      if (initPanel.getPaths()[1] == 1) {
        //There is a panel to the RIGHT, so we can move over there
        //Now we need to search the panel which has the coordinates [x+1,y]
        int[] newCoordinates = new int[]{this.horizontalPositionPlayer + 1,
                this.verticalPositionPlayer};

        int i = 0;
        while (i < n) {
          int horizontalPanel = panels[i].getPanelLocation()[0];
          int verticalPanel = panels[i].getPanelLocation()[1];

          if ((horizontalPanel == newCoordinates[0])
                  &&
                  verticalPanel == newCoordinates[1]) {

            panels[i].addPlayer(this);
            initPanel.removePlayer(this);

            break;
          }
          i++;
        }

      } else {
        System.out.println("You cant travel to the right");
      }
    }
    //CASE UP
    if (dir[2] == 1) {
      //The player wants to move up. We need to check if there is a panel up.
      if (initPanel.getPaths()[2] == 1) {
        //There is a panel up, so we can move over there
        //Now we need to search the panel which has the coordinates [x,y]
        int[] newCoordinates = new int[]{this.horizontalPositionPlayer,
                this.verticalPositionPlayer + 1};

        int i = 0;
        while (i < n) {
          int horizontalPanel = panels[i].getPanelLocation()[0];
          int verticalPanel = panels[i].getPanelLocation()[1];

          if ((horizontalPanel == newCoordinates[0])
                  &&
                  verticalPanel == newCoordinates[1]) {

            panels[i].addPlayer(this);
            initPanel.removePlayer(this);

            break;
          }
          i++;
        }

      } else {
        System.out.println("You cant travel up");
      }
    }
    if (dir[3] == 1) {
      //The player wants to move down. We need to check if there is a panel down.
      if (initPanel.getPaths()[3] == 1) {
        //There is a panel to the down, so we can move over there
        //Now we need to search the panel which has the coordinates [x,y-1]
        int[] newCoordinates = new int[]{this.horizontalPositionPlayer,
                this.verticalPositionPlayer - 1};

        int i = 0;
        while (i < n) {
          int horizontalCoordinatePanel = panels[i].getPanelLocation()[0];
          int verticalCoordinatePanel = panels[i].getPanelLocation()[1];

          if ((horizontalCoordinatePanel == newCoordinates[0])
                  &&
                  verticalCoordinatePanel == newCoordinates[1]) {

            panels[i].addPlayer(this);
            initPanel.removePlayer(this);
            break;
          }
          i++;
        }

      } else {
        System.out.println("You cant travel down");
      }
    }
  }


  /**
   * Returns the current norma level.
   */
  public int getNormaLevel() {
    return normaLevel;
  }

  /**
   * Sets the conditions to do a normaclear.
   */
  public void normaCheck() {
    //NORMA 1->2
    if (getNormaLevel() == 1 && getStars() >= 10) {
      normaClear();
      System.out.println("Lvl UP!! , You are Norma Level " + getNormaLevel() + " now");
    }
    //NORMA 2->3
    if (getNormaLevel() == 2 && getStars() >= 30 && getWins() >= 2) {
      normaClear();
      System.out.println("Lvl UP!! , You are Norma Level " + getNormaLevel() + " now");
    }
    //NORMA 3->4
    if (getNormaLevel() == 3 && getStars() >= 70 && getWins() >= 5) {
      normaClear();
      System.out.println("Lvl UP!! , You are Norma Level " + getNormaLevel() + " now");
    }
    //NORMA 4->5
    if (getNormaLevel() == 4 && getStars() >= 120 && getWins() >= 9) {
      normaClear();
      System.out.println("Lvl UP!! , You are Norma Level " + getNormaLevel() + " now");
    }
    if (getNormaLevel() == 5 && getStars() >= 200 && getWins() >= 14) {
      normaClear();
      System.out.println("Lvl UP!! , You are Norma Level " + getNormaLevel() + " now");
      System.out.println("The player " + getName() + " Have won ");
    }

  }

  public void normaClear() {
    this.normaLevel++;
  }

  /**
   * This function checks if there is an enemy player already in the panel.
   * If there is an enemy, it will trigger a fight if the attacker wants to.
   */
  public boolean checksForEnemyPlayer() {
    if (actualPanel.getPlayers()[0] != null
            &&
            actualPanel.getPlayers()[1] != null) {
      return true;
    }else{
      return false;
    }
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (! (o instanceof final Player player)) {
      return false;
    }
    return getMaxHp() == player.getMaxHp()
            && getAtk() == player.getAtk()
            && getDef() == player.getDef()
            && getEvd() == player.getEvd()
            && getNormaLevel() == player.getNormaLevel()
            && getStars() == player.getStars()
            && getCurrentHp() == player.getCurrentHp()
            && getName().equals(player.getName());
  }

}
