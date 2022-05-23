package no.ntnu.iir.wargames.models;

import java.util.ArrayList;
import no.ntnu.iir.wargames.models.units.CavalryUnit;
import no.ntnu.iir.wargames.models.units.CommanderUnit;
import no.ntnu.iir.wargames.models.units.InfantryUnit;
import no.ntnu.iir.wargames.models.units.RangedUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Unit factory, created to be in compliance with spec sheet.
 * UnitFactory contains methods such as createUnit, getUnitType and generateUnits.
 *
 * @author Henrik Norheim Nysæther
 * @version 22.05.2022
 */
public class UnitFactory {
  private static final Logger logger = LogManager.getLogger(UnitFactory.class);
  /**
   * Creates unit of unit type.
   *
   * @param unitType - UnitType of army unit
   * @param unitName - Name of the unit
   * @param health - Unit health
   */
  public Unit createUnit(UnitType unitType, String unitName, int health) {
    Unit result = null;
    switch (unitType) {
      case RANGED -> result = (new RangedUnit(unitName, health));
      case CAVALRY -> result = (new CavalryUnit(unitName, health));
      case INFANTRY -> result = (new InfantryUnit(unitName, health));
      case COMMANDER -> result = (new CommanderUnit(unitName, health));
      default -> logger.info("Invalid unit type given");
    }
    return result;
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
   * Generates a list of units with specified name, health and unit type.
   *
   * @param unitSize - amount of units to generate and add to list
   * @param name - name for the units
   * @param health - health of the units
   * @param unitType - the type of unit to generate
   * @return ArrayList of units
   */
  public ArrayList<Unit> createUnits(int unitSize, String name, int health, UnitType unitType) {
    ArrayList<Unit> unitList = new ArrayList<>();
    for (int i = 0; i < unitSize; i++) {
      unitList.add(createUnit(unitType, name, health));
    }
    return unitList;
  }
}
