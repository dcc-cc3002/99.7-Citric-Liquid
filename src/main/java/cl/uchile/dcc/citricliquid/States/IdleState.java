package cl.uchile.dcc.citricliquid.States;

import cl.uchile.dcc.citricliquid.model.Entities.Character;

public class IdleState extends State{
  private Character character;
  public IdleState() {
  }
  public void setCharacter(Character character) {
    this.character=character;
  }
  public void attack(Character enemy){
    character.ChangeState(new AttackingPlayer1State());
    character.attack(enemy);
  }
  public boolean isIdle(){
    return true;
  }

}
