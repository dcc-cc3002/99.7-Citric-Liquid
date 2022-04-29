module cl.uchile.dcc.citricliquid {
  requires javafx.controls;
  requires javafx.fxml;

  requires org.controlsfx.controls;
  requires org.jetbrains.annotations;

  exports cl.uchile.dcc.citricliquid.view;
  opens cl.uchile.dcc.citricliquid.view to javafx.fxml;
}