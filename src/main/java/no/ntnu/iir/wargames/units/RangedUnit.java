package no.ntnu.iir.wargames.units;

import no.ntnu.iir.wargames.Unit;

public class RangedUnit extends Unit {

    private int attackedCount = 0;

    public RangedUnit(String name,int health, int attack, int armor) {
        super(name, health, attack, armor);
    }

    public RangedUnit(String name, int health) {
        super(name, health, 15, 8);
    }

    public int getAttackBonus() {
        return 3;
    }

    public int getResistBonus() {
        switch (this.attackedCount) {
            case 0:
                this.attackedCount++;
                return 6;
            case 1:
                this.attackedCount++;
                return 4;
            default:
                this.attackedCount++;
                return 2;
        }
    }
}
