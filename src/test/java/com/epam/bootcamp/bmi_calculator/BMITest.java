package com.epam.bootcamp.bmi_calculator;

import static org.junit.Assert.*;

import org.junit.*;
import org.mockito.internal.util.reflection.Whitebox;
import com.epam.bootcamp.bmi_calculator.interfaces.UnitsInterface;

public class BMITest {

    App app;

    private static final double METRIC_WEIGHT_IRRELEVANT = 50.0;
    private static final double METRIC_HEIGHT_IRRELEVANT = 170;

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
        app.setHeight(0);
        app.setWeight(50.0);
        assertThrows("Height is equals or less than zero.", Exception.class, () -> app.calculateBMI());
    }

    @Test
    public void shouldThrowExceptionForNegativeHeight() {
        app.setHeight(-1);
        app.setWeight(50.0);
        assertThrows("Height is equals or less than zero.", Exception.class, () -> app.calculateBMI());
    }

    @Test
    public void shouldThrowExceptionForNegativeWeight() {
        app.setHeight(1);
        app.setWeight(-3);
        assertThrows("Height is equals or less than zero.", Exception.class, () -> app.calculateBMI());
    }

    @Test
    public void shouldThrowExceptionForWeightZero() {
        app.setHeight(170);
        app.setWeight(0);
        assertThrows("Height is equals or less than zero.", Exception.class, () -> app.calculateBMI());
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

    /**
     * Overall I thought about power mocking BMI values for these tests f.e with
     * {@link Whitebox#setInternalState(Object, String, Object)}, as they should be checking if numerical BMI is
     * adequately mapped to String values, but I'm not a fan of either using reflections if not <b>really</b>
     * necessary as well as framework's internal methods...
     * On the other hand, if something was to fail with height to weight calculation it should ideally only fail the
     * test regarding calculation, not the ones below.
     * <p>
     * Code smell either way and refactoring candidate, but the point of the project isn't to polish the production
     * part of the code, so I'll leave it as is.
     */
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

    @Test
    public void shouldReturnAdequateBMIForEdgeCases() throws Exception {
        app.setHeight(100);
        app.setWeight(18.5);
        app.calculateBMI();
        assertEquals("Normal", app.bmiResult());

        app.setWeight(24.9);
        app.calculateBMI();
        assertEquals("Normal", app.bmiResult()); //this test is there for consistency, but does nothing regarding
        // mutations - as mentioned, check on bmi > 24.9 is dead code due to evaluation in previous else-if

        app.setWeight(29.9);
        app.calculateBMI();
        assertEquals("Overweight", app.bmiResult());
    }
}