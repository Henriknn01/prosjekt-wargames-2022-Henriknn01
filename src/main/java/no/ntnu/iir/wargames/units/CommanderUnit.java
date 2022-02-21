package no.ntnu.iir.wargames.units;

/**
 * CommanderUnit class extends CavalryUnit.
 * Commander unit default values, specified in the spec sheet:
 *      attack: 25,
 *      armor: 15
 *
 * @author Henrik Norheim Nysæther
 * @version 21.02.2022
 */
public class CommanderUnit extends CavalryUnit{
  /**
   * Constructor to set all values for the unit.
   *
   * @param name - name of the unit type
   * @param health - health of the unit
   * @param attack - the amount of damage a unit does on attack
   * @param armor - armor bonus for when the unit gets hit
   */
  public CommanderUnit(String name, int health, int attack, int armor) {
    super(name, health, attack, armor);
  }

  /**
   * Default constructor used for creating a new commander unit.
   * This will set the default values specified in the spec sheet.
   *
   * @param name - name of the unit
   * @param health - health of the unit
   */
  public CommanderUnit(String name, int health) {
    super(name, health, 25, 15);
  }
}
