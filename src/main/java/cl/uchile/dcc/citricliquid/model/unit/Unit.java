package cl.uchile.dcc.citricliquid.model.unit;

public interface Unit {
    Unit copy();
    String getName();

    int getMaxHp();
    int getCurrentHp();
    int getAtk();
    int getDef();
    int getEvd();

    int attack();
    void evade(int attack);
    void defend(int attack);
    void SetSeed(long l);
    boolean isKO();

    void increaseWinsByPlayer(Player player);
    void increaseStarsByPlayer(Player player);
    void increaseWinsByBoss(BossUnit bossUnit);
    void increaseStarsByBoss(BossUnit bossUnit);
    void increaseWinsByWild(WildUnit wildUnit);
    void increaseStarsByWild(WildUnit wildUnit);

    void increaseStarsBy(Unit rival);
    void increaseWinsBy(Unit rival);


}
