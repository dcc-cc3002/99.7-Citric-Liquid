package cl.uchile.dcc.citricliquid.model.Handlers;

import org.jetbrains.annotations.NotNull;

import java.beans.PropertyChangeEvent;

public class NormaObserver implements Handler {
  private Controller controller;

  public NormaObserver(Controller controller) {
    this.controller = controller;
  }

  @Override
  public void propertyChange(@NotNull PropertyChangeEvent evt) {
    int normaLevel = (int) evt.getNewValue();
    if (normaLevel==4){
      controller.ActivateBossPanel();
      System.out.println("Activating boss panels");
    }
    if (normaLevel==6){
      System.out.println("Somebody has reached level 6");
      if (controller.getPlayer1().getNormaLevel()==6){
        controller.setWinner(controller.getPlayer1());
      }
      if (controller.getPlayer2().getNormaLevel()==6){
        controller.setWinner(controller.getPlayer2());
      }
      if (controller.getPlayer3().getNormaLevel()==6){
        controller.setWinner(controller.getPlayer3());
      }
      if (controller.getPlayer4().getNormaLevel()==6){
        controller.setWinner(controller.getPlayer4());
      }
    }
  }
}