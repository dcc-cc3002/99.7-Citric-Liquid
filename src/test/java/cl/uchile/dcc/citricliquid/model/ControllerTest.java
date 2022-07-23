package cl.uchile.dcc.citricliquid.model;

import cl.uchile.dcc.citricliquid.States.AttackingPlayer1State;
import cl.uchile.dcc.citricliquid.States.IdleState;
import cl.uchile.dcc.citricliquid.model.Entities.Player;
import cl.uchile.dcc.citricliquid.model.Handlers.Controller;
import cl.uchile.dcc.citricliquid.model.Panel.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class ControllerTest {
  private Player Player1;
  private Player Player2;
  private Player Player3;
  private Player Player4;

  private static final int BASE_HP = 4;
  private static final int BASE_ATK = 1;
  private static final int BASE_DEF = - 1;
  private static final int BASE_EVD = 2;

  private Panel testHomePanel;
  private Panel testNeutralPanel;
  private Panel testBonusPanel;
  private Panel testDropPanel;
  private Panel testEncounterPanel;
  private Panel testBossPanel;

  private Panel testBonusPanel1;

  private Panel testNeutralPanel1;

  private Panel testDrawPanel;

  Controller controller;



  @BeforeEach
  public void SetUp(){
    controller = new Controller();

    controller.addPlayer("PLAYER_1",BASE_HP,BASE_ATK,BASE_DEF,BASE_EVD,1);
    controller.addPlayer("PLAYER_2",BASE_HP,BASE_ATK,BASE_DEF,BASE_EVD,2);
    controller.addPlayer("PLAYER_3",BASE_HP,BASE_ATK,BASE_DEF,BASE_EVD,3);
    controller.addPlayer("PLAYER_4",BASE_HP,BASE_ATK,BASE_DEF,BASE_EVD,4);

    testBonusPanel = new BonusPanel(0, 0, new int[]{0, 1, 0, 0});
    testBonusPanel1 = new BonusPanel(1, 0, new int[]{0, 1, 0, 0});
    testBossPanel = new BossPanel(2, 0, new int[]{0, 1, 1, 0}, false);
    testDropPanel = new DropPanel(2, 1, new int[]{0, 1, 0, 0});
    testEncounterPanel = new EncounterPanel(3, 0, new int[]{0, 1, 0, 0});
    testHomePanel = new HomePanel(3, 1, new int[]{0, 0, 0, 1});
    testNeutralPanel = new NeutralPanel(4, 0, new int[]{0, 0, 1, 0});
    testNeutralPanel1 = new NeutralPanel(4, 1, new int[]{1, 0, 0, 0});
    testDrawPanel = new DrawPanel(5, 0, new int[]{0, 0, 0, 0});

    Panel[] panels = new Panel[]{testBonusPanel, testBonusPanel1, testBossPanel,
            testDropPanel, testEncounterPanel,
            testNeutralPanel, testHomePanel, testNeutralPanel1, testDrawPanel};


 }
 @Test
 public void testActivateBossPanel(){
   Panel[] panels = new Panel[]{testBonusPanel, testBonusPanel1, testBossPanel,
           testDropPanel, testEncounterPanel,
           testNeutralPanel, testHomePanel, testNeutralPanel1, testDrawPanel};
    controller.setPanels(panels);
    controller.getPlayer1().setNormaLevel(4);
    Assertions.assertTrue(((BossPanel)panels[2]).isActive());
 }
 @Test
 public void testObserver(){
    Player Player1 = controller.getPlayer1();
    Player1.setNormaLevel(6);
    Assertions.assertEquals(Player1,controller.getWinner());
 }
 @RepeatedTest(100)
 public void battleTest1(){
    Player1 = controller.getPlayer1();
    Player2 = controller.getPlayer2();
    Player1.setState(new IdleState());
    Player2.setState(new IdleState());
    Assertions.assertTrue(Player1.isIdle());
    Assertions.assertTrue(Player2.isIdle());

    /////////////PLAYER 1 ATTACKS PLAYER 2 //////////
    Player1.attack(Player2);
    Assertions.assertTrue(Player1.isAttacking());
    Assertions.assertTrue(Player2.isAttacked());


    /////////////PLAYER 2 DEFENDS FROM PLAYER 1///////////
   Player2.defendDecision(Player1,"defend");

   /////////////PLAYER 2 COUNTERATTACKS AND PLAYER 1 DEFENDS///////////////////
   if (Player2.isKo()==false){
     Player2.attack(Player1);
     Assertions.assertTrue(Player2.isAttacking());
     Assertions.assertTrue(Player1.isAttacked());
     Player1.defendDecision(Player2,"defend");
   }

 }

  @RepeatedTest(100)
  public void battleTest2(){
    Player1 = controller.getPlayer1();
    Player2 = controller.getPlayer2();
    Player1.setState(new IdleState());
    Player2.setState(new IdleState());
    Assertions.assertTrue(Player1.isIdle());
    Assertions.assertTrue(Player2.isIdle());

    /////////////PLAYER 1 ATTACKS PLAYER 2 //////////
    Player1.attack(Player2);
    Assertions.assertTrue(Player1.isAttacking());
    Assertions.assertTrue(Player2.isAttacked());


    /////////////PLAYER 2 TRIES TO EVADE PLAYER 1///////////
    Player2.defendDecision(Player1,"evade");

    /////////////PLAYER 2 COUNTERATTACKS AND PLAYER 1 DEFENDS///////////////////
    if (Player2.isKo()==false){
      Player2.attack(Player1);
      Assertions.assertTrue(Player2.isAttacking());
      Assertions.assertTrue(Player1.isAttacked());
      Player1.defendDecision(Player2,"defend");
    }

  }
  @RepeatedTest(100)
  public void battleTest3(){
    Player1 = controller.getPlayer1();
    Player2 = controller.getPlayer2();
    Player1.setState(new IdleState());
    Player2.setState(new IdleState());
    Assertions.assertTrue(Player1.isIdle());
    Assertions.assertTrue(Player2.isIdle());

    /////////////PLAYER 1 ATTACKS PLAYER 2 //////////
    Player1.attack(Player2);
    Assertions.assertTrue(Player1.isAttacking());
    Assertions.assertTrue(Player2.isAttacked());


    /////////////PLAYER 2 TRIES TO EVADE PLAYER 1///////////
    Player2.defendDecision(Player1,"evade");

    /////////////PLAYER 2 COUNTERATTACKS AND PLAYER 1 EVADES///////////////////
    if (Player2.isKo()==false){
      Player2.attack(Player1);
      Assertions.assertTrue(Player2.isAttacking());
      Assertions.assertTrue(Player1.isAttacked());
      Player1.defendDecision(Player2,"evade");
    }

  }
  @RepeatedTest(100)
  public void battleTest4(){
    Player1 = controller.getPlayer1();
    Player2 = controller.getPlayer2();
    Player1.setState(new IdleState());
    Player2.setState(new IdleState());
    Assertions.assertTrue(Player1.isIdle());
    Assertions.assertTrue(Player2.isIdle());

    /////////////PLAYER 1 ATTACKS PLAYER 2 //////////
    Player1.attack(Player2);
    Assertions.assertTrue(Player1.isAttacking());
    Assertions.assertTrue(Player2.isAttacked());


    /////////////PLAYER 2 TRIES TO EVADE PLAYER 1///////////
    Player2.defendDecision(Player1,"evade");

    /////////////PLAYER 2 COUNTERATTACKS AND PLAYER 1 DEFENDS///////////////////
    if (Player2.isKo()==false){
      Player2.attack(Player1);
      Assertions.assertTrue(Player2.isAttacking());
      Assertions.assertTrue(Player1.isAttacked());
      Player1.defendDecision(Player2,"defend");
    }
  }

  @Test
  public void move1spaceTest(){
    //The movements has already been tested in tarea1.
    Panel[] panels = new Panel[]{testBonusPanel, testBonusPanel1, testBossPanel,
            testDropPanel, testEncounterPanel,
            testNeutralPanel, testHomePanel, testNeutralPanel1, testDrawPanel};
    controller.setPanels(panels);
    Player player =controller.getPlayer1();
    player.setLocation(testBonusPanel);
    controller.move1Space(player,"right");
    Assertions.assertEquals(player.getPlayerPanel(),testBonusPanel1);

    controller.move1Space(player,"right");
    Assertions.assertEquals(player.getPlayerPanel(),testBossPanel);
  }
  @Test
  public void checkGetters(){
    Player player = controller.getPlayer1();
    player.setNormaLevel(1);
    Assertions.assertEquals(player.getAtk(),controller.getCharacterATK(player));
    Assertions.assertEquals(player.getDef(),controller.getCharacterDEF(player));
    Assertions.assertEquals(player.getCurrentHp(),controller.getCharacterHp(player));
    Assertions.assertEquals(player.getEvd(),controller.getCharacterEVD(player));
    Assertions.assertEquals(player.getNormaLevel(),controller.getPlayerNorma(player));
    Assertions.assertEquals(player.getStars(),controller.getCharacterStars(player));
    Assertions.assertEquals(player.getName(),controller.getCharacterName(player));

  }
  @Test
  public void testActivatedBy(){

  }
}
