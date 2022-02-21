package no.ntnu.iir.wargames;

/**
 * Unit is a template for creating other units.
 *
 * @author Henrik Norheim Nysæther
 * @version 20.02.2022
 */
public abstract class Unit {
  // Class fields
  private final String name;
  private int health;
  private final int attack;
  private final int armor;

  /**
   * Unit constructor.
   *
   * @param name - name of the unit type
   * @param health - health of the unit
   * @param attack - the amount of damage a unit does on attack
   * @param armor - armor bonus for when the unit gets hit
   */
  protected Unit(String name, int health, int attack, int armor) {
    this.name = name;
    this.health = health;
    this.attack = attack;
    this.armor = armor;
  }

  /**
   * Deals damage to specified unit with the following formula:
   * health_opponent = health_opponent - ( attack + attackBonus )_this + ( armor + resistBonus)_opponent
   *
   * @param opponent - unit to attack
   */
  public void attack(Unit opponent) {
    opponent.health -= (this.attack + this.getAttackBonus()) + (opponent.getArmor() + opponent.getResistBonus());
  }

  /**
   * Returns the name of the unit.
   *
   * @return name
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the health of the unit.
   *
   * @return health
   */
  public int getHealth() {
    return health;
  }

  /**
   * Returns the attack points of the unit.
   *
   * @return attack points
   */
  public int getAttack() {
    return attack;
  }

  /**
   * Returns the armor points of the unit.
   *
   * @return armor points
   */
  public int getArmor() {
    return armor;
  }

  /**
   * Returns the current health of the unit.
   * @param health current health
   */
  public void setHealth(int health) {
    this.health = health;
  }

  /**
   * Returns the unit in string format with information from every field.
   *
   * @return unit information
   */
  public String toString() {
    return ("Name: " + this.name +
        " | Health: " + this.health +
        " | Attack: " + this.attack +
        " | Armor: " + this.armor
    );
  }

  /**
   * The attack bonus is calculated in different ways for each unit.
   * Attack bonus is used to calculate how much damage a unit deals on attack.
   *
   * @return attack bonus
   */
  public abstract int getAttackBonus();

  /**
   * The resist bonus is calculated in different ways for each unit.
   * Resist bonus is a number used to calculate how resistant a unit is to an attack.
   *
   * @return resist bonus
   */
  public abstract int getResistBonus();
}
