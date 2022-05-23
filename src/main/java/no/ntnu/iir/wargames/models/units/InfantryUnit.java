package no.ntnu.iir.wargames.models.units;

import no.ntnu.iir.wargames.models.Unit;
import no.ntnu.iir.wargames.models.UnitFactory;

/**
 * InfantryUnit class extends Unit class to create a specialized unit.
 * Infantry unit default values, specified in the spec sheet:
 *      attack: 15,
 *      armor: 10,
 * Other default values:
 *      range: 50
 *
 * @author Henrik Norheim Nysæther
 * @version 21.05.2022
 */
public class InfantryUnit extends Unit {
  /**
   * Constructor to set all values for the unit.
   *
   * @param name - name of the unit type
   * @param health - health of the unit
   * @param attack - the amount of damage a unit does on attack
   * @param armor - armor bonus for when the unit gets hit
   * @param range - attack range of the unit
   */
  public InfantryUnit(String name, int health, int attack, int armor, double range) {
    super(name, health, attack, armor, range);
  }

  /**
   * Default constructor used for creating a new infantry unit.
   * This will set the default values specified in the spec sheet.
   *
   * @param name - name of the unit
   * @param health - health of the unit
   */
  public InfantryUnit(String name, int health) {
    super(name, health, 15, 10, 50);
  }

  /**
   * Calculates the attack bonus for the unit.
   * Temporarily set to return a static attack bonus.
   *
   * @return 2
   */
  public int getAttackBonus() {
    return 2;
  }

  /**
   * Calculates the resist bonus for the unit.
   * Temporarly set to return a static attack bonus.
   *
   * @return 1
   */
  public int getResistBonus() {
    return 1;
  }

  /**
   * Bonus is given in like an array like this [attack, armor, range].
   *
   * @return 2d array with values [attack, armor]
   */
  @Override
  public int[] getTerrainBonus() {
    int[] bonus = new int[2];
    switch (this.getTerrainType()) {
      case HILL: {
        bonus[0] = 0; // attack bonus
        bonus[1] = 0; // armor bonus
      }
      case FOREST: {
        bonus[0] = 5;
        bonus[1] = 4;
      }
      case PLAINS: {
        bonus[0] = 0;
        bonus[1] = 0;
      }
    }
    return bonus;
  }
}
