package no.ntnu.iir.wargames.units;

public class CommanderUnit extends CavalryUnit{
    public CommanderUnit(String name, int health, int attack, int armor) {
        super(name, health, attack, armor);
    }

    public CommanderUnit(String name, int health) {
        super(name, health, 25, 15);
    }
}
