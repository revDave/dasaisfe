package test;

import tasks.Labyrinth;


public class TestTactileSensor {
	public static void main(String[] args) {
	
	    //TactileSensor tactileSensor = null;
		//tactileSensor = new TactileSensor();
		
		//LCD.drawString("Lego Test", 0, 4);
		//Delay.msDelay(5000);
		
		//Sensortest
		//boolean duration = true;
//		while (duration) {
//
//			//LCD.drawString(String.valueOf(tactileSensor.rightIsPressed()), 0 , 4);
//			//LCD.drawString(String.valueOf(tactileSensor.leftIsPressed()), 0 , 5);
//			Delay.msDelay(500);
//			duration = Button.readButtons() == 0;
//		}
		
		Labyrinth lab = new Labyrinth(null);
		
		lab.execute();


		
		

	}
}
	