package no.ntnu.iir.wargames.units;

import no.ntnu.iir.wargames.models.Unit;
import no.ntnu.iir.wargames.models.units.CavalryUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CavalryUnitTest {

    private Unit cavalryUnit;

    // set's up a new cavalry unit for each test.
    @BeforeEach
    void setUp() {
        cavalryUnit = new CavalryUnit("Test unit", 100);
    }

    // Checks if the constructor sets the correct values
    @Test
    void constructorTest() {
        assertEquals(cavalryUnit.getName(), "Test unit");
        assertEquals(cavalryUnit.getHealth(), 100);
        assertEquals(cavalryUnit.getAttack(), 20);
        assertEquals(cavalryUnit.getArmor(), 12);
    }

    // Checks if the attack bonus is correct on the first attack.
    @Test
    void getAttackBonus() {
        assertEquals(cavalryUnit.getAttackBonus(), 6);
    }

    // Checks if the attack bonus is correct on the second attack.
    @Test
    void getAttackBonusTwice() {
        cavalryUnit.getAttackBonus();
        assertEquals(cavalryUnit.getAttackBonus(), 2);
    }

    // Checks if the resist bonus is correct.
    @Test
    void getResistBonus() {
        assertEquals(cavalryUnit.getResistBonus(), 1);
    }
}