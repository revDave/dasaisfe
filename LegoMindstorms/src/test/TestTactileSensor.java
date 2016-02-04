package test;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.utility.Delay;
import main.Main;
import sensors.ColorSensor;
import sensors.Movement;
import sensors.TactileSensor;
import tasks.FollowPath;
import tasks.Labyrinth;
import lejos.robotics.navigation.DifferentialPilot;

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
	