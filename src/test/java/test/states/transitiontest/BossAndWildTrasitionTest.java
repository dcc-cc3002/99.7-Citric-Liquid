package test.states.transitiontest;

import cl.uchile.dcc.citricliquid.model.exeptions.InvalidStateOperationException;
import cl.uchile.dcc.citricliquid.model.personaje.enemies.boss.BossType;
import cl.uchile.dcc.citricliquid.model.personaje.enemies.boss.BossUnit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

/**
 * Class to prove all the posible transitions for a wild and boss unit.
 *
 */
public class BossAndWildTrasitionTest {
  private BossUnit bossUnit;

  @BeforeEach
  public void setUp() {
    bossUnit = new BossUnit(BossType.SHIFU_ROBOT);
  }

  /**
   * Class to prove all the transition that is aleatory.
   *
   */
  @RepeatedTest(100)
  public void transitionTest() throws InvalidStateOperationException {
    Assertions.assertTrue(bossUnit.isInactive());
    bossUnit.toAttackedState();
    if (bossUnit.isAttackedDefender()) {
      bossUnit.toCounterAttackState();
      Assertions.assertTrue(bossUnit.isCounterAttack());

    } else if (bossUnit.isAttackedEsquivar()) {
      bossUnit.toCounterAttackState();
      Assertions.assertTrue(bossUnit.isCounterAttack());

    } else {
      System.out.println("ERROR");
    }



  }
}
