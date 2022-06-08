package cl.uchile.dcc.citricliquid.model.battle;

import cl.uchile.dcc.citricliquid.model.Player;
import cl.uchile.dcc.citricliquid.model.Wild;

import java.util.Random;
import java.util.Scanner;

public class PlayerVsWild extends Battle {

  public static boolean turnPlayer(Player player, Wild wild) {
    int random = new Random().nextInt(2);
    if (random == 0) {
      attack(player, wild, Action.DEF);
      return false;
    } else {
      attack(player, wild, Action.EVD);
      return false;
    }
  }

  public static boolean turnWild(Player player, Wild wild) {
    Scanner myObj = new Scanner(System.in);
    System.out.println(player.getName() + "debes escoger entre defender o evadir");
    String action = myObj.nextLine();
    switch (action) {
      case "defender":
        attack(wild, player, Action.DEF);
        return true;
      case "evadir":
        attack(wild, player, Action.EVD);
        return true;
      default:
        System.out.println("Opcion invalida, intentalo de nuevo");
        return false;
    }
  }

  public static boolean battling(Player player, Wild wild) {
    boolean turn = true;
    while (!(player.getCurrentHp() == 0 || wild.getCurrentHp() == 0)) {
      if (turn) {
        turn = turnPlayer(player, wild);
      } else {
        turn = turnWild(player, wild);
      }
    }
    if (player.getCurrentHp() == 0) {
      wild.increaseStarsBy(player.getStars() / 2);
      player.reduceStarsBy(player.getStars() / 2);
      return false;
    } else {
      player.increaseWinsBy(1);
      player.increaseStarsBy(wild.getStars());
      return true;
    }
  }
}
