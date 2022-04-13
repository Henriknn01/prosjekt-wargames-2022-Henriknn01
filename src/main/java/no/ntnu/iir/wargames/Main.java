package no.ntnu.iir.wargames;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main application class, responsible for starting the simulation.
 *
 * @author Henrik Norheim Nysaether
 * @version 21.02.2022
 */
public class Main extends Application {
  /**
   * Main method, responsible for running application.
   *
   * @param args - any argument to pass to the application
   */
  public static void main(String[] args) {
    /*
    // Human army setup
    Army human = new Army("Human army");

    human.generateUnits(500, "Footman", 100, UnitType.INFANTRY);
    human.generateUnits(100, "Knight", 100, UnitType.CAVALRY);
    human.generateUnits(200, "Archer", 100, UnitType.RANGED);
    human.generateUnits(1, "Mountain King", 180, UnitType.COMMANDER);

    // Orcish army setup
    Army orcish = new Army("Orcish horde");

    orcish.generateUnits(500, "Grunt", 100, UnitType.INFANTRY);
    orcish.generateUnits(100, "Raider", 100, UnitType.CAVALRY);
    orcish.generateUnits(200, "Spearman", 100, UnitType.RANGED);
    orcish.generateUnits(1, "Gul´dan", 180, UnitType.COMMANDER);

    FileUtil fileUtil = new FileUtil();
    fileUtil.saveArmyToFile(human, "C:\\Users\\Henrik\\IdeaProjects\\prosjekt-wargames-2022-Henriknn01\\src\\main\\resources\\saves\\examples", "human_army");

    // Start simulation
    Battle battle = new Battle(human, orcish);
    System.out.println(battle);
    Army v = battle.simulate();
    System.out.println("Army: " + v.getName() + " won the war!");
    System.out.println(battle);
    System.out.println(CavalryUnit.class.getSimpleName());
    */

    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    try {
      Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("MainPage.fxml"));
      primaryStage.setTitle("Battle simulation");
      Scene scene = new Scene(root, 640, 430);
      primaryStage.setScene(scene);
      primaryStage.show();
    } catch (Exception e) {
      System.out.println("error loading fxml file");
    }
  }
}
