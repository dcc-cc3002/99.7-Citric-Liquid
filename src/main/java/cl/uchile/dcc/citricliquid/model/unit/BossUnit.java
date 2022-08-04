package cl.uchile.dcc.citricliquid.model.unit;

public class BossUnit extends AbstractUnit{
    /**
     * Creates a new character.
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
    public BossUnit(String name, int hp, final int atk, final int def, final int evd) {
        super(name, hp, atk, def, evd);
    }

    /**
     * Set's the seed for this unit's random number generator.
     *
     * <p>The random number generator is used for taking non-deterministic decisions, this method is
     * declared to avoid non-deterministic behaviour while testing the code.
     */
    public void SetSeed(long l) {

    }

    /**
     * Increases the rival's number of wins by 3.
     * @param player being the rival
     */
    public void increaseWinsByPlayer(Player player) {
        player.setWins(player.getWins() + 3);
    }

    /**
     * Reduces this unit's amount of stars, and adds the right
     * amount to the winner's number of stars.
     * @param player being the winner.
     */
    public void increaseStarsByPlayer(Player player) {
        player.increaseStarsBy((int) Math.floor(this.getStars() * 0.5));
        this.reduceStarsBy((int) Math.ceil(this.getStars()*0.5));
    }

    /**
     * Increases the rival's number of wins by 3.
     * @param bossUnit being the rival
     */
    public void increaseWinsByBoss(BossUnit bossUnit) {
        bossUnit.setWins(bossUnit.getWins() + 3);
    }

    /**
     * Reduces this unit's amount of stars, and adds the right
     * amount to the winner's number of stars.
     * @param bossUnit being the winner.
     */
    public void increaseStarsByBoss(BossUnit bossUnit) {
        bossUnit.increaseStarsBy((int) Math.floor(this.getStars()*0.5));
        this.reduceStarsBy((int) Math.ceil(this.getStars()*0.5));
    }

    /**
     * Increases the rival's number of wins by 3.
     * @param wildUnit being the rival
     */
    public void increaseWinsByWild(WildUnit wildUnit) {
        wildUnit.setWins((wildUnit.getWins()) + 3);
    }

    /**
     * Reduces this unit's amount of stars, and adds the right
     * amount to the winner's number of stars.
     * @param wildUnit being the winner.
     */
    public void increaseStarsByWild(WildUnit wildUnit) {
        wildUnit.increaseStarsBy((int) Math.floor(this.getStars() * 0.5));
        this.reduceStarsBy((int) Math.ceil(this.getStars() * 0.5));
    }

    /**
     * Increases this unit's star count by an amount,
     * by calling the specific method through the rival.
     */
    public void increaseStarsBy(Unit rival) {
        rival.increaseStarsByBoss(this);
    }

    /**
     * Increases this unit's number of wins by an amount,
     * by calling the specific method through the rival.
     */
    public void increaseWinsBy(Unit rival) {
        rival.increaseStarsByBoss(this);
    }

    /**
     * Returns a copy of this character.
     */
    public BossUnit copy() {
        return new BossUnit(this.getName(), this.getMaxHp(), this.getAtk(), this.getDef(), this.getEvd());
    }
}
