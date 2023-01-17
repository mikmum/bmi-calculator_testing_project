package com.epam.bootcamp.bmi_calculator;

import static org.junit.Assert.*;

import org.junit.*;

public class GuessTheUnitsTest {

    private static final double METRIC_WEIGHT_IRRELEVANT = 50.0;
    private static final double METRIC_HEIGHT_IRRELEVANT = 170;

    private static final double US_WEIGHT_IRRELEVANT = 1750;
    private static final double US_HEIGHT_IRRELEVANT = 5.5;

    @Test
    public void shouldThrowExceptionOnMetricHeightAndUSWeight() {
        try {
            new GuessTheUnits(METRIC_HEIGHT_IRRELEVANT, US_WEIGHT_IRRELEVANT);
        } catch (Exception e) {
            assertEquals("Height and weight is in different metric.", e.getMessage());
        }
    }

    @Test
    public void shouldThrowExceptionOnUSHeightAndMetricWeight() {
        try {
            new GuessTheUnits(US_HEIGHT_IRRELEVANT, METRIC_WEIGHT_IRRELEVANT);
        } catch (Exception e) {
            assertEquals("Height and weight is in different metric.", e.getMessage());
        }
    }

    @Test
    public void shouldRecognizeMetersAndKilosAsMetric() throws Exception {
        GuessTheUnits gtu = new GuessTheUnits(1.7, 50.0);
        assertEquals("metric", gtu.getUnitType());
    }

    @Test
    public void shouldRecognizeCmsAndKilosAsMetric() throws Exception {
        GuessTheUnits gtu = new GuessTheUnits(170, 50.0);
        assertEquals("metric", gtu.getUnitType());
    }

    @Test
    public void shouldRecognizeFeetAndOuncesAsUS() throws Exception {
        GuessTheUnits gtu = new GuessTheUnits(5.58, 1764);
        assertEquals("US", gtu.getUnitType());
    }

    @Test
    public void shouldRecognizeInchesAndOuncesAsUS() throws Exception {
        GuessTheUnits gtu = new GuessTheUnits(89, 1764);
        assertEquals("US", gtu.getUnitType());
    }

    @Test
    public void shouldCalculateWeightFromOuncesToPoundsCorrectly() throws Exception {
        GuessTheUnits gtu = new GuessTheUnits(US_HEIGHT_IRRELEVANT, 1764);
        assertEquals(110, gtu.getWeight(), 1);
    }

    @Test
    public void shouldNotRecalculateWeightIfGivenInPounds() throws Exception {
        GuessTheUnits gtu = new GuessTheUnits(US_HEIGHT_IRRELEVANT, 110);
        assertEquals(110, gtu.getWeight(), 0);
    }

    @Test
    public void shouldCalculateHeightFromFeetToInchesCorrectly() throws Exception {
        GuessTheUnits gtu = new GuessTheUnits(5.58, US_WEIGHT_IRRELEVANT);
        assertEquals(67, gtu.getHeight(), 1);
    }

    @Test
    public void shouldCalculateHeightFromCmToMetersCorrectly() throws Exception {
        GuessTheUnits gtu = new GuessTheUnits(170, METRIC_WEIGHT_IRRELEVANT);
        assertEquals(1.7, gtu.getHeight(), 0.01);
    }

}