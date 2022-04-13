package no.ntnu.iir.wargames.units;

import no.ntnu.iir.wargames.models.Unit;
import no.ntnu.iir.wargames.models.units.RangedUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RangedUnitTest {

    private Unit rangedUnit;

    // set's up a new cavalry unit for each test.
    @BeforeEach
    void setUp() {
        rangedUnit = new RangedUnit("Test unit", 100);
    }

    // Checks if the constructor sets the correct values
    @Test
    void constructorTest() {
        assertEquals(rangedUnit.getName(), "Test unit");
        assertEquals(rangedUnit.getHealth(), 100);
        assertEquals(rangedUnit.getAttack(), 15);
        assertEquals(rangedUnit.getArmor(), 8);
    }

    // Checks if the attack bonus is correct.
    @Test
    void getAttackBonus() {
        assertEquals(rangedUnit.getAttackBonus(), 3);
    }

    // Checks if the resist bonus is correct.
    @Test
    void getResistBonusOne() {
        assertEquals(rangedUnit.getResistBonus(), 6);
    }

    // Checks if the resist bonus is correct.
    @Test
    void getResistBonusTwo() {
        rangedUnit.getResistBonus();
        assertEquals(rangedUnit.getResistBonus(), 4);
    }

    // Checks if the resist bonus is correct.
    @Test
    void getResistBonusThree() {
        rangedUnit.getResistBonus();
        rangedUnit.getResistBonus();
        assertEquals(rangedUnit.getResistBonus(), 2);
    }
}