package test.states.actionstest.battle.player.attack;

import cl.uchile.dcc.citricliquid.model.exeptions.InvalidStateOperationException;
import cl.uchile.dcc.citricliquid.model.personaje.enemies.AbstractEnemies;
import cl.uchile.dcc.citricliquid.model.personaje.enemies.boss.BossType;
import cl.uchile.dcc.citricliquid.model.personaje.enemies.boss.BossUnit;
import cl.uchile.dcc.citricliquid.model.personaje.enemies.wild.WildType;
import cl.uchile.dcc.citricliquid.model.personaje.enemies.wild.WildUnit;
import cl.uchile.dcc.citricliquid.model.personaje.player.Player;
import cl.uchile.dcc.citricliquid.model.state.states.battle.atacar.Attack;
import cl.uchile.dcc.citricliquid.model.state.states.battle.defender.attacked.AttackedDefender;
import cl.uchile.dcc.citricliquid.model.state.states.battle.defender.attacked.AttackedEsquivar;
import cl.uchile.dcc.citricliquid.model.state.states.turnstate.Inactive;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Class for test when a player is attacking.
 * it prove exeption when cant call the method, and prove complete
 * secuences of attack and stars and wins transferred.
 *
 */
public class PlayerAttackTest {
  private static final String PLAYER_NAME = "Suguri";
  private Player suguri;
  private BossUnit bossUnit;

  /**
   * create elements for all the test.
   *
   */
  @BeforeEach
  public void setUp() {
    suguri = new Player(PLAYER_NAME, 100, 4, -1, 2);
    bossUnit = new BossUnit(BossType.SHIFU_ROBOT);
  }

  //Se comprueba que se esté dando la exepción cuando se llama al método desde un
  //estado no permitido.
  @Test
  public void attackdBattleTest() throws InvalidStateOperationException {
    boolean e = false;
    suguri.setState(new Inactive(suguri));
    try {
      suguri.battle(bossUnit);
    } catch (InvalidStateOperationException ex) {
      e = true;
    }
    Assertions.assertTrue(e);
  }

  //SE TESTEAN BATALLAS COMPLETAS.
  //COMENZAMOS CON BATALLAS SIN CONTRAATAQUE. EL ENEMIGO DEBE MORIR EN EL PRIMER ATAQUE.
  //Jugador vs jugador, atacado esquiva.
  @Test
  public void battlepvpEsq() throws InvalidStateOperationException {
    Player p2 = new Player("test", 4, 1, 1, 1);
    int i = 0;
    while (i < 20) {
      Assertions.assertTrue(p2.isInactive());
      Assertions.assertTrue(suguri.isInactive());;

      p2.increaseStarsBy(i * 2);
      p2.setCurrentHp(p2.getMaxHp());
      suguri.setCurrentHp(suguri.getMaxHp());

      suguri.setState(new Attack(suguri));
      //se da manualmente el estado porque se espera un input.
      p2.setState(new AttackedEsquivar(p2));

      final int ws = p2.getStars();
      final int ps = suguri.getStars();
      final int pw = suguri.getWins();
      final int trans = (int) Math.floor(((float) p2.getStars()) / 2);

      suguri.battle(p2);

      Assertions.assertEquals(trans, suguri.getStars() - ps);
      Assertions.assertEquals(trans, ws - p2.getStars());
      Assertions.assertEquals(2, suguri.getWins() - pw);

      i++;
    }
  }


  //Jugador vs jugador, atacado defiende.
  @Test
  public void battlepvpDef() throws InvalidStateOperationException {
    Player p2 = new Player("test", 4, 1, 1, 1);
    int i = 0;
    while (i < 20) {
      Assertions.assertTrue(p2.isInactive());
      Assertions.assertTrue(suguri.isInactive());

      p2.increaseStarsBy(i * 2);
      p2.setCurrentHp(p2.getMaxHp());

      final int trans = (int) Math.floor(((float) p2.getStars()) / 2);
      final int ps = suguri.getStars();
      final int pw = suguri.getWins();
      final int ws = p2.getStars();

      suguri.setState(new Attack(suguri));
      //Se da explícitamente la transición, porque se espera un input.
      p2.setState(new AttackedDefender(p2));

      suguri.battle(p2);

      Assertions.assertEquals(trans, suguri.getStars() - ps);
      Assertions.assertEquals(trans, ws - p2.getStars());
      Assertions.assertEquals(2, suguri.getWins() - pw);

      i++;
    }
  }

  //Jugador vs wild unit.
  @Test
  public void battlepvw() throws InvalidStateOperationException {
    AbstractEnemies p2 = new WildUnit(WildType.SEAGULL);
    int i = 0;
    while (i < 20) {
      Assertions.assertTrue(p2.isInactive());
      Assertions.assertTrue(suguri.isInactive());

      p2.increaseStarsBy(i * 2);
      p2.setCurrentHp(p2.getMaxHp());
      suguri.setCurrentHp(suguri.getMaxHp());

      final int trans = (int) Math.floor(((float) p2.getStars()) / 2);
      final int ps = suguri.getStars();
      final int pw = suguri.getWins();
      final int ws = p2.getStars();

      suguri.setState(new Attack(suguri));

      suguri.battle(p2);

      Assertions.assertEquals(trans, suguri.getStars() - ps);
      Assertions.assertEquals(trans, ws - p2.getStars());
      Assertions.assertEquals(2, suguri.getWins() - pw);
      i++;
    }
  }

  //Jugador vs boss unit.
  @Test
  public void battlepvboss() throws InvalidStateOperationException {
    AbstractEnemies p2 = new BossUnit(BossType.SHIFU_ROBOT);
    int i = 0;
    while (i < 20) {
      Assertions.assertTrue(p2.isInactive());
      Assertions.assertTrue(suguri.isInactive());

      p2.increaseStarsBy(i * 2);
      p2.setCurrentHp(p2.getMaxHp());
      suguri.setCurrentHp(suguri.getMaxHp());

      final int trans = (int) Math.floor(((float) p2.getStars()) / 2);
      final int ps = suguri.getStars();
      final int pw = suguri.getWins();
      final int bs = p2.getStars();

      suguri.setState(new Attack(suguri));

      suguri.battle(p2);

      Assertions.assertEquals(trans, suguri.getStars() - ps);
      Assertions.assertEquals(3, suguri.getWins() - pw);
      Assertions.assertEquals(trans, bs - p2.getStars());
      i++;
    }
  }



  //TEST DE BATALLAS CON CONTRAATAQUES

  //Player vs boss. Boss gana.
  @Test
  public void counterPvBoss() throws InvalidStateOperationException {
    AbstractEnemies p2 = new BossUnit(BossType.SHIFU_ROBOT);
    Player player = new Player("prueb", 0, 1, 1, 1);
    int i = 0;
    while (i < 20) {

      Assertions.assertTrue(p2.isInactive());
      Assertions.assertTrue(player.isInactive());

      player.increaseStarsBy(i * 2);
      player.setCurrentHp(player.getMaxHp());
      p2.setCurrentHp(p2.getMaxHp());
      final int trans = (int) Math.floor(((float) player.getStars()) / 2);

      final int ps = player.getStars();
      final int bs = p2.getStars();

      player.setState(new Attack(player));

      player.battle(p2);
      if (p2.getCurrentHp() == 0) {
        i++;
        continue;
      }

      player.toCounterAttackedDefender();
      p2.battle(player);

      Assertions.assertEquals(trans, p2.getStars() - bs);
      Assertions.assertEquals(trans, ps - player.getStars());
      i++;
    }
  }



  //Player vs wild. wild win.
  @Test
  public void batlecountreattackWild() throws InvalidStateOperationException {
    AbstractEnemies p2 = new WildUnit(WildType.ROBO_BALL);
    Player player = new Player("prueb", 0, 1, 1, 1);
    int i = 0;
    while (i < 20) {

      Assertions.assertTrue(p2.isInactive());
      Assertions.assertTrue(player.isInactive());

      player.increaseStarsBy(i * 2);
      player.setCurrentHp(player.getMaxHp());
      p2.setCurrentHp(p2.getMaxHp());

      final int trans = (int) Math.floor(((float) player.getStars()) / 2);
      final int ps = player.getStars();
      final int bs = p2.getStars();

      player.setState(new Attack(player));

      player.battle(p2);
      if (p2.getCurrentHp() == 0) {
        i++;
        continue;
      }

      player.toCounterAttackedDefender();
      p2.battle(player);


      Assertions.assertEquals(trans, p2.getStars() - bs);
      Assertions.assertEquals(trans, ps - player.getStars());
      i++;
    }
  }

  //Player vs Player (p2 vs suguri). sugiri wins.
  @Test
  public void counterPvP() throws InvalidStateOperationException {
    Player player = new Player("prueb", 0, 1, 1, 1);
    int i = 0;
    while (i < 20) {

      Assertions.assertTrue(suguri.isInactive());
      Assertions.assertTrue(player.isInactive());

      player.increaseStarsBy(i * 2);
      player.setCurrentHp(player.getMaxHp());
      suguri.setCurrentHp(suguri.getMaxHp());

      final int trans = (int) Math.floor(((float) player.getStars()) / 2);
      final int ps = player.getStars();
      final int bs = suguri.getStars();
      final int bw = suguri.getWins();

      player.setState(new Attack(player));
      suguri.setState(new AttackedDefender(suguri));

      player.battle(suguri);

      if (suguri.getCurrentHp() == 0) {
        i++;
        continue;
      }
      player.toCounterAttackedEsquivar();
      suguri.battle(player);


      Assertions.assertEquals(trans, suguri.getStars() - bs);
      Assertions.assertEquals(2, suguri.getWins() - bw);
      Assertions.assertEquals(trans, ps - player.getStars());

      i++;
    }
  }



}
