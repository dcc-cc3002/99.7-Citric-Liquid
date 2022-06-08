package cl.uchile.dcc.citricliquid.model.board;

import cl.uchile.dcc.citricliquid.model.Player;
import cl.uchile.dcc.citricliquid.model.Wild;
import cl.uchile.dcc.citricliquid.model.battle.PlayerVsWild;
import cl.uchile.dcc.citricliquid.model.generator.WildGenerator;

public class EncounterPanel extends Panel implements BattlePanel {
  private Wild enemy = null;

  public EncounterPanel() {
    super(PanelType.ENCOUNTER);
  }

  public void setEnemy() {
    enemy = WildGenerator.randomGenerator();
  }

  public void initBattle(Player player) {
    if (enemy == null) {
      setEnemy();
    }
    System.out.println(player.getName() + " pelea con " + enemy.getName());
    PlayerVsWild.battling(player, enemy);
  }

  @Override
  public void activatedBy(Player player) {
    initBattle(player);
  }
}
