package cl.uchile.dcc.citricliquid.model.controller.handlers;

import cl.uchile.dcc.citricliquid.model.controller.Controller;
import java.beans.PropertyChangeEvent;

/**
 * Handler for observer norma.
 *
 */
public class NormaObserver implements Handler {

  private final Controller controller;

  /**
   * Constructor. Only have one parameter.
   *
   */
  public NormaObserver(Controller controller) {
    this.controller = controller;
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    int norma = (int) evt.getNewValue();

    // Luego decidimos que es lo que queremos hacer con el valor nuevo, en este caso llamamos a
    // un método del controlador
    if (norma == 4 && !controller.isBoss()) {
      controller.activeBoss();
    }
    if (norma == 6) {
      controller.checkNorma6();
    }
  }
}
