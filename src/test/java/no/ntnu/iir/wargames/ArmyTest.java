package no.ntnu.iir.wargames;

import no.ntnu.iir.wargames.units.InfantryUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ArmyTest {

    private Army testArmy;

    @BeforeEach
    void setUp() {
        testArmy = new Army("Human army");
    }

    // Checks if getName() method returns the correct name.
    @Test
    void getName() {
        assertEquals(testArmy.getName(), "Human army");
    }

    // Checks if add() method adds a unit to the army.
    @Test
    void add() {
        Unit testUnit = new InfantryUnit("Inf", 100);
        testArmy.add(testUnit);

        assertTrue(testArmy.hasUnits());
    }

    // Checks if addAll() method adds units from list to army.
    @Test
    void addAll() {
        ArrayList<Unit> unitList = new ArrayList<>();
        Unit testUnit1 = new InfantryUnit("Inf", 100);
        Unit testUnit2 = new InfantryUnit("Inf 2", 100);
        unitList.add(testUnit1);
        unitList.add(testUnit2);

        testArmy.addAll(unitList);
        assertTrue(testArmy.hasUnits());
    }

    // Checks if remove() method removes a unit from the army.
    @Test
    void remove() {
        Unit testUnit = new InfantryUnit("Inf", 100);
        testArmy.add(testUnit);
        assertTrue(testArmy.hasUnits());

        testArmy.remove(testUnit);
        assertFalse(testArmy.hasUnits());
    }

    // Checks if army has any units in it.
    @Test
    void hasUnits() {
        Unit testUnit = new InfantryUnit("Inf", 100);
        testArmy.add(testUnit);
        assertTrue(testArmy.hasUnits());
    }

    // Checks if the army has no units in it.
    @Test
    void hasNoUnits() {
        assertFalse(testArmy.hasUnits());
    }
}
