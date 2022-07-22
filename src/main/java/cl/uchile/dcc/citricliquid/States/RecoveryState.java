package cl.uchile.dcc.citricliquid.States;

import cl.uchile.dcc.citricliquid.model.Handlers.Controller;
import cl.uchile.dcc.citricliquid.model.Entities.Character;
import org.jetbrains.annotations.NotNull;

public class RecoveryState extends State{
  private Character character;
  public RecoveryState(){}
  public void setCharacter(Character character) {
    this.character=character;
  }
  public void recover(@NotNull Character character, Controller controller){
    int roll = character.roll();

  }
  public boolean isRecovering(){
    return true;
  }
}
