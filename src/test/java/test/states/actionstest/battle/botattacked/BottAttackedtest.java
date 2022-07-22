package test.states.actionstest.battle.botattacked;

import cl.uchile.dcc.citricliquid.model.exeptions.InvalidStateOperationException;
import cl.uchile.dcc.citricliquid.model.personaje.enemies.boss.BossType;
import cl.uchile.dcc.citricliquid.model.personaje.enemies.boss.BossUnit;
import cl.uchile.dcc.citricliquid.model.personaje.enemies.wild.WildType;
import cl.uchile.dcc.citricliquid.model.personaje.enemies.wild.WildUnit;
import cl.uchile.dcc.citricliquid.model.personaje.player.Player;
import cl.uchile.dcc.citricliquid.model.state.states.battle.defender.attacked.AttackedDefender;
import cl.uchile.dcc.citricliquid.model.state.states.battle.defender.attacked.AttackedEsquivar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

/**
 * class for test  a wild or boss unit when it is attacked.
 *
 */
public class BottAttackedtest {

  private Player suguri;
  private BossUnit bossUnit;
  private WildUnit wildUnit;

  /**
   * create elements for all the test.
   *
   */
  @BeforeEach
  public void setUp() {
    suguri = new Player("Juan", 4, 1, -1, 2);
    bossUnit = new BossUnit(BossType.SHIFU_ROBOT);
    wildUnit = new WildUnit(WildType.CHICKEN);
  }

  /**
   * test when a boss is attacked and decide to defender.
   * It have aleatory optiones.
   *
   */
  //Test al recibir un ataque un boss
  @RepeatedTest(100)
  public void attackedDefenderTest() throws InvalidStateOperationException {
    bossUnit.setState(new AttackedDefender(bossUnit));
    int hp = bossUnit.getMaxHp();
    int des = bossUnit.attacked(4);
    Assertions.assertTrue(bossUnit.getCurrentHp() < hp);
    Assertions.assertEquals(Math.max(0, hp - des), bossUnit.getCurrentHp());
  }

  /**
   * test when a boss is attacked and decide to esquive.
   * It have aleatory optiones.
   *
   */
  @RepeatedTest(100)
  public void attackedEsquiveTest() throws InvalidStateOperationException {
    bossUnit.setState(new AttackedEsquivar(bossUnit));
    int hp = bossUnit.getMaxHp();
    int des = bossUnit.attacked(4);
    if (des == 0) {
      Assertions.assertEquals(hp, bossUnit.getCurrentHp());
    } else {
      Assertions.assertEquals(Math.max(0, hp - des), bossUnit.getCurrentHp());
    }
  }

  /**
   * test when a wild is attacked and decide to defender.
   * It have aleatory optiones.
   *
   */
  //Test al recibir un ataque un wild
  @RepeatedTest(100)
  public void attackedDefenderWildTest() throws InvalidStateOperationException {
    wildUnit.setState(new AttackedDefender(wildUnit));
    int hp = wildUnit.getMaxHp();
    int des = wildUnit.attacked(4);
    Assertions.assertTrue(wildUnit.getCurrentHp() < hp);
    Assertions.assertEquals(Math.max(0, hp - des), wildUnit.getCurrentHp());
  }

  /**
   * test when a wild is attacked and decide to esquive.
   * It have aleatory optiones.
   *
   */
  @RepeatedTest(100)
  public void attackedEsquiveWildTest() throws InvalidStateOperationException {
    wildUnit.setState(new AttackedEsquivar(wildUnit));
    int hp = wildUnit.getMaxHp();
    int des = wildUnit.attacked(4);
    if (des == 0) {
      Assertions.assertEquals(hp, wildUnit.getCurrentHp());
    } else {
      Assertions.assertEquals(Math.max(0, hp - des), wildUnit.getCurrentHp());
    }
  }


  //test para contraatacar
  @Test
  public void exeptiontest() {
    boolean e = false;
    try {
      bossUnit.battle(wildUnit);
    } catch (InvalidStateOperationException exception) {
      e = true;
    }
    Assertions.assertTrue(e);
  }

  @Test
  public void counterattackdBattleTestWild() {
    boolean e = false;
    try {
      wildUnit.battle(bossUnit);
    } catch (InvalidStateOperationException exception) {
      e = true;
    }
    Assertions.assertTrue(e);
  }
}
