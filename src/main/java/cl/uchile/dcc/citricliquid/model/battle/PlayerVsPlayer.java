package cl.uchile.dcc.citricliquid.model.battle;

import cl.uchile.dcc.citricliquid.model.Player;

import java.util.Scanner;

public class PlayerVsPlayer extends Battle {
  private final Player player1;
  private final Player player2;
  private Player winner;
  private Player looser;

  public PlayerVsPlayer(Player player1, Player player2) {
    this.player1 = player1;
    this.player2 = player2;
  }

  public void initCards() {
    System.out.println("Pone en accion las cartas");
  }

  public boolean turn(Player player1, Player player2, boolean turn) {
    Scanner myObj = new Scanner(System.in);
    System.out.println(player2.getName() + "debes escoger entre defender o evadir");
    String action = myObj.nextLine();
    switch (action) {
      case "defender":
        attack(player1, player2, Action.DEF);
        return !turn;
      case "evadir":
        attack(player1, player2, Action.EVD);
        return !turn;
      default:
        System.out.println("Opcion invalida, intentalo de nuevo");
        return turn;
    }
  }

  public void battling() {
    boolean turn = true;
    while (!(player1.getCurrentHp() == 0 || player2.getCurrentHp() == 0)) {
      if (turn) {
        turn = turn(player1, player2, true);
      } else {
        turn = turn(player2, player1, false);
      }
    }
    if (player1.getCurrentHp() == 0) {
      winner = player2;
      looser = player1;
    } else {
      winner = player1;
      looser = player2;
    }
  }


  public void finish() {
    winner.increaseWinsBy(2);
    winner.increaseStarsBy(looser.getStars() / 2);
    looser.reduceStarsBy(looser.getStars() / 2);
  }
}
