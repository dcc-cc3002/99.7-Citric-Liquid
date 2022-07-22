package cl.uchile.dcc.citricliquid.model.Handlers;

import cl.uchile.dcc.citricliquid.States.IdleState;
import cl.uchile.dcc.citricliquid.model.Entities.BossUnit.BossUnit;
import cl.uchile.dcc.citricliquid.model.Entities.BossUnit.FlyingCastle;
import cl.uchile.dcc.citricliquid.model.Entities.BossUnit.ShifuRobot;
import cl.uchile.dcc.citricliquid.model.Entities.BossUnit.StoreManager;
import cl.uchile.dcc.citricliquid.model.Entities.Character;
import cl.uchile.dcc.citricliquid.model.Entities.Entity;
import cl.uchile.dcc.citricliquid.model.Entities.Player;
import cl.uchile.dcc.citricliquid.model.Entities.Wild_Unit.Chicken;
import cl.uchile.dcc.citricliquid.model.Entities.Wild_Unit.Robo_Ball;
import cl.uchile.dcc.citricliquid.model.Entities.Wild_Unit.Seagull;
import cl.uchile.dcc.citricliquid.model.Entities.Wild_Unit.WildUnit;
import cl.uchile.dcc.citricliquid.model.Panel.*;
import org.jetbrains.annotations.NotNull;

import java.util.Scanner;
import java.util.stream.IntStream;

import static java.lang.Math.floor;

/**
 * Manages the flux of the game.
 *
 */
public class Controller{
  private Panel[] BoardPanels;

  private Player player1;
  private Player player2;
  private Player player3;
  private Player player4;

  private Player winner;

  private Player playerTurn;

  private int Turns =1;

  private int Chapter =0;

  public Controller() {}
  /**
   * Creates a player
   * @param name Name of the player.
   * @param hp Max hp for the player.
   * @param atk Atk of the player.
   * @param def Defense of the player.
   * @param evd Evasion of the player.
   * @param playerNumber The turn order of the player.
   */
  public void addPlayer(String name, final int hp, final int atk, final int def, final int evd,int playerNumber) {
    Player player = new Player(name, hp, atk, def, evd);
    if (playerNumber == 1) {
      player1 = player;
      player1.addNormaChangeNotification(new NormaObserver(this));
    }
    if (playerNumber == 2) {
      player2 = player;
      player2.addNormaChangeNotification(new NormaObserver(this));
    }
    if (playerNumber == 3) {
      player3 = player;
      player3.addNormaChangeNotification(new NormaObserver(this));
    }
    if (playerNumber == 4) {
      player4 = player;
      player4.addNormaChangeNotification(new NormaObserver(this));
    }
  }

  /**
   * Gets the panels.
   * @return an array of the panels.
   */
  public Panel[] getBoardPanels() {
    return BoardPanels;
  }
  //////////////////////////////CREATION OF PLAYERS////////////////////////////
  /**
   * The player1
   * @return A player
   */
  public Player getPlayer1() {
    return player1;
  }

  /**
   * The player2
   * @return A player
   */
  public Player getPlayer2() {
    return player2;
  }

  /**
   * The player3
   * @return A player
   */
  public Player getPlayer3() {
    return player3;
  }

  /**
   * The player4
   * @return A player
   */
  public Player getPlayer4() {
    return player4;
  }


  //////////////////////////GETTERS //////////////////////

  /**
   * The next functions are very obvious. They just call the original function from the characters class.
   * @param character
   * @return
   */
  public int getCharacterHp(@NotNull Character character  ){
    return character.getCurrentHp();
  }

  public int getCharacterATK(@NotNull Character character){
    return character.getAtk();
  }
  public int getCharacterDEF(@NotNull Character character){
    return character.getDef();
  }
  public int getCharacterEVD(@NotNull Character character){
    return character.getEvd();
  }
  public int getPlayerNorma(@NotNull Player player){
    return player.getNormaLevel();
  }
  public int getCharacterStars(@NotNull Character character){
    return character.getStars();
  }
  public String getCharacterName(@NotNull Character character){
    return character.getName();
  }


  public boolean isCharacterKo(@NotNull Character character){
    return character.isKo();
  }

  /**
   * Returns the player turns.
   * @return the player who is going to play.
   */
  public Player getPlayerTurn() {
    return playerTurn;
  }

  /**
   * The number of turns player in this chapter
   * @return
   */
  public int getTurns() {
    return Turns;
  }

  /**
   * The current chapter
   * @return
   */
  public int getChapter() {
    return Chapter;
  }

  /**
   * Gets the winner of the game.
   * @return the winner.
   */
  public Player getWinner() {
    return winner;
  }

  /////////////////////////SETTERS//////////////////////////////
  /**
   * Sets the turn for the player. If the player is recovering after the recovery check, he will not play the turn.
   * @param player who is going to play
   */
  public void setTurn(Player player){
    recover(player);
    if (player.isRecovering()==false){
      playerTurn = player;
    }
  }
  /**
   * Sets up the panels in the game
   * @param panels An array of panels.
   */
  public void setPanels(Panel[] panels){
    BoardPanels = panels;
  }

  /**
   * Sets the location of the player.
   * @param player The player.
   * @param panel The panel.
   */
  public void setPlayerLocation(@NotNull Player player, Panel panel){
    player.setLocation(panel);
  }

  /**
   * Sets the home panel of a player.
   * @param player who is going to have a home panel.
   * @param homePanel a panel.
   */
  public void setHomePanel(Player player, @NotNull HomePanel homePanel){
    homePanel.setPlayerHome(player);
  }
  /**
   * Checks if there is anybody at norma level 6.
   * @return Returns true if anybody is at norma level 6
   */
  public void setWinner(@NotNull Player player){
    this.winner=player;
    System.out.println("The player "+ player.getName() + " have won");
  }


  ////////////////////////////////////////////////////////////////////////////////////////

  ///////////////////////////////////CREATION OF NPC'S////////////////////////////////////
  public void createChicken(Panel panel){
    new Chicken(panel);
  }
  public void createRoboBall(Panel panel){
    new Robo_Ball(panel);
  }
  public void createSeagull(Panel panel){
    new Seagull(panel);
  }
  public void createFlyingCastle(Panel panel){
    new FlyingCastle(panel);
  }
  public void createShifuRobot(Panel panel){
    new ShifuRobot(panel);
  }
  public void createStoreManager(Panel panel){
    new StoreManager(panel);
  }
  ////////////////////////////////////////////////////////////////////////////////////////

  /**
   * After the handler sends the signal, this method handles the winner of the game.
   * @param player who wins the game.
   */
  public void NormaCheckBoard(@NotNull Player player){
    player.normaCheck();
  }

  /**
   * Increases the chapter if the number of turns played is ==5
   */
  public void increaseChapter(){
    if (Turns==5){
      Chapter +=1;
      Turns =1;
    }
  }

  /**
   * Method handles the rewards after a fight.
   * @param winner of the battle.
   * @param loser of the battle.
   */
  //////////////////////////REWARDS AFTER BATTLE///////////////////////
  public void PlayerBeatsPlayer(@NotNull Player winner, @NotNull Player loser){
    winner.setWins(winner.getWins()+2);
    int stars =(int) floor(loser.getStars()/2);
    winner.increaseStarsBy(stars);
    loser.decreaseStarsBy(stars);
  }
  /**
   * Method handles the rewards after a fight.
   * @param winner of the battle.
   * @param loser of the battle.
   */
  public void PlayerBeatsWildUnit(@NotNull Player winner, @NotNull WildUnit loser){
    winner.increaseStarsBy(loser.getStars());
    winner.setWins(winner.getWins()+1);
  }
  /**
   * Method handles the rewards after a fight.
   * @param winner of the battle.
   * @param loser of the battle.
   */
  public void PlayerBeatsBossUnit(@NotNull Player winner, @NotNull BossUnit loser){
    winner.increaseStarsBy(loser.getStars());
    winner.setWins(winner.getWins()+3);
  }
  /**
   * Method handles the rewards after a fight.
   * @param winner of the battle.
   * @param loser of the battle.
   */
  public void EntityBeatsPlayer(@NotNull Entity winner, @NotNull Player loser){
    int stars =(int) floor(loser.getStars()/2);
    winner.increaseStarsBy(stars);
    loser.decreaseStarsBy(stars);
  }

  /**
   * Converts a direction to an array
   * @param direction string of direction "left, up, right, down"
   * @return an array.
   */
  private int[] dirToArray(String direction){
    int[] dir =new int []{0,0,0,0};
    if (direction=="left"){dir= new int[]{1,0,0,0};}
    if (direction=="right"){dir= new int[]{0,1,0,0};}
    if (direction=="up"){dir= new int[]{0,0,1,0};}
    if (direction=="down"){dir= new int[]{0,0,0,1};}
    return dir;
  }

  /**
   * Moving 1 space.
   * @param player The moving player.
   * @param dir The direction of moving.
   */
  public void move1Space(@NotNull Player player, String dir){
    int[] direction = dirToArray(dir);
    player.move1space(this.BoardPanels,direction);
  }

  /**
   * Activate all boss panels if a character arrives to norma level 4.
   */
  public void ActivateBossPanel(){
    Panel[] panels=this.getBoardPanels();
    for (Panel panel:panels){
      if (panel.getType()==PanelType.BOSS){
        panel.setActive();
      }
    }
  }

  /**
   * Checks for enemies.
   * @param player
   * @return
   */
  public boolean checkForEnemies(@NotNull Player player){
    return player.checksForEnemyPlayer();
  }

  /**
   * Attacks a character.
   * @param player
   * @param enemy
   */
  public void attackCharacter(Player player,Character enemy){
    if (playerTurn==player){
      player.attack(enemy);
    }
  }

  /**
   * Recovers a player if it can.
   * @param player
   */
  public void recover(@NotNull Player player){
    if (player.isRecovering()){
      if (player.roll()>=Math.max(6-this.getChapter(),0)){
        player.ChangeState(new IdleState());
      }
    }
  }

  /**
   * This function runs the entire game. Sadly, i dont know how to test it because it needs inputs.
   * @param Player1
   * @param Player2
   * @param Player3
   * @param Player4
   * @param panels of the game.
   */
  public void game(Player Player1, Player Player2, Player Player3, Player Player4, Panel[] panels){
    /////////////SETUP OF THE GAME//////////////////
    player1=Player1;
    player2=Player2;
    player3=Player3;
    player4=Player4;
    setPanels(panels);

    ///////////////////////////////////////////////
    System.out.println("Game starts");
    /**
     * First, we need to check in every turn if there is anybody at norma level 6. If there is, the game ends.
     */
    while (this.winner==null){

      if (getTurns()==1){
        setTurn(player1);
      }
      if (getTurns()==2){
        setTurn(player2);
      }
      if (getTurns()==3){
        setTurn(player3);
      }
      if (getTurns()==4){
        setTurn(player4);
      }
      if (playerTurn.isKo()){
        //The player is KO, later we are taking care of this case.
      }
      else{
        int stars = (int) (floor(getChapter()/5) +1);
        playerTurn.increaseStarsBy(stars);
        // Now we increased the stars of the player. Time to roll the die.
        int roll =playerTurn.roll();
        //RECURSION FOR THE MOVEMENT
        while(roll>0){
          Panel actualPanel = playerTurn.getPlayerPanel();
          int[] possibleDirections = actualPanel.getPaths();
          int sum = IntStream.of(possibleDirections).sum();
          // CASE WE CAN ONLY GO ONE DIRECTION
          if(sum==1){
            playerTurn.move1space(getBoardPanels(),possibleDirections);
          }
          //CASE MANY DIRECTIONS
          else{
            System.out.println("The possible directions from this panel are: "+ possibleDirections + "(stands for {left,right,up,down})");
            System.out.println("Please, write exactly the desired direction. Eg. left (in lowercase)");
            Scanner scanner=new Scanner(System.in);
            String dir=scanner.nextLine();
            int[] direction =dirToArray(dir);
            this.move1Space(playerTurn,dir);
          }
          if(playerTurn.getPlayerPanel().isHomePanel(playerTurn)){
            System.out.println("You have fall in your Home Panel. You want to stop? Please answer Y or N");
            Scanner scanner1=new Scanner(System.in);
            String answer = scanner1.nextLine();
            if (answer=="Y"){
              System.out.println("You have chosen to stop");
              roll=0;
            }
            if (answer=="N"){
              System.out.println("You have chosen to keep going");
            }
          }
          roll=roll-1;

        }
        //NOW WE ARE OUT OF MOVEMENTS.WE HAVE FALLEN IN A PANEL.
        playerTurn.getPlayerPanel().activatedBy(playerTurn);
        //IF THERE IS A BATTLE OR A BONUS, IT WILL HAPPEN HERE.

        //NOW WE NEED TO CHECK IF THERE IS A ENEMY PLAYER.
        if (playerTurn.isKo()==false){
          if (playerTurn.checksForEnemyPlayer()){
            playerTurn.getPlayerPanel().setBattleAgainstPlayer(playerTurn);
          }
        }
      }
      Turns=+1; //INCREASE TURNS , SO THE NEXT ITERATION WILL BE ANOTHER TURN
      increaseChapter();//IF TURNS = 5 , WE CHANGE THE CHAPTER AND TURNS =1
      System.out.println("Your turn have ended");
    }
    System.out.println("Game have ended");
  }
}
