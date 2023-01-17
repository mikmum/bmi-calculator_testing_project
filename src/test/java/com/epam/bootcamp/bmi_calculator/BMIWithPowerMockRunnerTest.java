package com.epam.bootcamp.bmi_calculator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.powermock.api.mockito.PowerMockito.whenNew;

//needs to be run with --add-opens java.base/java.lang=ALL-UNNAMED
@RunWith(PowerMockRunner.class)
@PowerMockIgnore("jdk.internal.reflect.*")
@PrepareForTest(App.class)
public class BMIWithPowerMockRunnerTest {
    /**
     * In that scenario the production code should probably throw an exception instead but welp...
     */
    @Test
    public void shouldReturnBMIZeroIfNotRecognizedMetric() throws Exception {
        GuessTheUnits gtu = Mockito.mock(GuessTheUnits.class);
        when(gtu.getUnitType()).thenReturn("Nonexistent metric");
        whenNew(GuessTheUnits.class).withAnyArguments().thenReturn(gtu);

        App app = new App();
        app.setHeight(150);
        app.setWeight(60);

        try {
            assertEquals(0, app.calculateBMI(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
