package no.ntnu.iir.wargames.units;

import no.ntnu.iir.wargames.Unit;

/**
 * RangedUnit class extends Unit class to create a specialized unit.
 * Ranged unit default values, specified in the spec sheet:
 *      attack: 15,
 *      armor: 8
 *
 * @author Henrik Norheim Nysæther
 * @version 21.02.2022
 */
public class RangedUnit extends Unit {
  // class fields
  private int attackedCount = 0;

  /**
   * Constructor to set all values for the unit.
   *
   * @param name - name of the unit type
   * @param health - health of the unit
   * @param attack - the amount of damage a unit does on attack
   * @param armor - armor bonus for when the unit gets hit
   */
  public RangedUnit(String name,int health, int attack, int armor) {
    super(name, health, attack, armor);
  }

  /**
   * Default constructor used for creating a new ranged unit.
   * This will set the default values specified in the spec sheet.
   *
   * @param name - name of the unit
   * @param health - health of the unit
   */
  public RangedUnit(String name, int health) {
    super(name, health, 15, 8);
  }

  /**
   * Calculates the attack bonus for the unit.
   * Temporarly set to return a static attack bonus.
   *
   * @return 3
   */
  public int getAttackBonus() {
    return 3;
  }

  /**
   * Resist value is based on if the unit has attacked a unit previously.
   * Resist bonus specified by spec sheet:
   *    First attack: 6,
   *    Second attack: 4,
   *    Base bonus: 2
   *
   * @return 6 on the first attack, 4 on the second attack, 2 base bonus.
   */
  public int getResistBonus() {
    switch (this.attackedCount) {
      // First attack
      case 0:
        this.attackedCount++;
        return 6;
      // Second attack
      case 1:
        this.attackedCount++;
        return 4;
      // Default - base resist
      default:
        this.attackedCount++;
        return 2;
    }
  }
}
