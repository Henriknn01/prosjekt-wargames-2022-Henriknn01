package no.ntnu.iir.wargames.units;

import no.ntnu.iir.wargames.Unit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InfantryUnitTest {

    private Unit infantryUnit;

    // set's up a new cavalry unit for each test.
    @BeforeEach
    void setUp() {
        infantryUnit = new InfantryUnit("Test unit", 100);
    }

    // Checks if the constructor sets the correct values
    @Test
    void constructorTest() {
        assertEquals(infantryUnit.getName(), "Test unit");
        assertEquals(infantryUnit.getHealth(), 100);
    }

    // Checks if the attack bonus is correct.
    @Test
    void getAttackBonus() {
        assertEquals(infantryUnit.getAttackBonus(), 2);
    }

    // Checks if the resist bonus is correct.
    @Test
    void getResistBonus() {
        assertEquals(infantryUnit.getResistBonus(), 1);
    }
}