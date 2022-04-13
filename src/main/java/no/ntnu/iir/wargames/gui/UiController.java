package no.ntnu.iir.wargames.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class UiController implements Initializable {
  @FXML
  private Label label;

  @FXML
  private Canvas canvas;

  @FXML
  private void handleButtonAction(ActionEvent event) {
    //System.out.println("You clicked me!");
    //label.setText("Hello World!");
    GraphicsContext gc = canvas.getGraphicsContext2D();
    gc.setStroke(Color.FORESTGREEN.brighter());
    gc.setLineWidth(2);
    gc.strokeOval(30, 30, 80, 80);
    gc.fillOval(10, 10, 4, 4);
  }

  @Override
  public void initialize(URL url, ResourceBundle rb) {
  }

  public void onUpdate() {
    // onUpdate will run on every frame update
  }
}
