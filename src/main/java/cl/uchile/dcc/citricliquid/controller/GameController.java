package cl.uchile.dcc.citricliquid.controller;

import cl.uchile.dcc.citricliquid.model.NormaGoal;
import cl.uchile.dcc.citricliquid.model.unit.Unit;
import cl.uchile.dcc.citricliquid.phases.MovingPhase;
import cl.uchile.dcc.citricliquid.phases.Phase;
import cl.uchile.dcc.citricliquid.model.board.*;
import cl.uchile.dcc.citricliquid.model.unit.BossUnit;
import cl.uchile.dcc.citricliquid.model.unit.Player;
import cl.uchile.dcc.citricliquid.model.unit.WildUnit;

import java.util.ArrayList;
import java.util.List;

public class GameController {
    private final List<Player> playerList;
    private final List<Panel> panelList;
    private int turn;
    private int chapter;
    private boolean bosses;
    private boolean over;
    private Phase phase;
    private  Player winner;

    public GameController() {
        playerList = new ArrayList<>();
        panelList = new ArrayList<>();
        turn = 0;
        chapter = 1;
        phase = new Phase();
        winner = null;
        bosses = false;
        over = false;
    }

    /**
     * Creates a new bonus panel,
     * adding it to the game's panel list.
     *
     * @param id the identification code of the panel.
     */
    public BonusPanel createBonusPanel(int id) {
        BonusPanel newPanel = new BonusPanel(id);
        panelList.add(newPanel);
        return newPanel;
    }

    /**
     * Creates a new boss panel,
     * adding it to the game's panel list.
     *
     * @param id the identification code of the panel.
     */
    public BossPanel createBossPanel(int id) {
        BossPanel newPanel = new BossPanel(id);
        panelList.add(newPanel);
        return newPanel;
    }

    /**
     * Creates a new drop panel,
     * adding it to the game's panel list.
     *
     * @param id the identification code of the panel.
     */
    public DropPanel createDropPanel(int id) {
        DropPanel newPanel = new DropPanel(id);
        panelList.add(newPanel);
        return newPanel;
    }

    /**
     * Creates a new encounter panel,
     * adding it to the game's panel list.
     *
     * @param id the identification code of the panel.
     */
    public EncounterPanel createEncounterPanel(int id) {
        EncounterPanel newPanel = new EncounterPanel(id);
        panelList.add(newPanel);
        return newPanel;
    }

    /**
     * Creates a new home panel,
     * adding it to the game's panel list.
     *
     * @param id the identification code of the panel.
     */
    public HomePanel createHomePanel(int id) {
        HomePanel newPanel = new HomePanel(id);
        panelList.add(newPanel);
        return newPanel;
    }

    /**
     * Creates a new neutral panel,
     * adding it to the game's panel list.
     *
     * @param id the identification code of the panel.
     */
    public NeutralPanel createNeutralPanel(int id) {
        NeutralPanel newPanel = new NeutralPanel(id);
        panelList.add(newPanel);
        return newPanel;
    }

    /**
     * Creates a new wild unit.
     *
     * @param name
     *     the character's name.
     * @param hp
     *     the initial (and max) hit points of the character.
     * @param atk
     *     the base damage the character does.
     * @param def
     *     the base defense of the character.
     * @param evd
     *     the base evasion of the character.
     */
    public WildUnit createWildUnit(String name, int hp, int atk, int def, int evd) {
        return new WildUnit(name, hp, atk, def, evd);
    }

    /**
     * Creates a new boss unit.
     *
     * @param name
     *     the character's name.
     * @param hp
     *     the initial (and max) hit points of the character.
     * @param atk
     *     the base damage the character does.
     * @param def
     *     the base defense of the character.
     * @param evd
     *     the base evasion of the character.
     */
    public BossUnit createBossUnit(String name, int hp, int atk, int def, int evd) {
        return new BossUnit(name, hp, atk, def, evd);
    }

    /**
     * Creates a new player and adds them to the playerList.
     *
     * @param name
     *     the character's name.
     * @param hp
     *     the initial (and max) hit points of the character.
     * @param atk
     *     the base damage the character does.
     * @param def
     *     the base defense of the character.
     * @param evd
     *     the base evasion of the character.
     */
    public Player createPlayer(String name, int hp, int atk, int def, int evd) {
        Player player = new Player(name, hp, atk, def, evd);
        playerList.add(player);
        return player;
    }

    /**
     * Sets this player's home panel.
     *
     * @param player is the player.
     *
     * @param homePanel is the player's new home panel.
     */
    public void setHomePanel(Player player, HomePanel homePanel) {player.setHomePanel(homePanel);}

    /**
     * Sets the panel in which this player is on.
     *
     * @param player is the player.
     *
     * @param panel is the player's new place.
     */
    public void setCurrentPanel(Player player, Panel panel) {
        if (player.getPanel() != null) {
            player.getPanel().getPlayers().remove(player);
        }
        panel.addPlayer(player);
        player.setCurrentPanel(panel);
    }

    /**
     * Returns the current turn's owner.
     */
    public Player getTurnOwner() {
        Player owner = playerList.get(turn % playerList.size());
        return owner;
    }

    /**
     * Goes to next turn
     */
    public void endTurn() {
        this.turn = (turn + 1) % playerList.size();
        if (turn == 0) nextChapter();
    }

    /**
     * Changes the chapter
     */
    public void nextChapter() {
        this.chapter += 1;
    }

    /**
     * Returns current panel
     */
    public Panel getCurrentPanel(){
        return getTurnOwner().getPanel();
    }

    /**
     * Sets a panel as another's next panel
     */
    public void setNextPanel(Panel panel, Panel next) {
        panel.addNextPanel(next);
    }

    /**
     * Roll and move the player
     */
    public void move() {
        System.out.println("Moving");
        changePhase(new MovingPhase());
        Player player = getTurnOwner();
        int roll = player.roll();
        Panel panel = getCurrentPanel();
        while (roll != 0) {

            if (panel.equals(player.getHomePanel())) {
                System.out.println("Home");
                panel.activatedBy(player);
                break;
            }
            if (panel.getNextPanels().isEmpty()) {
                break;
            }

            if (panel.getNextPanels().size() > 1) {
                break;
            }

            if (panel.getPlayers().size() > 1) {
                break;
            }
            else {
                panel = getTurnOwner().getPanel().getNextPanels().iterator().next();
                setCurrentPanel(getTurnOwner(), panel);
                roll = roll - 1;
            }
        }
        panel.activatedBy(player);
    }

    /**
     * Sets this game's norma goal.
     *
     * @param goal is the game's goal to win.
     */
    public void setNormaGoal(NormaGoal goal) { getTurnOwner().setNormaGoal(goal);}

    /**
     * Annexes a panel to the specified one.
     * @param next is the annexed panel.
     */
    public void addNextPanel(Panel current, Panel next) {
        current.addNextPanel(next);
    }

    /**
     * Returns the game's panel list.
     */
    public List<Panel> getPanelList(){ return panelList; }

    /**
     * Returns the current chapter in which the game is in.
     */
    public int getChapter() { return chapter; }

    /**
     * Changes the current phase of the game.
     */
    public void changePhase(Phase newPhase){
        this.phase = newPhase;
    }

    /**
     * Returns the current phase in which the game is in.
     */
    public Phase getPhase() {
        return phase;
    }

    /**
     * The unit performs the action of defending.
     */
    public void defend(Unit unit, int atk) {
        unit.defend(atk);
    }

    /**
     * The unit performs the action of evading.
     */
    public void evade(Unit unit, int atk) {
        unit.evade(atk);
    }

    /**
     * Initiates a battle
     *
     * @param attacker is the unit that initiates the battle.
     *
     * @param rival is the unit that has to answer to the attack.
     */
    public void battle(Unit attacker, Unit rival) {
        int atk_1 = attacker.attack();
        if(rival.getBattleDecide()) {
            defend(rival, atk_1);
        }

        if (!rival.getBattleDecide()) {
            evade(rival, atk_1);
        }
    }

    /**
     * Sets the winner if norma 6 reached
     */
    public void onNormaLevel(int lvl) {
        if (lvl == 4 && !bosses){
            bosses = true;
        }
        if (lvl == 6){
            winner = getTurnOwner();
            over = true;
        }
    }

    /**
     * Returns game winner
     */
    public Player getWinner(){
        return winner;
    }
}