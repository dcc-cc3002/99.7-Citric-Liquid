import java.util.Random;

public class DropPanel extends Panel {

    public DropPanel(PanelType type){
        super(type);
        type = PanelType.DROP;
    }

    @Override
    public void activatedBy(Player player) {
        player.reduceStarsBy(player.roll() * player.getNormaLevel());
    }


}