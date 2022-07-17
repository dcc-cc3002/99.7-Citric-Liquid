package cl.uchile.dcc.citricliquid.model.board;

import cl.uchile.dcc.citricliquid.model.unit.Player;

public class DropPanel extends AbstractPanel{
    /**
     * Creates a new panel.
     *
     * @param id the identification code of the panel.
     */
    public DropPanel(int id) {
        super(id);
    }

    @Override
    public void activatedBy(Player player) {
        player.reduceStarsBy(player.roll() * player.getNormaLevel());
    }
}
