package cl.uchile.dcc.citricliquid.model;

import java.util.HashSet;
import java.util.Set;

public class Players {
  private final Set<Player> players = new HashSet<>();

  public void addPlayer(Player player) {
    players.add(player);
  }

  public boolean removePlayer(Player player) {
    return players.remove(player);
  }

  public Set<Player> getPlayers() {
    return players;
  }

  public boolean contains(Player player) {
    return players.contains(player);
  }
}
