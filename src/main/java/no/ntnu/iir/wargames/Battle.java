package no.ntnu.iir.wargames;

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

  /**
   * Battle constructor used to set the two armies that will battle each other.
   *
   * @param armyOne - army one
   * @param armyTwo - army two
   */
  public Battle(Army armyOne, Army armyTwo) {
    this.armyOne = armyOne;
    this.armyTwo = armyTwo;
  }

  /**
   * Responsible for removing units that have been killed from their army.
   *
   * @param army - the army the unit belongs to.
   * @param unit - unit to check if was killed.
   */
  public void unitCleanup(Army army, Unit unit) {
    // used try catch, in case of unit not being listed in specified army.
    // TODO: assess if try catch is necessary
    try {
      if (unit.getHealth() <= 0 ) {
        army.remove(unit);
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
  public Army simulate() {
    // Turn is used to keep track of how many turns the simulation has used,
    // and also decide which armies turn it is to attack the opponent.
    int turn = 0;

    // Simulation loop
    while (this.armyOne.hasUnits() && this.armyTwo.hasUnits()) {
      Unit randomUnitArmyOne = this.armyOne.getRandom();
      Unit randomUnitArmyTwo = this.armyTwo.getRandom();

      // Decides who's turn it is to attack. Once an attack has been made, the cleanup method is run
      // to remove the unit from the army if it was killed.
      if (turn % 2 == 0) {
        randomUnitArmyOne.attack(randomUnitArmyTwo);
        unitCleanup(armyTwo, randomUnitArmyTwo);
      } else {
        randomUnitArmyTwo.attack(randomUnitArmyOne);
        unitCleanup(armyOne, randomUnitArmyOne);
      }

      turn++;
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
    return "Battle{" +
        "armyOne=" + armyOne.getName() + ", armyOne unit count: " + armyOne.getAllUnits().size() +
        ", armyTwo=" + armyTwo.getName() +", armyTwo unit count: " + armyOne.getAllUnits().size() +
        '}';
  }
}
