package no.ntnu.iir.wargames.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import no.ntnu.iir.wargames.models.units.CavalryUnit;
import no.ntnu.iir.wargames.models.units.CommanderUnit;
import no.ntnu.iir.wargames.models.units.InfantryUnit;
import no.ntnu.iir.wargames.models.units.RangedUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Army is a collection of units.
 *
 * @author Henrik Norheim Nysæther
 * @version 21.02.2022
 */
public class Army {
  // class fields
  private String name;
  private ArrayList<Unit> units;
  private Random randomNumb;
  private static final Logger logger = LogManager.getLogger(Army.class);

  /**
   * Constructor for army class.
   *
   * @param name - name of the army
   * @param units - list of units
   */
  public Army(String name, List<Unit> units) {
    this.name = name;
    this.units = new ArrayList<>();
    this.randomNumb = new Random();
  }

  /**
   * Default constructor for army class.
   *
   * @param name - name of the army
   */
  public Army(String name) {
    this.name = name;
    this.units = new ArrayList<>();
    this.randomNumb = new Random();
  }

  /**
   * Gets the army's name.
   *
   * @return army name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the army's name.
   *
   * @param name - new army name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Add specified unit to the army.
   *
   * @param unit - unit to add
   */
  public void add(Unit unit) {
    this.units.add(unit);
  }

  /**
   * Adds all units in given list to the army.
   *
   * @param units - list of units
   */
  public void addAll(List<Unit> units) {
    this.units.addAll(units);
  }

  /**
   * Remove specified unit from army.
   *
   * @param unit - unit to remove
   */
  public void remove(Unit unit) {
    this.units.remove(unit);
  }

  /**
   * checks if the army has any units.
   *
   * @return true if the army has units
   */
  public boolean hasUnits() {
    return !this.units.isEmpty();
  }

  /**
   * gets all units in the army.
   *
   * @return List of units - containing all the units in the army
   */
  public List<Unit> getAllUnits() {
    return this.units;
  }

  /**
   * get a random unit from the army.
   *
   * @return unit - randomly picked
   */
  public Unit getRandom() {
    return this.units.get(
        this.randomNumb.nextInt(this.units.size())
    );
  }

  /**
   * Generates a list of units with specified name, health and unit type.
   *
   * @param unitSize - amount of units to generate and add to list
   * @param name - name for the units
   * @param health - health of the units
   * @param unitType - the type of unit to generate
   */
  public void generateUnits(int unitSize, String name, int health, UnitType unitType) {
    ArrayList<Unit> unitList = new ArrayList<>();
    for (int i = 0; i < unitSize; i++) {
      addUnitOfUnitType(unitType, name, health);
    }
  }

  /**
   * Adds unit of unit type to army.
   *
   * @param unitType - UnitType of army unit
   * @param unitName - Name of the unit
   * @param health - Unit health
   */
  public void addUnitOfUnitType(UnitType unitType, String unitName, int health) {
    switch (unitType) {
      case RANGED -> this.units.add(new RangedUnit(unitName, health));
      case CAVALRY -> this.units.add(new CavalryUnit(unitName, health));
      case INFANTRY -> this.units.add(new InfantryUnit(unitName, health));
      case COMMANDER -> this.units.add(new CommanderUnit(unitName, health));
      default -> logger.info("Invalid unit type given");
    }
  }

  /**
   * Get the UnitType of a specified unit.
   *
   * @param unit - unit to get UnitType for
   * @return UnitType of specified unit
   */
  public UnitType getUnitType(Unit unit) {
    return switch (unit.getClass().getSimpleName()) {
      case "CavalryUnit" -> UnitType.CAVALRY;
      case "CommanderUnit" -> UnitType.COMMANDER;
      case "InfantryUnit" -> UnitType.INFANTRY;
      case "RangedUnit" -> UnitType.RANGED;
      default -> UnitType.UNKNOWN;
    };
  }

  /**
   * gets a list of all infantry units in the army.
   *
   * @return list of infantry units
   */
  public List<Unit> getInfantryUnits() {
    return this.units.stream()
            .filter(unit -> unit.getClass().equals(InfantryUnit.class))
            .toList();
  }

  /**
   * gets a list of all cavalry units in the army.
   *
   * @return list of cavalry units
   */
  public List<Unit> getCavalryUnits() {
    return this.units.stream()
            .filter(unit -> unit.getClass().equals(CavalryUnit.class))
            .toList();
  }

  /**
   * gets a list of all ranged units in the army.
   *
   * @return list of ranged units
   */
  public List<Unit> getRangedUnit() {
    return this.units.stream()
            .filter(unit -> unit.getClass().equals(RangedUnit.class))
            .toList();
  }

  /**
   * gets a list of all commander units in the army.
   *
   * @return list of commander units
   */
  public List<Unit> getCommanderUnits() {
    return this.units.stream()
            .filter(unit -> unit.getClass().equals(CommanderUnit.class))
            .toList();
  }

  /**
   * returns information about the army in string format.
   *
   * @return amry information
   */
  @Override
  public String toString() {
    return "Army{"
        + "name: '" + name + '\''
        + ", units: " + units.size()
        + '}';
  }
}

