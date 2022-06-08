package cl.uchile.dcc.citricliquid.model.board;

import cl.uchile.dcc.citricliquid.model.*;
import cl.uchile.dcc.citricliquid.model.battle.PlayerVsBoss;
import cl.uchile.dcc.citricliquid.model.battle.PlayerVsWild;
import cl.uchile.dcc.citricliquid.model.generator.BossGenerator;
import cl.uchile.dcc.citricliquid.model.generator.WildGenerator;

public class BossPanel extends Panel implements BattlePanel {
  private boolean bossCondition = false;
  private Entity enemy = null;
  private boolean isBoss = false;

  public BossPanel() {
    super(PanelType.BOSS);
  }

  public void updateBossCondition(PlayersInGame playersInGame) {
    bossCondition = playersInGame.bossCondition();
  }

  public void setEnemy() {
    if (bossCondition) {
      enemy = BossGenerator.randomGenerator();
      isBoss = true;
    } else {
      enemy = WildGenerator.randomGenerator();
      isBoss = false;
    }
  }

  @Override
  public void initBattle(Player player) {
    if (enemy == null) {
      setEnemy();
    }
    if (isBoss) {
      PlayerVsBoss.battling(player, (Boss) enemy);
    } else {
      PlayerVsWild.battling(player, (Wild) enemy);
    }
  }

  @Override
  public void activatedBy(Player player) {
    initBattle(player);
  }
}
