package cl.uchile.dcc.citricliquid.model;

import java.util.HashSet;
import java.util.Set;

public class PlayersInGame extends Players {
  private final Set<Player> players = new HashSet<>();

  public boolean bossCondition() {
    boolean output = false;
    for (Player player : players) {
      output = output || (player.getNormaLevel() >= 4);
    }
    return output;
  }
}
