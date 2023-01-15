package com.epam.bootcamp.bmi_calculator;

import static org.junit.Assert.*;

import org.junit.*;

public class BMITest {
	
	App app;
	
	@Before
	public void setup(){
		app = new App();
	}
	
	@Test
	public void shouldReturnThinnessForHeightInMetersAndWeightInKgs() throws Exception{
		app.setHeight(1.7);
		app.setWeight(50);
		assertEquals(app.calculateBMI(),17.3,1);
		assertEquals(app.bmiResult(),"Thinness");
	}
	
	@Test
	public void shouldThrowExceptionForHeightZero() throws Exception{
		try{
			app.setHeight(0);
			app.setWeight(50.0);
			app.calculateBMI();
		}catch(Exception e){
			assertEquals(e.getMessage(),"Height is equals or less than zero.");
		}
	}
	
	@Test
	public void shouldThrowExceptionForNegativeWeight() throws Exception{
		try{
			app.setHeight(1);
			app.setWeight(-3);
			app.calculateBMI();
		}catch(Exception e){
			assertEquals(e.getMessage(),"Weight is equals or less than zero.");
		}
	}
	
	@Test
	public void shouldThrowExceptionForDifferentMetricUsed() throws Exception{
		try{
			app.setHeight(170);
			app.setWeight(1764);
			app.calculateBMI();
		}catch(Exception e){
			assertEquals(e.getMessage(),"Height and weight is in different metric.");
		}
	}
	
	@Test
	public void shouldReturnThinnessForHeightInCmAndWeightInKgs() throws Exception{
		app.setHeight(170);
		app.setWeight(50.0);
		assertEquals(app.calculateBMI(),17.3,1);
		assertEquals(app.bmiResult(),"Thinness");
	}
	
	@Test
	public void shouldReturnThinnessForHeightInInchesAndWeightInOunces() throws Exception{
		app.setHeight(5.58);
		app.setWeight(1764);
		assertEquals(app.calculateBMI(),17.3,1);
		assertEquals(app.bmiResult(),"Thinness");
	}
	
	@Test
	public void shouldReturnCorrectBMIForHeightInInchesAndWeightInPounds() throws Exception{
		app.setHeight(86.7);
		app.setWeight(6349.3);
		assertEquals(app.calculateBMI(),37.19,0.5);
		assertEquals(app.bmiResult(),"Heavily overweight");
	}
	
	@Test
	public void shouldReturnCorrectBMIForHeightInFeetAndWeightInKgs() throws Exception{
		app.setHeight(6.56);
		app.setWeight(220.47);
		assertEquals(app.calculateBMI(),25,0.5);
		assertEquals(app.bmiResult(),"Overweight");
	}
	
	@Test
	public void shouldReturnCorrectBMIForHeightInCmAndWeightInKgs() throws Exception{
		app.setHeight(200);
		app.setWeight(80);
		assertEquals(app.calculateBMI(),20,0.0);
		assertEquals(app.bmiResult(),"Normal");
	}
	
}