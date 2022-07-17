package cl.uchile.dcc.citricliquid.controller;

import cl.uchile.dcc.citricliquid.model.NormaGoal;
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
    private Phase phase;

    public GameController() {
        playerList = new ArrayList<>();
        panelList = new ArrayList<>();
        turn = 1;
        chapter = 1;
        phase = new Phase();
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

    public void setHomePanel(Player player, HomePanel homePanel) {player.setHomePanel(homePanel);}

    public void setCurrentPanel(Player player, Panel panel) {
        player.getPanel().getPlayers().remove(player);
        panel.addPlayer(player);
        player.setCurrentPanel(panel);
    }

    public Player getTurnOwner() {
        Player owner = playerList.get((turn - 1) % playerList.size());
        return owner;
    }

    public void setNormaGoal(NormaGoal goal) { getTurnOwner().setNormaGoal(goal);}


    public void addNextPanel(Panel current, Panel next) {
        current.addNextPanel(next);
    }

    public List<Panel> getPanelList(){ return panelList; }

    public int getChapter() { return chapter; }

    public void changePhase(Phase newPhase){
        this.phase = newPhase;
    }

    public void battlePvP(Player player_1,Player player_2) {

    }



}
