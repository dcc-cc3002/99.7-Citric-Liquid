package cl.uchile.dcc.citricliquid.model.board;

import cl.uchile.dcc.citricliquid.model.unit.*;

public class BonusPanel extends AbstractPanel{
    /**
     * Creates a new panel.
     *
     * @param id the identification code of the panel.
     */
    public BonusPanel(int id) {
        super(id);
    }

    @Override
    public void activatedBy(Player player) {
        player.increaseStarsBy(player.roll() * Math.min(player.getNormaLevel(), 3));
    }
}
