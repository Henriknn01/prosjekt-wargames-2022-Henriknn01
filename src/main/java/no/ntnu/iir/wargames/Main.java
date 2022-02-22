package no.ntnu.iir.wargames;

import java.util.ArrayList;
import java.util.List;
import no.ntnu.iir.wargames.units.CavalryUnit;
import no.ntnu.iir.wargames.units.CommanderUnit;
import no.ntnu.iir.wargames.units.InfantryUnit;
import no.ntnu.iir.wargames.units.RangedUnit;

/**
 * Main application class, responsible for starting the simulation.
 *
 * @author Henrik Norheim Nysaether
 * @version 21.02.2022
 */
public class Main {
  public static void main(String[] args) {
    Army human = new Army("Human army");
    Army orcish = new Army("Orcish horde");

    // Human army setup
    List<Unit> humanInfantry = generateUnits(500, "Footman", 100, UnitType.INFANTRY);
    List<Unit> humanCavalry = generateUnits(100, "Knight", 100, UnitType.CAVALRY);
    List<Unit> humanRanged = generateUnits(200, "Archer", 100, UnitType.RANGED);
    List<Unit> humanCommander = generateUnits(1, "Mountain King", 180, UnitType.COMMANDER);

    human.addAll(humanInfantry);
    human.addAll(humanCavalry);
    human.addAll(humanRanged);
    human.addAll(humanCommander);

    // Orcish army setup
    List<Unit> orcishInfantry = generateUnits(500, "Grunt", 100, UnitType.INFANTRY);
    List<Unit> orcishCavalry = generateUnits(100, "Raider", 100, UnitType.CAVALRY);
    List<Unit> orcishRanged = generateUnits(200, "Spearman", 100, UnitType.RANGED);
    List<Unit> orcishCommander = generateUnits(1, "Gul´dan", 180, UnitType.COMMANDER);

    orcish.addAll(orcishInfantry);
    orcish.addAll(orcishCavalry);
    orcish.addAll(orcishRanged);
    orcish.addAll(orcishCommander);

    // Start simulation
    Battle battle = new Battle(human, orcish);
    System.out.println(battle);
    battle.simulate();
    System.out.println(battle);
  }

  /**
   * Generates a list of units with specified name, health and unit type.
   *
   * @param unitSize - amount of units to generate and add to list
   * @param name - name for the units
   * @param health - health of the units
   * @param unitType - the type of unit to generate
   * @return List of units
   */
  public static List<Unit> generateUnits(int unitSize, String name, int health, UnitType unitType) {
    ArrayList<Unit> unitList = new ArrayList<>();

    for (int i = 0; i < unitSize; i++) {
      switch (unitType) {
        case RANGED -> unitList.add(new RangedUnit(name, health));
        case CAVALRY -> unitList.add(new CavalryUnit(name, health));
        case INFANTRY -> unitList.add(new InfantryUnit(name, health));
        case COMMANDER -> unitList.add(new CommanderUnit(name, health));
      }
    }

    return unitList;
  }
}
