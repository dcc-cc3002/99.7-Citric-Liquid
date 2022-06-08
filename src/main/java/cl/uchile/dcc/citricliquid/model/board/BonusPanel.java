public class BonusPanel extends Panel {

    public BonusPanel(PanelType type) {
        super(type);
        type = PanelType.BONUS;
    }

    private void applyBonusTo(final Player player) {
        player.increaseStarsBy(player.roll() * Math.min(player.getNormaLevel(), 3));
    }

    @Override
    public void activatedBy(Player player) {
        applyBonusTo(player);
    }
}
