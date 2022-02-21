package no.ntnu.iir.wargames;

public class Battle {
  // class fields
  private Army armyOne;
  private Army armyTwo;

  public Battle(Army armyOne, Army armyTwo) {
    this.armyOne = armyOne;
    this.armyTwo = armyTwo;
  }

  public Army simulate() {
    // TODO: create method to simulate battle.
    return null;
  }

  @Override
  public String toString() {
    return "Battle{" +
        "armyOne=" + armyOne.getName() +
        ", armyTwo=" + armyTwo.getName() +
        '}';
  }
}
