package cl.uchile.dcc.citricliquid.model.board;

import cl.uchile.dcc.citricliquid.model.unit.*;

import java.util.List;
import java.util.Set;

public interface Panel {
    Set<Panel> getNextPanels();
    void addNextPanel(Panel expectedPanel);
    void activatedBy(Player player);

    List<Player> getPlayers();
    void addPlayer(Player player);
    void removePlayer(Player player);
    int getID();

    Panel getLeft();
    Panel getRight();
    Panel getUp();
    Panel getDown();
}
