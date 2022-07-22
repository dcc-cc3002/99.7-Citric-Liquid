package cl.uchile.dcc.citricliquid.model.state.states.turnstate;

import cl.uchile.dcc.citricliquid.model.exeptions.InvalidStateOperationException;
import cl.uchile.dcc.citricliquid.model.paneles.AbstracPanel;
import cl.uchile.dcc.citricliquid.model.personaje.AbstractCharacter;
import cl.uchile.dcc.citricliquid.model.personaje.player.Player;
import cl.uchile.dcc.citricliquid.model.state.AbstractState;
import cl.uchile.dcc.citricliquid.model.state.states.battle.atacar.Attack;


/**
 * Class to recive a panel effect.
 *
 */
public class EffectPanelState extends AbstractState {
  public EffectPanelState(AbstractCharacter context) {
    super(context);
  }

  //en este caso las posibles transiciones son:
  //Inactive: en caso de los efectos como bonus y drop.
  //Attack: en caso de los paneles de pelea
  
  @Override
  public int runAction() throws InvalidStateOperationException {
    AbstracPanel actual = ((Player) context).getActualPanel();
    actual.activate((Player) context);
    //cada panel envía a inactive al context.
    return 0;
  }

  @Override
  public void toInactiveState() {
    context.setState(new Inactive(context));
  }

  @Override
  public void toAttack() {
    context.setState(new Attack(context));
  }

  @Override
  public boolean isEffectPanelState() {
    return true;
  }
}
