package cl.uchile.dcc.citricliquid.model.state.states.turnstate;

import cl.uchile.dcc.citricliquid.model.personaje.AbstractCharacter;
import cl.uchile.dcc.citricliquid.model.state.AbstractState;
import cl.uchile.dcc.citricliquid.model.state.states.battle.atacar.Attack;

/**
 * The player stop in a panel. Fot he moves end, gfind another player or is home panel.
 *
 */
public class DetenidoState extends AbstractState {
  public DetenidoState(AbstractCharacter context) {
    super(context);
  }

  //Acá hay dos posibles estados:
  //Puede pasar a atacar, en caso de encontrarse otro jugador.
  //Puede pasar a EffectPanelState, al activar el poder de panel.

  @Override
  public void toAttack() {
    context.setState(new Attack(context));
  }

  @Override
  public void toEffectPanelState() {
    context.setState(new EffectPanelState(context));
  }

  @Override
  public boolean isDetenidoState() {
    return true;
  }

}
