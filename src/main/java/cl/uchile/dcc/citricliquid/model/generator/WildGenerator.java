package cl.uchile.dcc.citricliquid.model.generator;

import cl.uchile.dcc.citricliquid.model.Wild;

import java.util.Random;

public class WildGenerator {
  public static Wild chicken() {
    return new Wild("Chicken", 3, -1, -1, 1);
  }

  public static Wild roboBall() {
    return new Wild("Robo Ball", 3, -1, 1, -1);
  }

  public static Wild seagull() {
    return new Wild("Seagull", 3, 1, -1, -1);
  }

  public static Wild randomGenerator() {
    int random = new Random().nextInt(3);
    switch (random) {
      case 0:
        return chicken();
      case 1:
        return roboBall();
      case 2:
        return seagull();
      default:
        return new Wild("", 0, 0, 0, 0);
    }
  }
}
