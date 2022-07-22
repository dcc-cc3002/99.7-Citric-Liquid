package test.states.createtest;

import cl.uchile.dcc.citricliquid.model.state.states.ManejoAtaque;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *  Test posibilitys in clase manejoAtraque.
 *
 */
public class ManejoAtaqueTest {


  @Test
  public void elegirTest() {
    ManejoAtaque r1 = ManejoAtaque.elegir(0);
    Assertions.assertEquals(ManejoAtaque.DEFENDER.getClass(), r1.getClass());

    r1 = ManejoAtaque.elegir(1);
    Assertions.assertEquals(ManejoAtaque.ESQUIVAR.getClass(), r1.getClass());
  }


}
