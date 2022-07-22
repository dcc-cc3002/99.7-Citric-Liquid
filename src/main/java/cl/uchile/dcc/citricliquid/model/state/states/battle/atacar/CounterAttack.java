package cl.uchile.dcc.citricliquid.model.state.states.battle.atacar;

import cl.uchile.dcc.citricliquid.model.exeptions.InvalidStateOperationException;
import cl.uchile.dcc.citricliquid.model.personaje.AbstractCharacter;
import cl.uchile.dcc.citricliquid.model.personaje.player.Player;
import cl.uchile.dcc.citricliquid.model.state.AbstractState;
import cl.uchile.dcc.citricliquid.model.state.states.turnstate.Inactive;
import org.jetbrains.annotations.NotNull;

/**
 * State for beeing counter attacked.
 *
 */
public class CounterAttack extends AbstractState {
  public CounterAttack(AbstractCharacter context) {
    super(context);
  }

  @Override
  public void battle(@NotNull AbstractCharacter atacado) throws InvalidStateOperationException {
    System.out.println("SE VA A CONTRAATACAR.");
    int ohp = atacado.getCurrentHp();;
    int ataque = context.attack_point();

    atacado.attacked(ataque);
    System.out.println("HP inicial: " + ohp + " Nuevo hp: " + atacado.getCurrentHp());

    if (atacado.getCurrentHp() == 0) {
      System.out.println("El atacado " + atacado.getName() + " está K.O");

      if (context.getClass() == Player.class) {
        ((Player) context).increaseWinsBy(atacado.getWinValue());
      }

      int transference = (int) Math.floor(((float) atacado.getStars()) / 2);
      context.increaseStarsBy(transference);
      atacado.reduceStarsBy(transference);
      System.out.println(" ");
    }

    context.toInactiveState();
    System.out.println("LA BATALLA HA TERMINADO");
  }

  //En el estado de contraataque, solo puede pasar a Inactive

  @Override
  public void toInactiveState() {
    context.setState(new Inactive(context));
  }

  @Override
  public boolean isCounterAttack() {
    return true;
  }
}
