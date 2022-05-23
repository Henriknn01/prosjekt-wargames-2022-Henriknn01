package no.ntnu.iir.wargames.models;

/**
 * Unit is the base for other units units.
 *
 * @author Henrik Norheim Nysæther
 * @version 20.02.2022
 */
public abstract class Unit extends Actor {
  // Class fields
  private String name;
  private int health;
  private int attack;
  private int armor;
  private Unit target;
  private double range;
  private TerrainType terrainType;

  /**
   * Unit constructor.
   *
   * @param name - name of the unit type
   * @param health - health of the unit
   * @param attack - the amount of damage a unit does on attack
   * @param armor - armor bonus for when the unit gets hit
   */
  protected Unit(String name, int health, int attack, int armor, double range) {
    this.name = name;
    this.health = health;
    this.attack = attack;
    this.armor = armor;
    this.range = range;
    this.target = null;
  }

  /**
   * Deals damage to specified unit with the following formula:
   * health_opponent = health_opponent
   *                  - ( attack + attackBonus )_this
   *                  + ( armor + resistBonus )_opponent.
   *
   * Note: added terrain bonus to the attack formula.
   *
   * @param opponent - unit to attack
   */
  public void attack(Unit opponent) {
    opponent.health -= (
          (this.attack
          + this.getAttackBonus()
          + this.getTerrainBonus()[0])
          + (opponent.getArmor()
          + opponent.getResistBonus()
          + opponent.getTerrainBonus()[1]));
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
   *
   * @param health current health
   */
  public void setHealth(int health) {
    this.health = health;
  }

  /**
   * Checks if the unit has a target.
   *
   * @return boolean - true if unit has target
   */
  public boolean hasTarget() {
    return (target != null);
  }

  /**
   * Gets the target of the unit.
   *
   * @return target
   */
  public Unit getTarget() {
    return this.target;
  }

  /**
   * Sets the target of the unit to a specified unit.
   *
   * @param target target unit
   */
  public void setTarget(Unit target) {
    this.target = target;
  }

  /**
   * Gets the unit range.
   *
   * @return range
   */
  public double getRange() {
    return range;
  }

  /**
   * Sets the unit range to specified range.
   *
   * @param range new range
   */
  public void setRange(double range) {
    this.range = range;
  }

  /**
   * Sets the terrain type of the unit.
   *
   * @param terrainType terrain type
   */
  public void setTerrainType(TerrainType terrainType) {
    this.terrainType = terrainType;
  }

  /**
   * Gets the terrain type the unit is standing on.
   *
   * @return Terrain type
   */
  public TerrainType getTerrainType() {
    return this.terrainType;
  }

  /**
   * Checks if specified position is within the range of the unit.
   *
   * @param position position to check if is inside range
   * @return true if position is inside the range
   */
  public boolean isInRange(double[] position) {
    double distance = Math.abs(this.getDistance(this.getPosition(), position));
    return distance <= this.getRange();
  }

  /**
   * Returns the unit in string format with information from every field.
   *
   * @return unit information
   */
  public String toString() {
    return ("Name: " + this.name
        + " | Health: " + this.health
        + " | Attack: " + this.attack
        + " | Armor: " + this.armor);
  }

  /**
   * Gets the unit terrain bouns
   *
   * @return terrain bonus
   */
  public abstract int[] getTerrainBonus();

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

  /**
   * Method controlls what every unit does on a simulation update.
   */
  @Override
  public void onUpdate() {
    // Checks if the unit has a target, if no target is found then it will get a new one.
    if (this.hasTarget() && this.getTarget() != null) {
      // Checks if the unit is within the range of target unit
      if (this.isInRange(this.getTarget().getPosition())) {
        // Attack the target unit if within range.
        this.attack(this.getTarget());
      } else {
        // Move closer to target if the target is outside the range of the unit.
        this.moveTowardPosition(this.getTarget().getPosition());
      }
    }
  }
}
