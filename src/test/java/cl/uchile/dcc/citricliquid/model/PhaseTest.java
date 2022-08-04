package cl.uchile.dcc.citricliquid.model;

import cl.uchile.dcc.citricliquid.model.unit.BossUnit;
import cl.uchile.dcc.citricliquid.model.unit.Player;
import cl.uchile.dcc.citricliquid.model.unit.WildUnit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cl.uchile.dcc.citricliquid.phases.*;
import cl.uchile.dcc.citricliquid.controller.GameController;

public class PhaseTest {
    protected GameController controller;
    private Phase phase;
    private StartPhase startPhase;
    private Battle battlePhase;
    private MovingPhase movingPhase;
    private RecoveryPhase recoveryPhase;
    private WaitFight waitFightPhase;
    private WaitHome waitHomePhase;
    private WaitPath waitPathPhase;
    private final static String PLAYER_NAME = "Suguri";
    private Player suguri;
    private final static String WILD_NAME_1 = "Chicken";
    private WildUnit chicken;

    @BeforeEach
    public void setUp() {
        phase = new Phase();
        controller = new GameController();
        phase.setController(controller);
        suguri = new Player(PLAYER_NAME, 4, 1, -1, 2);
        chicken = new WildUnit(WILD_NAME_1, 3, -1, -1, 1);
        startPhase = new StartPhase();
        battlePhase = new Battle(chicken, suguri);
        movingPhase = new MovingPhase();
        recoveryPhase = new RecoveryPhase();
        waitFightPhase = new WaitFight();
        waitPathPhase = new WaitPath();
        waitHomePhase = new WaitHome();
    }

    @Test
    public void changePhaseTest() {
        phase.changePhase(startPhase);
        Assertions.assertEquals(startPhase, controller.getPhase());
        phase.changePhase(battlePhase);
        Assertions.assertEquals(battlePhase, controller.getPhase());
        phase.changePhase(movingPhase);
        Assertions.assertEquals(movingPhase, controller.getPhase());
        phase.changePhase(recoveryPhase);
        Assertions.assertEquals(recoveryPhase, controller.getPhase());
        phase.changePhase(waitFightPhase);
        Assertions.assertEquals(waitFightPhase, controller.getPhase());
        phase.changePhase(waitPathPhase);
        Assertions.assertEquals(waitPathPhase, controller.getPhase());
        phase.changePhase(waitHomePhase);
        Assertions.assertEquals(waitHomePhase, controller.getPhase());
    }

}
