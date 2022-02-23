package no.ntnu.iir.wargames;

/**
 * Main application class, responsible for starting the simulation.
 *
 * @author Henrik Norheim Nysaether
 * @version 21.02.2022
 */
public class Main {
  /**
   * Main method, responsible for running application.
   *
   * @param args - any argument to pass to the application
   */
  public static void main(String[] args) {
    Army human = new Army("Human army");
    Army orcish = new Army("Orcish horde");

    // Human army setup
    human.generateUnits(500, "Footman", 100, UnitType.INFANTRY);
    human.generateUnits(100, "Knight", 100, UnitType.CAVALRY);
    human.generateUnits(200, "Archer", 100, UnitType.RANGED);
    human.generateUnits(1, "Mountain King", 180, UnitType.COMMANDER);

    // Orcish army setup
    orcish.generateUnits(500, "Grunt", 100, UnitType.INFANTRY);
    orcish.generateUnits(100, "Raider", 100, UnitType.CAVALRY);
    orcish.generateUnits(200, "Spearman", 100, UnitType.RANGED);
    orcish.generateUnits(1, "Gul´dan", 180, UnitType.COMMANDER);

    // Start simulation
    Battle battle = new Battle(human, orcish);
    System.out.println(battle);
    Army v = battle.simulate();
    System.out.println("Army: " + v.getName() + " won the war!");
    System.out.println(battle);
  }
}
