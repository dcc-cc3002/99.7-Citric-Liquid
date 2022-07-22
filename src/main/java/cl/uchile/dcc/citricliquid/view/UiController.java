package cl.uchile.dcc.citricliquid.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Example GUI controller.
 */
public class UiController {
  @FXML
  private Label welcomeText;

  @FXML
  protected void onHelloButtonClick() {
    welcomeText.setText("Welcome to JavaFX Application!");
  }
}