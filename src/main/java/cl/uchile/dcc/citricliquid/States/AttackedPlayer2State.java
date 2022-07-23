package cl.uchile.dcc.citricliquid.States;

import cl.uchile.dcc.citricliquid.model.Entities.Character;
import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

public class AttackedPlayer2State extends State{
  private Character character;
  public AttackedPlayer2State(){}
  public void setCharacter(Character character) {
    this.character=character;
  }

  public void defendDecision(@NotNull Character enemy,String decision){


    if (decision=="defend"){
      character.ChangeState(new DefendingPlayer2State());
      character.defendFrom(enemy);
    }
    if (decision=="evade"){
      character.ChangeState(new EvadingPlayer2State());
      character.evadeFrom(enemy);
    }
  }
  public boolean isAttacked(){
    return true;
  }
}
