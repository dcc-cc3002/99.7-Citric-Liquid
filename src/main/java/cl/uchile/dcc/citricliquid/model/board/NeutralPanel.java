public class NeutralPanel extends Panel {

    public NeutralPanel(PanelType type) {
        super(type);
        type = PanelType.NEUTRAL;
    }

    @Override
    public void activatedBy(Player player) {
        return;
    }
}
