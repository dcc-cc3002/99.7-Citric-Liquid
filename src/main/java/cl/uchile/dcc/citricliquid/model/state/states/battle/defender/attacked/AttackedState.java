package cl.uchile.dcc.citricliquid.model.state.states.battle.defender.attacked;

import cl.uchile.dcc.citricliquid.model.personaje.AbstractCharacter;
import cl.uchile.dcc.citricliquid.model.state.AbstractState;


/**
 * State for beeing attacked.
 *
 */
public class AttackedState extends AbstractState {
  public AttackedState(AbstractCharacter context) {
    super(context);
  }


  //ACA SE DEBE RECIBIR UN INPUT PARA INDICAR SI DEFIENDE O ESQUIVA.
  ///CON ESTE VALOR SE DETERMINARÁ SI VA A LA CLASE DE DEFENDER O ESQUIVAR
  @Override
  public void toAttackedDefender()  {
    context.setState(new AttackedDefender(context));
  }

  @Override
  public void toAttackedEsquivar()  {
    context.setState(new AttackedEsquivar(context));
  }

  @Override
  public boolean isAttackedState() {
    return true;
  }

}
