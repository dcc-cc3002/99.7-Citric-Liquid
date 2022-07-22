package cl.uchile.dcc.citricliquid.model.state.states.turnstate;

import cl.uchile.dcc.citricliquid.model.paneles.AbstracPanel;
import cl.uchile.dcc.citricliquid.model.personaje.AbstractCharacter;
import cl.uchile.dcc.citricliquid.model.personaje.player.Player;
import cl.uchile.dcc.citricliquid.model.state.AbstractState;

/**
 * the player move in the panels.
 *
 */
public class MoveState extends AbstractState {
  public MoveState(AbstractCharacter context) {
    super(context);
  }


  //De acá solo puede pasar a detenerse.
  @Override
  public void toDetenidoState() {
    context.setState(new DetenidoState(context));
  }

  @Override
  public int mover_un_panel() {
    AbstracPanel actualPanel = ((Player) context).getActualPanel();
    if (actualPanel.getNextPanels().size() == 1) {
      actualPanel.mover_un_panel((Player) context, 0);
    }
    //else {
    //System.out.println("Se tienen las opciones");
    //int j=0;
    //for (AbstracPanel pa: actual.getNextPanels()) {
    //  System.out.println("Seleccione " + j + ". " + pa.toString());
    //  j=j+1;
    //}
    //Scanner scanner = new Scanner(System.in);
    //int i = scanner.nextInt();
    // actual.mover_un_panel((Player) context, i);
    //}
    return 0;
  }

  @Override
  public boolean isMoveState() {
    return true;
  }

}
