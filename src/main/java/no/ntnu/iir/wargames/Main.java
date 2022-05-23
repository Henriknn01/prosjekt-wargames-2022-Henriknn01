package no.ntnu.iir.wargames;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import no.ntnu.iir.wargames.models.units.CommanderUnit;
import no.ntnu.iir.wargames.util.FileUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Main application class, responsible for starting the simulation.
 *
 * @author Henrik Norheim Nysaether
 * @version 21.02.2022
 */
public class Main extends Application {
  private static final Logger logger = LogManager.getLogger(Main.class);
  /**
   * Main method, responsible for running application.
   *
   * @param args - any argument to pass to the application
   */
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    try {
      Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("wargames.fxml"));
      primaryStage.setTitle("Battle simulation");
      Scene scene = new Scene(root, 640, 430);
      scene.getStylesheets().add("style.css");
      primaryStage.setScene(scene);
      primaryStage.show();
    } catch (Exception e) {
      e.printStackTrace();
      logger.error("error loading fxml file");
      System.exit(0);
    }
  }
}
