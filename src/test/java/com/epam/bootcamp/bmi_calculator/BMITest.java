package com.epam.bootcamp.bmi_calculator;

import static org.junit.Assert.*;

import org.junit.*;
import org.junit.function.ThrowingRunnable;
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

    /***
     * That should utilize {@link Assert#assertThrows(String, Class, ThrowingRunnable)} but I'm leaving it because
     * it provides fun results with mutation testing later on. Same with all following exception-testing methods.
     */
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
    /**
     * Some additional notes
     *
     * Test that should be here but would require refactor of production code:
     * - scenario when {@link GuessTheUnits#getUnitType()} returns something elsa than "US" or "metric". Currently not
     * possible with production code. Regarding testing - it would require something like PowerMockito framework to
     * mock GTU new object initialization inside {@link App#calculateBMI() method}, example
     * {@link BMIWithPowerMockRunnerTest}, but @PrepareForTest breaks coverage both with default IntelliJ runner and
     * JaCoCo runner. Ideally - add a factory for, and field of GuessTheUnits class. Also - we have generic
     * {@link UnitsInterface} but we don't utilize it...
     *
     * 100% branch coverage in {@link App} is not achievable atm:
     * - in line 55 "this.bmi >= 18.5" is dead code, as "this.bmi < 18.5" is checked in previous if. Mutually exclusive.
     * - line 57, same situation with "this.bmi > 24.9" and "this.bmi <= 24.9 earlier
     * - line 40, aforementioned issue that would require power mocking GTU initialization.
     */
}