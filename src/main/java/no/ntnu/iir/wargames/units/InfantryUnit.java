package no.ntnu.iir.wargames.units;

import no.ntnu.iir.wargames.Unit;

public class InfantryUnit extends Unit {

    public InfantryUnit(String name,int health, int attack, int armor) {
        super(name, health, attack, armor);
    }

    public InfantryUnit(String name, int health) {
        super(name, health, 15, 10);
    }

    public int getAttackBonus() {
        return 2;
    }

    public int getResistBonus() {
        return 1;
    }
}
