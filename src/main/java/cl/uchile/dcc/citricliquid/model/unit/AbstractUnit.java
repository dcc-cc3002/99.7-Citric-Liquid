package cl.uchile.dcc.citricliquid.model.unit;

import java.util.Random;

public abstract class AbstractUnit implements Unit {
    private final Random random;
    private final String name;
    private final int maxHp;
    protected int atk;
    protected int def;
    protected int evd;
    private int stars;
    private int currentHp;
    private int wins;
    private boolean battleDecide;

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

    public AbstractUnit(String name,int hp,int atk,int def, int evd) {
        this.name = name;
        this.maxHp = currentHp = hp;
        this.atk = atk;
        this.def = def;
        this.evd = evd;
        random = new Random();
        stars = 0;
    }

    /**
     * Returns this unit's star count.
     */
    public int getStars() {
        return stars;
    }

    /**
     * Returns the character's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the character's max hit points.
     */
    public int getMaxHp() {
        return maxHp;
    }

    /**
     * Returns the current character's attack points.
     */
    public int getAtk() {
        return atk;
    }

    /**
     * Returns the current character's defense points.
     */
    public int getDef() {
        return def;
    }

    /**
     * Returns the current character's evasion points.
     */
    public int getEvd() {
        return evd;
    }

    /**
     * Returns the current hit points of the character.
     */
    public int getCurrentHp() {
        return currentHp;
    }

    /**
     * Set's the seed for this unit's random number generator.
     *
     * <p>The random number generator is used for taking non-deterministic decisions, this method is
     * declared to avoid non-deterministic behaviour while testing the code.
     */
    public void setSeed(final long seed) {
        random.setSeed(seed);
    }

    /**
     * Returns a uniformly distributed random value in [1, 6].
     */
    public int roll() {
        return random.nextInt(6) + 1;
    }

    /**
     * Sets the current character's hit points.
     *
     * <p>The character's hit points have a constraint to always be between 0 and maxHP, both
     * inclusive.
     */
    public void setCurrentHp(final int newHp) {
        this.currentHp = Math.max(Math.min(newHp, maxHp), 0);
    }

    /**
     * Increases this unit's star count by an amount.
     */
    public void increaseStarsBy(final int amount) {
        stars += amount;
    }

    /**
     * Reduces this unit's star count by a given amount.
     *
     * <p>The star count will must always be greater or equal to 0
     */
    public void reduceStarsBy(final int amount) {
        stars = Math.max(0, stars - amount);
    }

    /**
     * Returns this unit's current amount of wins.
     */
    public int getWins() { return wins;}

    /**
     * Sets this unit's current number of wins.
     * @param number is the new number of wins.
     */
    public void setWins(int number) {this.wins = number;}

    /**
     * Increases this unit's number of wins by an amount.
     */
    public abstract void increaseWinsBy(Unit unit);

    /**
     * Sums up the attack's damage points.
     * @return the number of damage points the attack makes.
     */
    public int attack() {
        return(this.roll() + this.getAtk());
    }

    /**
     * Performs the action of defending.
     */
    public void defend(int attack) {
        int def = (this.getDef() + this.roll());
        int damage = Math.max(1, attack - def);
        this.setCurrentHp(this.getCurrentHp() - damage);
    }

    /**
     * Sets this unit's decision in battle
     * @param action represents defend or evade
     *               defend: true
     *               evade: false
     */
    public void setBattleDecide(boolean action) {
        battleDecide = action;
    }

    /**
     * Returns this unit's decision in battle
     * @return is the decision, being true in the case of defend,
     * and false if the choice is to evade.
     */

    public boolean getBattleDecide() {
        return battleDecide;
    }
    /**
     * Performs the action of evading.
     */
    @Override
    public void evade(int attack) {
        int evd = (this.getEvd() + this.roll());
        int hp = this.getCurrentHp();
        if(evd <= attack) {
            int current = Math.max((hp - attack), 0);
            this.setCurrentHp(current);
        }
    }

    @Override
    public boolean isKO() {
        if(getCurrentHp() == 0) {
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AbstractUnit)) {
            return false;
        }
        final AbstractUnit unit = (AbstractUnit) o;
        return getMaxHp() == unit.getMaxHp()
                && getAtk() == unit.getAtk()
                && getDef() == unit.getDef()
                && getEvd() == unit.getEvd()
                && getStars() == unit.getStars()
                && getCurrentHp() == unit.getCurrentHp()
                && getName().equals(unit.getName());
    }

    /**
     * Returns a copy of this character.
     */
    public abstract Unit copy();

    }

