package com.epam.bootcamp.bmi_calculator;

import static org.junit.Assert.*;

import org.junit.*;

public class GuessTheUnitsTest {

    @Test
    public void shouldRecognizeMetersAndKilosAsMetric() throws Exception {
        GuessTheUnits gtu = new GuessTheUnits(1.7, 50.0);
        assertEquals(gtu.getUnitType(), "metric");
    }

    @Test
    public void shouldRecognizeCmsAndKilosAsMetric() throws Exception {
        GuessTheUnits gtu = new GuessTheUnits(170, 50.0);
        assertEquals(gtu.getUnitType(), "metric");
    }

    @Test
    public void shouldRecognizeFeetAndOuncesAsUS() throws Exception {
        GuessTheUnits gtu = new GuessTheUnits(5.58, 1764);
        assertEquals(gtu.getUnitType(), "US");
        assertEquals(gtu.getWeight(), 110, 1);
    }

    @Test
    public void shouldThrowExceptionOnDifferentMetrics() {
        try {
            GuessTheUnits gtu = new GuessTheUnits(170, 1764);
            assertEquals(gtu.getUnitType(), "US");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Height and weight is in different metric.");
        }
    }

}