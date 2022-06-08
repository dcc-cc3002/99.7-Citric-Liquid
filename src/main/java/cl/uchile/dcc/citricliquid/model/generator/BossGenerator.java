package cl.uchile.dcc.citricliquid.model.generator;

import cl.uchile.dcc.citricliquid.model.Boss;

import java.util.Random;

public class BossGenerator extends Generator {

  public static Boss storeManager() {
    return new Boss("Store Manager", 8, 3, 2, -1);
  }

  public static Boss shifuRobot() {
    return new Boss("Shifu Robot", 8, 3, 2, -1);
  }

  public static Boss flyingCastle() {
    return new Boss("Flying Castle", 8, 3, 2, -1);
  }

  public static Boss randomGenerator() {
    int random = new Random().nextInt(3);
    switch (random) {
      case 0:
        return storeManager();
      case 1:
        return shifuRobot();
      case 2:
        return flyingCastle();
      default:
        return new Boss("", 0, 0, 0, 0);
    }
  }
}
