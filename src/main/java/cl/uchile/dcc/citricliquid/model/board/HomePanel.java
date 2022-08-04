package cl.uchile.dcc.citricliquid.model.board;

import cl.uchile.dcc.citricliquid.model.unit.*;

public class HomePanel extends AbstractPanel implements Panel{
    /**
     * Creates a new panel.
     *
     * @param id the identification code of the panel.
     */
    public HomePanel(int id) {
        super(id);
    }

    @Override
    public void activatedBy(Player player) {
        player.setCurrentHp(player.getCurrentHp() + 1);
        player.normaCheck();
    }
}
