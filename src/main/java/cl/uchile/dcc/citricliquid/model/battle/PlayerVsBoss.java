package cl.uchile.dcc.citricliquid.model.battle;

import cl.uchile.dcc.citricliquid.model.Boss;
import cl.uchile.dcc.citricliquid.model.Player;

import java.util.Random;
import java.util.Scanner;

public class PlayerVsBoss extends Battle {
  public static boolean turnPlayer(Player player, Boss boss) {
    int random = new Random().nextInt(2);
    if (random == 0) {
      attack(player, boss, Action.DEF);
      return false;
    } else {
      attack(player, boss, Action.EVD);
      return false;
    }
  }

  public static boolean turnBoss(Player player, Boss boss) {
    Scanner myObj = new Scanner(System.in);
    System.out.println(player.getName() + "debes escoger entre defender o evadir");
    String action = myObj.nextLine();
    switch (action) {
      case "defender":
        attack(boss, player, Action.DEF);
        return true;
      case "evadir":
        attack(boss, player, Action.EVD);
        return true;
      default:
        System.out.println("Opcion invalida, intentalo de nuevo");
        return false;
    }
  }

  public static boolean battling(Player player, Boss boss) {
    boolean turn = true;
    while (!(player.getCurrentHp() == 0 || boss.getCurrentHp() == 0)) {
      if (turn) {
        turn = turnPlayer(player, boss);
      } else {
        turn = turnBoss(player, boss);
      }
    }
    if (player.getCurrentHp() == 0) {
      boss.increaseStarsBy(player.getStars() / 2);
      player.reduceStarsBy(player.getStars() / 2);
      return false;
    } else {
      player.increaseWinsBy(1);
      player.increaseStarsBy(boss.getStars());
      return true;
    }
  }
}
