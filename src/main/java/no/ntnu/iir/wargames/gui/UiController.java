package no.ntnu.iir.wargames.gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;
import javax.imageio.ImageIO;
import no.ntnu.iir.wargames.Main;
import no.ntnu.iir.wargames.models.Army;
import no.ntnu.iir.wargames.models.Battle;
import no.ntnu.iir.wargames.models.Terrain;
import no.ntnu.iir.wargames.models.UnitFactory;
import no.ntnu.iir.wargames.models.UnitType;
import no.ntnu.iir.wargames.models.units.CavalryUnit;
import no.ntnu.iir.wargames.util.FileUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UiController implements Initializable {
  private Terrain terrain;
  private double[][] terrainArray;
  private UnitFactory unitFactory;
  private FileUtil fileUtil = new FileUtil();
  private ArrayList<Army> armies;
  private FXMLLoader fxmlLoader;

  private static final Logger logger = LogManager.getLogger(UiController.class);

  private GraphicsContext graphicsContext;

  @FXML
  private Label label;

  @FXML
  private TextField armyName;

  @FXML
  private TextField infName;
  @FXML
  private TextField infHP;
  @FXML
  private TextField infNum;

  @FXML
  private TextField rngName;
  @FXML
  private TextField rngHP;
  @FXML
  private TextField rngNum;

  @FXML
  private TextField cavName;
  @FXML
  private TextField cavHP;
  @FXML
  private TextField cavNum;

  @FXML
  private TextField comName;
  @FXML
  private TextField comHP;
  @FXML
  private TextField comNum;

  @FXML
  private ChoiceBox amryChoiceBoxOne;
  @FXML
  private ChoiceBox amryChoiceBoxTwo;


  @FXML
  private ScrollPane scrollPane;

  @FXML
  private Canvas canvas;

  @FXML
  private void handleSimulateButtonAction(ActionEvent event) {
    // Create two default armies
    // Human army setup
    label.setText("");
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

    armies.add(human);
    armies.add(orcish);

    // Start simulation
    Terrain terrain = null;
    Battle battle = new Battle(human, orcish, terrain);
    logger.info(battle);
    try {
      Army v = battle.simulate();
      label.setText(v.getName() + " won with " + v.getAllUnits().size() + " units left!");
      logger.info("Army: " + v.getName() + " won the war!");
      logger.info(battle);
    }
    catch (Exception e) {
      logger.error("error: " + e);
    }
  }

  @FXML
  public void generateMap(ActionEvent event) {
    graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    this.terrain = new Terrain(6, 0.6f, 0.0050f);
    this.terrainArray = terrain.createWorld(330, 415);
    terrain.visualize(terrainArray, "generatedMap");
    Image image = new Image("generatedMap.png");
    graphicsContext.drawImage(image, 0, 0);
  }


  @FXML
  public void createArmy() {
    try {
      Army army = new Army(armyName.getText());
      if (infName.getText() != "" && infHP.getText() != "") {
        army.addAll(unitFactory.createUnits(Integer.parseInt(infNum.getText()), infName.getText(), Integer.parseInt(infHP.getText()), UnitType.INFANTRY));
      }
      if (rngName.getText() != "" && rngHP.getText() != "") {
        army.addAll(unitFactory.createUnits(Integer.parseInt(rngNum.getText()), rngName.getText(), Integer.parseInt(rngHP.getText()), UnitType.RANGED));
      }
      if (cavName.getText() != "" && cavHP.getText() != "") {
        army.addAll(unitFactory.createUnits(Integer.parseInt(cavNum.getText()), cavName.getText(), Integer.parseInt(cavHP.getText()), UnitType.CAVALRY));
      }
      if (comName.getText() != "" && comHP.getText() != "") {
        army.addAll(unitFactory.createUnits(Integer.parseInt(comNum.getText()), comName.getText(), Integer.parseInt(comHP.getText()), UnitType.COMMANDER));
      }
      armies.add(army);
    } catch (Exception e) {
      logger.error(e);
    }
  }

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    graphicsContext = canvas.getGraphicsContext2D();
    armies = new ArrayList<>();
    fxmlLoader = new FXMLLoader();
    unitFactory = new UnitFactory();

  }
}
