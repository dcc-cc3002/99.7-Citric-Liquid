package cl.uchile.dcc.citricliquid.model.battle;

import cl.uchile.dcc.citricliquid.model.Boss;
import cl.uchile.dcc.citricliquid.model.Player;
import cl.uchile.dcc.citricliquid.model.Wild;
import cl.uchile.dcc.citricliquid.model.generator.BossGenerator;
import cl.uchile.dcc.citricliquid.model.generator.WildGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BattleTest {
  Player player;
  Player playerEnemy;
  Wild wildEnemy;
  Boss bossEnemy;

  @BeforeEach
  public void setUp() {
    player = new Player("Suguri", 4, 1, -1, 2);
    playerEnemy = new Player("Enemy Suguri", 4, 1, -1, 2);
    wildEnemy = WildGenerator.randomGenerator();
    bossEnemy = BossGenerator.randomGenerator();
  }

  @Test
  public void attackTest() {
    /**
     * Player vs player
     */
    System.out.println("Player vs player");
    System.out.println("DEF");
    Battle.attack(player, playerEnemy, Action.DEF);
    System.out.println("HP");
    System.out.println(playerEnemy.getCurrentHp());
    playerEnemy.setCurrentHp(playerEnemy.getMaxHp());

    System.out.println("EVD");
    Battle.attack(player, playerEnemy, Action.EVD);
    System.out.println("HP");
    System.out.println(playerEnemy.getCurrentHp());
    playerEnemy.setCurrentHp(playerEnemy.getMaxHp());

    System.out.println("Player vs wild");
    System.out.println("DEF");
    Battle.attack(player, wildEnemy, Action.DEF);
    System.out.println("HP");
    System.out.println(wildEnemy.getCurrentHp());
    wildEnemy.setCurrentHp(wildEnemy.getMaxHp());

    System.out.println("EVD");
    Battle.attack(player, wildEnemy, Action.EVD);
    System.out.println("HP");
    System.out.println(wildEnemy.getCurrentHp());
    wildEnemy.setCurrentHp(wildEnemy.getMaxHp());

    System.out.println("Player vs boss");
    System.out.println("DEF");
    Battle.attack(player, bossEnemy, Action.DEF);
    System.out.println("HP");
    System.out.println(bossEnemy.getCurrentHp());
    bossEnemy.setCurrentHp(bossEnemy.getMaxHp());

    System.out.println("EVD");
    Battle.attack(player, bossEnemy, Action.EVD);
    System.out.println("HP");
    System.out.println(bossEnemy.getCurrentHp());
    bossEnemy.setCurrentHp(bossEnemy.getMaxHp());
  }
}