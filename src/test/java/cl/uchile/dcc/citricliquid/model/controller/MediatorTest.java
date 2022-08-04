package cl.uchile.dcc.citricliquid.model.controller;

import cl.uchile.dcc.citricliquid.controller.Mediator;
import cl.uchile.dcc.citricliquid.model.NormaGoal;
import cl.uchile.dcc.citricliquid.model.board.AbstractPanel;
import cl.uchile.dcc.citricliquid.model.board.Panel;
import cl.uchile.dcc.citricliquid.model.unit.Player;
import javafx.scene.layout.Pane;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test suite for the mediator.
 *
 * @author <a href="mailto:ignacio.slater@ug.uchile.cl">Ignacio Slater M.</a>.
 * @version 2.0-rc.1
 * @since 2.0
 */
class MediatorTest {
    private final Random random = new Random();
    private Mediator mediator;
    private List<Function<Integer, Mediator.MediatorPanel<?>>> panelSuppliers;
    private List<Mediator.MediatorPlayer<Player>> testPlayers;
    private List<Mediator.MediatorWildUnit<?>> testWildUnits;
    private List<Mediator.MediatorBoss<?>> testBosses;

    @BeforeEach
    public void setUp() {
        mediator = new Mediator();
        panelSuppliers =
                List.of(mediator::createBonusPanel, mediator::createBossPanel, mediator::createDropPanel,
                        mediator::createEncounterPanel, mediator::createHomePanel,
                        mediator::createNeutralPanel);
        createTestPlayers();
        createTestWildUnits();
        createBossUnits();
    }

    @Test
    public void testAddPanel() {
        assertTrue(mediator.getPanels().isEmpty(),
                "The game shouldn't have panels if none has been added.");
        int expectedSize = 0;
        for (Function<Integer, Mediator.MediatorPanel<?>> supplier : panelSuppliers) {
            var expectedPanel = supplier.apply(random.nextInt());
            assertEquals(++expectedSize, mediator.getPanels().size(),
                    "The expected amount of panels doesn't match the actual one");
            assertTrue(mediator.getPanels().contains(expectedPanel),
                    "The added panel is not correctly stored in the controller");
        }
    }

    @Test
    public void testAddPlayer() {
        panelSuppliers.stream().map(supplier -> supplier.apply(random.nextInt())).forEach(testPanel -> {
            for (var player : testPlayers) {
                var resultingPair =
                        mediator.createPlayer(testPanel, player.getName(), player.getMaxHP(), player.getAtk(),
                                player.getDef(), player.getEvd());
                var expectedPair = new Mediator.Pair<>(player, testPanel);
                assertEquals(expectedPair, resultingPair);
            }
        });
    }

    @Test
    public void testCreateWildUnit() {
        for (var wildUnit : testWildUnits) {
            var actualWildUnit =
                    mediator.createWildUnit(wildUnit.getName(), wildUnit.getMaxHP(), wildUnit.getAtk(),
                            wildUnit.getDef(), wildUnit.getEvd());
            assertEquals(wildUnit, actualWildUnit);
        }
    }

    @Test
    public void testCreateBossUnit() {
        for (var boss : testBosses) {
            var actualBoss =
                    mediator.createBossUnit(boss.getName(), boss.getMaxHP(), boss.getAtk(), boss.getDef(),
                            boss.getEvd());
            assertEquals(boss, actualBoss);
        }
    }

    @Test
    public void testSetNextPanel() {
        int id = 0;
        for (var originSupplier : panelSuppliers) {
            var resultingPanel = originSupplier.apply(id);
            assertTrue(resultingPanel.getNextPanels().isEmpty(),
                    "New panel shouldn't contain any next panels");
            mediator.setNextPanel(resultingPanel, originSupplier.apply(id++));
            assertTrue(resultingPanel.getNextPanels().isEmpty(),
                    "A panel shouldn't be able to add itself as next");
            int expectedPanelsN = 0;
            for (var supplier : panelSuppliers) {
                var newPanel = supplier.apply(id++);
                mediator.setNextPanel(resultingPanel, newPanel);
                assertEquals(++expectedPanelsN, resultingPanel.getNextPanels().size(),
                        "Actual amount of next panels after adding a new one doesn't match with the expected amount");
                assertTrue(resultingPanel.getNextPanels().contains(newPanel));
            }
        }
    }

    @Test
    public void testNormaGoal() {
        var panel = panelSuppliers.get(random.nextInt(panelSuppliers.size())).apply(1);
        var player = mediator.createPlayer(panel, testPlayers.get(random.nextInt(testPlayers.size())))
                .getFirst();
        assertEquals(NormaGoal.STARS, player.getNormaGoal(),
                "Players should begin with a star norma goal");
        mediator.setNormaGoal(NormaGoal.WINS);
        assertEquals(NormaGoal.WINS, player.getNormaGoal());
        mediator.setNormaGoal(NormaGoal.STARS);
        assertEquals(NormaGoal.STARS, player.getNormaGoal());
    }

    @Test
    public void testStarsNorma() {
        var bonusPanel = panelSuppliers.get(0).apply(1);
        var homePanel = mediator.createHomePanel(2);
        mediator.setNextPanel(homePanel, bonusPanel);
        mediator.setNextPanel(bonusPanel, homePanel);
        var player =
                mediator.createPlayer(homePanel, testPlayers.get(random.nextInt(testPlayers.size())))
                        .getFirst();
        int expectedLevel = 1;
        assertEquals(expectedLevel++, player.getNormaLevel(), "Player should start with level 1.");
        for (int starGoal : List.of(10, 30, 70, 120, 200)) {
            while (player.getStars() < starGoal && !homePanel.getPlayers().equals(List.of(player))) {
                mediator.movePlayer();
            }
            assertEquals(expectedLevel, player.getNormaLevel(),
                    "Player's norma level should be " + expectedLevel);
            expectedLevel++;
        }
    }

    @Test
    public void testMeetPlayer() {
        var panels = new Mediator.MediatorPanel<Panel>[]{
                panelSuppliers.get(random.nextInt(panelSuppliers.size())).apply(1),
                panelSuppliers.get(random.nextInt(panelSuppliers.size())).apply(2)};
        mediator.setNextPanel(panels[0], panels[1]);
        var players = new Mediator.MediatorPlayer<Player>[]{
                mediator.createPlayer(panels[0], testPlayers.get(0)).getFirst(),
                mediator.createPlayer(panels[1], testPlayers.get(1)).getFirst()};
        assertEquals(1, panels[0].getPlayers().size());
        assertTrue(panels[0].getPlayers().contains(players[0]));
        assertEquals(1, panels[1].getPlayers().size());
        assertTrue(panels[1].getPlayers().contains(players[1]));
        mediator.movePlayer();
        assertEquals(0, panels[0].getPlayers().size());
        assertEquals(2, panels[1].getPlayers().size());
        assertTrue(panels[1].getPlayers().contains(players[0]));
        assertTrue(panels[1].getPlayers().contains(players[1]));
    }

    @Test
    public void testPlayerHome() {
        var homePanel = mediator.createHomePanel(0);
        var panel1 = panelSuppliers.get(random.nextInt(panelSuppliers.size())).apply(1);
        var panel2 = panelSuppliers.get(random.nextInt(panelSuppliers.size())).apply(2);
        mediator.setNextPanel(panel1, homePanel);
        mediator.setNextPanel(homePanel, panel2);
        var player = mediator.createPlayer(homePanel, testPlayers.get(0)).getFirst();
        mediator.setPlayerHome(player, homePanel);
        mediator.movePlayer();
        assertTrue(homePanel.getPlayers().contains(player), "Player didn't stop at it's home panel");
    }

    @Test
    public void testMultipleNextPanels() {
        var panel1 = panelSuppliers.get(random.nextInt(panelSuppliers.size())).apply(1);
        var panel2 = panelSuppliers.get(random.nextInt(panelSuppliers.size())).apply(2);
        var panel3 = panelSuppliers.get(random.nextInt(panelSuppliers.size())).apply(3);
        var panel4 = panelSuppliers.get(random.nextInt(panelSuppliers.size())).apply(4);
        mediator.setNextPanel(panel1, panel2);
        mediator.setNextPanel(panel2, panel3);
        mediator.setNextPanel(panel2, panel4);
        var player = mediator.createPlayer(panel1, testPlayers.get(0)).getFirst();
        mediator.movePlayer();
        assertTrue(panel2.getPlayers().contains(player), "Player didn't stop at split");
    }

    @Test
    public void testTurns() {
        var panel = panelSuppliers.get(random.nextInt(panelSuppliers.size())).apply(1);
        testPlayers.forEach(player -> mediator.createPlayer(panel, player));
        int currentChapter = 1;
        for (int i = 1; i < 40; i++) {
            assertEquals(currentChapter, mediator.getChapter(),
                    "The current chapter is not the expected one.");
            assertEquals(testPlayers.get((i - 1) % 4), mediator.getTurnOwner(),
                    "This turn should belong to " + testPlayers.get((i - 1) % 4));
            mediator.endTurn();
            if (i % 4 == 0) {
                currentChapter++;
            }
        }
    }

    private void createTestPlayers() {
        testPlayers = new ArrayList<>();
        List.of("Suguri", "QP", "Yuki", "Kai").forEach(name -> testPlayers.add(
                new Mediator.MediatorPlayer<>(name, random.nextInt(10), random.nextInt(10) - 10,
                        random.nextInt(10) - 10, random.nextInt(10) - 10)));
    }

    private void createTestWildUnits() {
        testWildUnits = new ArrayList<>();
        List.of("Chicken", "Robo ball", "Seagull").forEach(name -> testWildUnits.add(
                new Mediator.MediatorWildUnit<>(name, random.nextInt(10), random.nextInt(10) - 10,
                        random.nextInt(10) - 10, random.nextInt(10) - 10)));
    }

    private void createBossUnits() {
        testBosses = new ArrayList<>();
        List.of("Store Manager", "Shifu Robot", "Flying Castle").forEach(name -> testBosses.add(
                new Mediator.MediatorBoss<>(name, random.nextInt(10), random.nextInt(10) - 10,
                        random.nextInt(10) - 10, random.nextInt(10) - 10)));
    }
}
