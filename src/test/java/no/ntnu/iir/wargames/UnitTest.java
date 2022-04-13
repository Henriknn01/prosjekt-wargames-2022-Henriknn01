package no.ntnu.iir.wargames;

import no.ntnu.iir.wargames.models.Unit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitTest {

    private Unit testUnit;
    private Unit testOpponent;

    @BeforeEach
    void setUp() {
        testUnit = new Unit("Test unit", 100, 20, 10) {
            @Override
            public int getAttackBonus() {
                return 1;
            }

            @Override
            public int getResistBonus() {
                return 2;
            }
        };

        testOpponent = new Unit("Test opponent", 100, 20, 10) {
            @Override
            public int getAttackBonus() {
                return 1;
            }

            @Override
            public int getResistBonus() {
                return 2;
            }
        };
    }

    // Checks if the attack method deals damage to opponent.
    @Test
    void attack() {
        testUnit.attack(testOpponent);
        assertNotEquals(testOpponent.getHealth(), 100);
    }

    // Checks if getName() method returns the correct name.
    @Test
    void getName() {
        assertEquals(testUnit.getName(), "Test unit");
    }

    // Checks if getHealth method returns the correct health.
    @Test
    void getHealth() {
        assertEquals(testUnit.getHealth(), 100);
    }

    // Checks if getAttack method returns the correct attack value.
    @Test
    void getAttack() {
        assertEquals(testUnit.getAttack(), 20);
    }

    // Checks if getAttack method returns the correct armor value.
    @Test
    void getArmor() {
        assertEquals(testUnit.getArmor(), 10);
    }

    // Checks if setHealth method sets the health to the specified value.
    @Test
    void setHealth() {
        testUnit.setHealth(200);
        assertEquals(testUnit.getHealth(), 200);
    }
}