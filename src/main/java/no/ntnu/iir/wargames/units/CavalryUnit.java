package no.ntnu.iir.wargames.units;

import no.ntnu.iir.wargames.Unit;

public class CavalryUnit extends Unit {

    private boolean firstCharge = true;

    public CavalryUnit(String name,int health, int attack, int armor) {
        super(name, health, attack, armor);
    }

    public CavalryUnit(String name, int health) {
        super(name, health, 20, 12);
    }


    public int getAttackBonus() {
        if (this.firstCharge) {
            this.firstCharge = false;
            return 6;
        } else {
            return 2;
        }
    }

    public int getResistBonus() {
        return 1;
    }
}
