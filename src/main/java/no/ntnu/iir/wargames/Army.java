package no.ntnu.iir.wargames;

import java.util.List;
import java.util.Objects;
import java.util.Random;


/**
 * Army is a collection of units
 *
 * @author Henrik Norheim Nysæther
 * @version 21.02.2022
 */
public class Army {
  // class fields
  private String name;
  private List<Unit> units;
  private Random randomNumb;

  /**
   * Constructor for army class.
   *
   * @param name - name of the army
   * @param units - list of units
   */
  public Army(String name, List<Unit> units) {
    this.name = name;
    this.units = units;
    this.randomNumb = new Random();
  }

  /**
   * Default constructor for army class.
   *
   * @param name - name of the army
   */
  public Army(String name) {
    this.name = name;
    this.randomNumb = new Random();
  }

  /**
   * Gets the army's name.
   *
   * @return army name
   */
  public String getName() {
    return name;
  }

  /**
   * Add specified unit to the army.
   *
   * @param unit - unit to add
   */
  public void add(Unit unit) {
    this.units.add(unit);
  }

  /**
   * Adds all units in given list to the army.
   *
   * @param units - list of units
   */
  public void addAll(List<Unit> units) {
    this.units.addAll(units);
  }

  /**
   * Remove specified unit from army.
   *
   * @param unit - unit to remove
   */
  public void remove(Unit unit) {
    this.units.remove(unit);
  }

  /**
   * checks if the army has any units.
   *
   * @return true if the army has units
   */
  public boolean hasUnits() {
    return this.units.size() > 0;
  }

  /**
   * gets all units in the army
   *
   * @return List of units - containing all the units in the army
   */
  public List<Unit> getAllUnits() {
    return this.units;
  }

  /**
   * get a random unit from the army
   *
   * @return unit - randomly picked
   */
  public Unit getRandom() {
    return this.units.get(
        this.randomNumb.nextInt(this.units.size())
    );
  }

  /**
   * returns information about the army in string format.
   *
   * @return amry information
   */
  @Override
  public String toString() {
    return "Army{" +
        "name: '" + name + '\'' +
        ", units: " + units.size() +
        '}';
  }
}

