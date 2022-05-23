package no.ntnu.iir.wargames.models.units;

import no.ntnu.iir.wargames.models.Unit;
import no.ntnu.iir.wargames.models.UnitFactory;

/**
 * CavalryUnit class extends Unit class to create a specialized unit.
 * Cavalry unit default values, specified in the spec sheet:
 *      attack: 20,
 *      armor: 12
 * Other default values:
 *      range: 100
 *
 * @author Henrik Norheim Nysæther
 * @version 21.05.2022
 */
public class CavalryUnit extends Unit {
  // class fields
  private boolean firstCharge = true;

  /**
   * Constructor to set all values for the unit.
   *
   * @param name - name of the unit type
   * @param health - health of the unit
   * @param attack - the amount of damage a unit does on attack
   * @param armor - armor bonus for when the unit gets hit
   * @param range - attack range of the unit
   */
  public CavalryUnit(String name, int health, int attack, int armor, double range) {
    super(name, health, attack, armor, range);
  }

  /**
   * Default constructor used for creating a new cavalry unit.
   * This will set the default values specified in the spec sheet.
   *
   * @param name - name of the unit
   * @param health - health of the unit
   */
  public CavalryUnit(String name, int health) {
    super(name, health, 20, 12, 100);
  }

  /**
   * Calculates the attack bonus for the unit.
   * If the attack is the first charge, the unit will recive an additional bonus of 4 attack points.
   *
   * @return 6 on first charge, 2 default bonus
   */
  public int getAttackBonus() {
    if (this.firstCharge) {
      this.firstCharge = false;
      return 6;
    } else {
      return 2;
    }
  }

  /**
   * Calculates the resist bonus for the unit.
   * Temporarily set to return a static attack bonus.
   *
   * @return 1
   */
  public int getResistBonus() {
    return 1;
  }

  /**
   * Bonus is given in like an array like this [attack, armor].
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
        bonus[0] = 0;
        bonus[1] = -this.getResistBonus();
      }
      case PLAINS: {
        bonus[0] = 6;
        bonus[1] = 0;
      }
    }
    return bonus;
  }
}
