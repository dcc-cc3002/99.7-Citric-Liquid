package cl.uchile.dcc.citricliquid.States;

import cl.uchile.dcc.citricliquid.model.Entities.Character;
import org.jetbrains.annotations.NotNull;

public class AttackingPlayer2State extends State{
  private Character character;
  public AttackingPlayer2State(){}
  public void setCharacter(Character character) {
    this.character=character;
  }
  public void attack(@NotNull Character attacked){
    System.out.println("The character " + character.getName() + " is attacking " + attacked.getName());
    attacked.ChangeState(new AttackedPlayer1State());
  }
  public boolean isAttacking(){
    return true;
  }
}
