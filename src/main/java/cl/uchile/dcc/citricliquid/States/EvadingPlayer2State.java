package cl.uchile.dcc.citricliquid.States;

import cl.uchile.dcc.citricliquid.model.Entities.Character;
import org.jetbrains.annotations.NotNull;

public class EvadingPlayer2State extends State{
  private Character character;
  public EvadingPlayer2State(){}
  public void setCharacter(Character character) {
    this.character=character;
  }
  public void evade(@NotNull Character enemy){
    System.out.println(character.getName() + " have chosen to evade");
    int damage = enemy.rollAtk()+ enemy.getAtk();
    int evasion = character.rollEvd()+ character.getEvd();
    if (evasion<damage ){
      character.setCurrentHp(character.getCurrentHp()- damage);
      System.out.println("Attack not evaded. Full damage received");
      System.out.println("Damage Done:" +  damage);
      System.out.println("current hp: " + character.getCurrentHp());
    }
    if (evasion>damage){
      System.out.println("Attack evaded. No damage received");

    }
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
