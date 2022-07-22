package cl.uchile.dcc.citricliquid.model.state.states.battle.atacar;

import cl.uchile.dcc.citricliquid.model.exeptions.InvalidStateOperationException;
import cl.uchile.dcc.citricliquid.model.personaje.AbstractCharacter;
import cl.uchile.dcc.citricliquid.model.personaje.player.Player;
import cl.uchile.dcc.citricliquid.model.state.AbstractState;
import cl.uchile.dcc.citricliquid.model.state.states.battle.defender.counterattacked.CounterAttacked;
import cl.uchile.dcc.citricliquid.model.state.states.turnstate.Inactive;
import org.jetbrains.annotations.NotNull;

/**
 * Class to attack for first time.
 *
 */
public class Attack extends AbstractState {
  public Attack(AbstractCharacter context) {
    super(context);
  }

  @Override
  public void battle(@NotNull AbstractCharacter atacado) throws InvalidStateOperationException {
    System.out.println("SE HA INICIADO UNA BATALLA");
    final int ohp = atacado.getCurrentHp();;
    final int ataque = context.attack_point();

    if (atacado.isInactive()) {
      atacado.toAttackedState();
    }
    atacado.attacked(ataque);
    System.out.println("HP inicial: " + ohp + " Nuevo hp: " + atacado.getCurrentHp());

    if (atacado.getCurrentHp() == 0) {
      System.out.println("El atacado " + atacado.getName() + " está K.O");

      ((Player) context).increaseWinsBy(atacado.getWinValue());

      int transference = (int) Math.floor(((float) atacado.getStars()) / 2);
      context.increaseStarsBy(transference);
      atacado.reduceStarsBy(transference);

      context.toInactiveState();
      atacado.toInactiveState();

      System.out.println("LA BATALLA HA TERMINADO");
      System.out.println(" ");
    } else {
      atacado.toCounterAttackState();
      ((Player) context).toCounterAttacked();
    }
  }

  //Hay dos posibles transiciones:
  //A inactivo se mató al enemigo.
  //A contraAtacado si no mató al enemigo.

  @Override
  public void toInactiveState() {
    context.setState(new Inactive(context));
  }

  @Override
  public void toCounterAttacked() {
    context.setState(new CounterAttacked(context));
  }

  @Override
  public boolean isAttack() {
    return true;
  }

}
