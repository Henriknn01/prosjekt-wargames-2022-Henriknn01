package no.ntnu.iir.wargames.models;

import java.util.concurrent.TimeUnit;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import no.ntnu.iir.wargames.Main;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Simulates a battle between two armies using a turn based system,
 * where they will take turns attacking each other.
 *
 * @author Henrik Norheim Nysæther
 * @version 21.02.2022
 */
public class Battle {
  // class fields
  private Army armyOne;
  private Army armyTwo;
  private Terrain map;
  private static int MAP_HEIGHT = 330;
  private static int MAP_WIDTH = 415;
  private static final Logger logger = LogManager.getLogger(Battle.class);

  /**
   * Battle constructor used to set the two armies that will battle each other.
   *
   * @param armyOne - army one
   * @param armyTwo - army two
   */
  public Battle(Army armyOne, Army armyTwo, Terrain terrain) {
    this.armyOne = armyOne;
    this.armyTwo = armyTwo;
    //this.armyOne.setStartPosition(0, MAP_HEIGHT);
    this.armyTwo.setStartPosition(MAP_WIDTH, MAP_HEIGHT);
    this.map = terrain;
  }

  /**
   * Responsible for removing units that have been killed from their army and,
   * removing dead units from targets.
   *
   * @param enemyArmy - the army the target unit belongs to.
   * @param attackingUnit - unit that is attacking enemy unit.
   */
  public void unitCleanup(Army enemyArmy, Unit attackingUnit) {
    // used try catch, in case of unit not being listed in specified army.
    try {
      if (attackingUnit.getTarget().getHealth() <= 0) {
        enemyArmy.remove(attackingUnit.getTarget());
        attackingUnit.setTarget(null);
      }
    } catch (Exception e) {
      System.err.println("Error: " + e);
    }
  }

  /**
   * Simulates a battle between two armies.
   *
   * @return army - the army that won the simulation
   */
  public Army simulate() throws InterruptedException {
    // Turn is used to keep track of how many turns the simulation has used,
    // and also decide which armies turn it is to attack the opponent.
    int turn = 0;

    if (this.map == null) {
      this.map = new Terrain(6, 0.6f, 0.0050f);
      this.map.createWorld(MAP_HEIGHT, MAP_WIDTH);
      this.map.visualize(this.map.getTerrainArray(), "generatedMap");
    }

    // Simulation loop
    while (this.armyOne.hasUnits() && this.armyTwo.hasUnits()) {
      // this takes one and one unit and attacks
      Unit randomUnitArmyOne = this.armyOne.getRandom();
      Unit randomUnitArmyTwo = this.armyTwo.getRandom();

      if (turn % 2 == 0) {
        for (Unit unit : this.armyOne.getAllUnits()) {
          // TODO: implement apply terrain effects
          if (unit.hasTarget() == false) {
            unit.setTarget(randomUnitArmyTwo);
          } else {
            unit.onUpdate();
            unitCleanup(this.armyTwo, unit);
            unit.setTerrainType(map.getTerrainType((int)unit.getX(), (int)unit.getY()));
          }
        }
      } else {
        for (Unit unit : this.armyTwo.getAllUnits()) {
          if (unit.hasTarget() == false) {
            unit.setTarget(randomUnitArmyOne);
          } else {
            unit.onUpdate();
            unitCleanup(this.armyOne, unit);
            unit.setTerrainType(map.getTerrainType((int)unit.getX(), (int)unit.getY()));
          }
        }
      }
      turn++;
      //TimeUnit.MILLISECONDS.sleep(10);
    }

    // Checks to see who won, then returns the army that won.
    if (this.armyOne.hasUnits()) {
      return this.armyOne;
    } else {
      return this.armyTwo;
    }
  }

  /**
   * Returns battle state as a string.
   *
   * @return Battle as string
   */
  @Override
  public String toString() {
    return "Battle{"
        + "armyOne=" + armyOne.getName()
        + ", armyOne unit count: " + armyOne.getAllUnits().size()
        + ", armyTwo=" + armyTwo.getName()
        + ", armyTwo unit count: " + armyTwo.getAllUnits().size()
        + '}';
  }
}
