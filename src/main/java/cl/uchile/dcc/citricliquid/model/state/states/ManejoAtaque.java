package cl.uchile.dcc.citricliquid.model.state.states;


/**
 * enum for options for a battle.
 *
 */
public enum ManejoAtaque {
  DEFENDER, ESQUIVAR;


  /**
   * elegir given a int (expected in input) return
   * the attack's response.
   *
   */
  public static ManejoAtaque elegir(int input) {
    if (input == 1) {
      return DEFENDER;
    }
    return ESQUIVAR;
  }
}
