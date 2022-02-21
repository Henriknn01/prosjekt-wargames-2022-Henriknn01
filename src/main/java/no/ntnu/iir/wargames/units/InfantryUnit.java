package no.ntnu.iir.wargames.units;

import no.ntnu.iir.wargames.Unit;

/**
 * InfantryUnit class extends Unit class to create a specialized unit.
 * Infantry unit default values, specified in the spec sheet:
 *      attack: 15,
 *      armor: 10
 *
 * @author Henrik Norheim Nysæther
 * @version 21.02.2022
 */
public class InfantryUnit extends Unit {
  /**
   * Constructor to set all values for the unit.
   *
   * @param name - name of the unit type
   * @param health - health of the unit
   * @param attack - the amount of damage a unit does on attack
   * @param armor - armor bonus for when the unit gets hit
   */
  public InfantryUnit(String name,int health, int attack, int armor) {
    super(name, health, attack, armor);
  }

  /**
   * Default constructor used for creating a new infantry unit.
   * This will set the default values specified in the spec sheet.
   *
   * @param name - name of the unit
   * @param health - health of the unit
   */
  public InfantryUnit(String name, int health) {
    super(name, health, 15, 10);
  }

  /**
   * Calculates the attack bonus for the unit.
   * Temporarly set to return a static attack bonus.
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
}
