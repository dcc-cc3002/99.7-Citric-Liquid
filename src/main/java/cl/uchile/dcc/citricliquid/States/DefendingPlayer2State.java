package cl.uchile.dcc.citricliquid.States;

import cl.uchile.dcc.citricliquid.model.Entities.Character;
import org.jetbrains.annotations.NotNull;

public class DefendingPlayer2State extends State{
  private Character character;
  public DefendingPlayer2State(){}
  public void setCharacter(Character character) {
    this.character=character;
  }
  @Override
  public void defend(@NotNull Character enemy) {
    System.out.println(character.getName() +  " have chosen to defend");
    int damage = Math.max(1, enemy.rollAtk() + enemy.getAtk() - (character.rollDef() + character.getDef()));
    character.setCurrentHp(character.getCurrentHp() - damage);
    System.out.println("Attack defended");
    System.out.println("Damage Done:" + damage);
    System.out.println("current hp: " + character.getCurrentHp());
    if (character.isKo()){
      System.out.println(character.getName()+ " is KO " +enemy.getName() + " is victorious" );
      character.ChangeState(new RecoveryState());
      enemy.ChangeState(new IdleState());

    }
    if (character.isKo()==false){
      character.ChangeState(new AttackingPlayer2State());

    }
  }
  public boolean isDefending(){
    return true;
  }
}
