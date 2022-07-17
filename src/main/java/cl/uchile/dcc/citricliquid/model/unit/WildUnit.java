package cl.uchile.dcc.citricliquid.model.unit;

public class WildUnit extends AbstractUnit{
    /**
     * Creates a new character.
     *
     * @param name the character's name.
     * @param atk  the base damage the character does.
     * @param def  the base defense of the character.
     * @param evd
     */
    public WildUnit(String name, int maxHp, int atk, int def, int evd) {
        super(name, maxHp, atk, def, evd);
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
     * Increases the rival's number of wins by 1.
     * @param player being the rival
     */
    public void increaseWinsByPlayer(Player player) {
        player.setWins(player.getWins() + 1);
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
     * Increases the rival's number of wins by 1.
     * @param bossUnit being the rival
     */
    public void increaseWinsByBoss(BossUnit bossUnit) {
        bossUnit.setWins(bossUnit.getWins() + 1);
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
     * Increases the rival's number of wins by 1.
     * @param wildUnit being the rival
     */
    public void increaseWinsByWild(WildUnit wildUnit) {
        wildUnit.setWins((wildUnit.getWins()) + 1);
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
     * Increases this unit's stars by calling the specific
     * method through the loser.
     * @param rival being the loser.
     */
    public void increaseStarsBy(Unit rival) {
        rival.increaseStarsByWild(this);
    }

    /**
     * Increases this unit's number of wins by an amount,
     * by calling the specific method through the rival.
     */
    public void increaseWinsBy(Unit rival) {
        rival.increaseStarsByWild(this);
    }

    /**
     * Returns a copy of this character.
     */
    public Unit copy() {
        return new WildUnit(this.getName(), this.getMaxHp(), this.getAtk(), this.getDef(), this.getEvd());
    }
}
