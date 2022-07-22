package cl.uchile.dcc.citricliquid.view;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

/**
 * Example JavaFX application.
 */
public class CitricLiquidApp extends Application {
  public static void main(String[] args) {
    launch();
  }

  @Override
  public void start(@NotNull Stage stage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(CitricLiquidApp.class.getResource("hello-view.fxml"));
    Scene scene = new Scene(fxmlLoader.load(), 320, 240);
    stage.setTitle("Hello!");
    stage.setScene(scene);
    stage.show();
  }
}