package com.epam.bootcamp.bmi_calculator;

import static org.junit.Assert.*;

import org.junit.*;

public class BMITest {

    App app;

    @Before
    public void setup() {
        app = new App();
    }

    @Test
    public void shouldCalculateBMICorrectlyForMetrics() throws Exception {
        app.setHeight(1.7);
        app.setWeight(50);
        assertEquals(17.3, app.calculateBMI(), 1);
    }

    @Test
    public void shouldCalculateBMICorrectlyForUSUnits() throws Exception {
        app.setHeight(5.58);
        app.setWeight(1764);
        assertEquals(17.3, app.calculateBMI(), 1);
    }

    @Test
    public void shouldThrowExceptionForHeightZero() {
        try {
            app.setHeight(0);
            app.setWeight(50.0);
            app.calculateBMI();
        } catch (Exception e) {
            assertEquals("Height is equals or less than zero.", e.getMessage());
        }
    }

    @Test
    public void shouldThrowExceptionForNegativeWeight() {
        try {
            app.setHeight(1);
            app.setWeight(-3);
            app.calculateBMI();
        } catch (Exception e) {
            assertEquals("Weight is equals or less than zero.", e.getMessage());
        }
    }

    @Test
    public void shouldThrowExceptionForDifferentMetricUsed() {
        try {
            app.setHeight(170);
            app.setWeight(1764);
            app.calculateBMI();
        } catch (Exception e) {
            assertEquals("Height and weight is in different metric.", e.getMessage());
        }
    }

    @Test
    public void shouldReturnThinnessForAdequateBMI() throws Exception {
        app.setHeight(1.7);
        app.setWeight(50);
        app.calculateBMI();
        assertEquals("Thinness", app.bmiResult());
    }

    @Test
    public void shouldReturnNormalForAdequateBMI() throws Exception {
        app.setHeight(1.7);
        app.setWeight(70);
        app.calculateBMI();
        assertEquals("Normal", app.bmiResult());
    }

    @Test
    public void shouldReturnOverweightForAdequateBMI() throws Exception {
        app.setHeight(1.7);
        app.setWeight(80);
        app.calculateBMI();
        assertEquals("Overweight", app.bmiResult());
    }

    @Test
    public void shouldReturnHeavilyOverweightForAdequateBMI() throws Exception {
        app.setHeight(1.7);
        app.setWeight(95);
        app.calculateBMI();
        assertEquals("Heavily overweight", app.bmiResult());
    }

}