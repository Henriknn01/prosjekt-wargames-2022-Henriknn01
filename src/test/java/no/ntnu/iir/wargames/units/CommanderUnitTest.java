package no.ntnu.iir.wargames.units;

import no.ntnu.iir.wargames.models.Unit;
import no.ntnu.iir.wargames.models.units.CommanderUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommanderUnitTest {
    private Unit commanderUnit;

    @BeforeEach
    void setUp() {
        commanderUnit = new CommanderUnit("Commander", 100);
    }

    // Checks if the constructor sets the correct values
    @Test
    void constructorTest() {
        assertEquals(commanderUnit.getName(), "Commander");
        assertEquals(commanderUnit.getHealth(), 100);
        assertEquals(commanderUnit.getAttack(), 25);
        assertEquals(commanderUnit.getArmor(), 15);
    }
}