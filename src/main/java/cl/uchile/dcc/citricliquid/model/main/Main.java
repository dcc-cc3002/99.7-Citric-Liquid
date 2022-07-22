package cl.uchile.dcc.citricliquid.model.main;

import cl.uchile.dcc.citricliquid.model.controller.Controller;
import cl.uchile.dcc.citricliquid.model.exeptions.InvalidStateOperationException;
import cl.uchile.dcc.citricliquid.model.paneles.AbstracPanel;
import cl.uchile.dcc.citricliquid.model.personaje.player.Player;
import cl.uchile.dcc.citricliquid.model.state.states.battle.defender.attacked.AttackedDefender;

/**
 * Clas for run an example of a game, driver by controller.
 *
 */
public class Main {

  /**
   * Example. First, I create the boad and player. After thar, game stars.
   *
   */
  public static void main(String[] args) throws InvalidStateOperationException {

    //Se crea lo necesario para un juego.
    Controller controller = new Controller();
    Player player1 = controller.createPlayer("player1", 4, 2, 1, 4);
    Player player2 = controller.createPlayer("player2", 4, 3, 2, 3);
    Player player3 = controller.createPlayer("player3", 4, 4, 1, 5);
    Player player4 = controller.createPlayer("player4", 5, 1, 1, 5);
    AbstracPanel neutralPanel1 = controller.createNeutralPanel();
    AbstracPanel neutralPanel2 = controller.createNeutralPanel();
    AbstracPanel neutralPanel3 = controller.createNeutralPanel();
    AbstracPanel neutralPanel4 = controller.createNeutralPanel();
    AbstracPanel neutralPanel5 = controller.createNeutralPanel();
    AbstracPanel neutralPanel6 = controller.createNeutralPanel();

    AbstracPanel encounterPanel = controller.createEncounterPanel();
    AbstracPanel homePanel1 =  controller.createHomePanel(player1);
    AbstracPanel homePanel2 =  controller.createHomePanel(player2);
    AbstracPanel homePanel3 =  controller.createHomePanel(player3);
    AbstracPanel homePanel4 =  controller.createHomePanel(player4);
    AbstracPanel bonusPanel = controller.createBonusPanel();
    AbstracPanel dropPanel = controller.createDropPanel();
    AbstracPanel bossPanel = controller.createBossPanel();


    controller.addNextPanel(neutralPanel1, neutralPanel2);
    controller.addNextPanel(neutralPanel2, homePanel1);
    controller.addNextPanel(homePanel1, neutralPanel3);
    controller.addNextPanel(neutralPanel3, bonusPanel);
    controller.addNextPanel(bonusPanel, encounterPanel);
    controller.addNextPanel(encounterPanel, neutralPanel4);
    controller.addNextPanel(neutralPanel4, bossPanel);
    controller.addNextPanel(bossPanel, homePanel2);
    controller.addNextPanel(homePanel2, homePanel3);
    controller.addNextPanel(homePanel3, dropPanel);
    controller.addNextPanel(dropPanel, homePanel4);
    controller.addNextPanel(homePanel4, neutralPanel5);
    controller.addNextPanel(neutralPanel5, neutralPanel6);
    controller.addNextPanel(neutralPanel6, neutralPanel1);


    controller.setPlayerInPanel(player1, homePanel1);
    controller.setPlayerInPanel(player2, homePanel2);
    controller.setPlayerInPanel(player3, homePanel3);
    controller.setPlayerInPanel(player4, homePanel4);


    //SE INICIA UN JUEGO, SE VA A TERMINAR MIENTRAS NO HAYA GANADOR.
    while (controller.getWinner() == null) {
      System.out.println("");
      Player player = controller.getPlayers().get(controller.getActualTurn());
      System.out.println(" CHAPTER: " + controller.getChapter() + " STARS: " + player.getStars());
      System.out.println("TURNO DE:" + player.getName() + " NORMA: " + player.getNormaLevel());
      player.toActiveState();

      if (player.isMoveState()) {
        int dice = player.roll();
        System.out.println("DADO ES " + dice);
        controller.move(player, dice, true, true);

        if (player.getActualPanel().getJugadores().size() > 1) {
          Player rival = controller.possibleBattle(player);
          System.out.println("PELEA EN PANEL: entre " + player.getName() + " y " + rival.getName());
          player.toAttack();
          rival.setState(new AttackedDefender(rival));
          controller.battle(player, rival);
          continue;
        }
        System.out.println("DETENIDO");
        player.toEffectPanelState();

      }
      controller.nextTurn();
    }
    System.out.println("GANADOR: " + controller.getWinner().getName());
  }
}
