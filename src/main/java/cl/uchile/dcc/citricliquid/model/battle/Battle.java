package cl.uchile.dcc.citricliquid.model.battle;

import cl.uchile.dcc.citricliquid.model.Entity;

public class Battle {

  public static void attack(Entity entity1, Entity entity2, Action action) {
    int final_attack = entity1.roll() + entity1.getAtk();
    System.out.println("final_attack");
    System.out.println(final_attack);
    int defense = entity2.roll() + entity2.getDef();
    System.out.println("defense");
    System.out.println(defense);
    System.out.println("damage");
    int damage;
    switch (action) {
      case DEF:
        damage = Math.max(1, final_attack - (defense));
        System.out.println(damage);

        entity2.setCurrentHp(entity2.getCurrentHp() - damage);
        break;
      case EVD:
        if (defense > final_attack) {
          damage = 0;
          System.out.println(damage);
        } else {
          damage = final_attack;
          System.out.println(damage);
        }
        entity2.setCurrentHp(entity2.getCurrentHp() - damage);
        break;
      default:
        break;
    }
  }
}
