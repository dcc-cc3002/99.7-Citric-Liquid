package cl.uchile.dcc.citricliquid.States;

import cl.uchile.dcc.citricliquid.model.Entities.Character;

public abstract class  State {
  private Character character;
  public State(){
  }
  public void setCharacter(Character character) {
    this.character=character;
  }


  public void attack(Character enemy){
    System.out.println("Error:This character is not attacking");
  }
  public void defendDecision(Character enemy,String decision){
    System.out.println("This character is not deciding");
  }

  public void defend(Character enemy){
    System.out.println("Error:This character is not defending");
  }
  public void evade(Character enemy){
    System.out.println("Error:This character is not evading");
  }

  public void recover(Character character){System.out.println("Error, this character is not in recovery mode");}

  public void idle(Character character){System.out.println("Error, this character is not in idle mode");}

  public boolean isAttacking(){
    return false;
  }
  public boolean isDefending(){
    return false;
  }
  public boolean isAttacked() { return false;}
  public boolean isEvading(){
    return false;
  }
  public boolean isIdle(){
    return false;
  }
  public boolean isRecovering(){
    return false;
  }
}
