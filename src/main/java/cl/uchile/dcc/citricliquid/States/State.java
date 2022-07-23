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


/**
 * La idea de los estados es la siguiente.
 * Todos los jugadores comienzan en estado IDLE.
 * Luego, si atacan a otro jugador, el que ataca pasa a AttackingPlayer1
 * Esta clase llama a attack y eso hace que el enemigo pase a estado AttackedPlayer2
 * Luego, el estado AttackedPlayer2State tiene la funcion para defenderse. Dependendiendo
 * de lo que quiera el jugador, el enemigo pasara a DefendingPlayer2 o a EvadingPlayer2
 * Posteriormente, si el enemigo sigue vivo atacará al player y sigue lo mismo. Si no sigue vivo, el player pasa a IDLE
 * y el enemigo pasa a RECOVERY.
 * El enemigo pasa a AttackingPlayer2 y esa clase hace que el player pase a AttackedPlayer1.
 * El player se defiende y por ello pasa a EvadingPlayer1 o DefendingPlayer1.
 * Si sigue vivo pasa a idle y si muere a recovery. El enemigo siempre pasa a idle.
 */
