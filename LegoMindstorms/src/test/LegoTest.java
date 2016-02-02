package test;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.utility.Delay;
import tasks.FollowPath;


public class LegoTest {
	public static void main(String[] args) {
		//LCD.drawString("Lego Test", 0, 4);
		
		
		// Fahrtest
		Motor.A.setSpeed(720);
		Motor.B.setSpeed(720);
		//Motor.C.setSpeed(360);
		
		Motor.A.backward();
		Motor.B.backward();
		
		//Motor.C.forward();
		Delay.msDelay(20000);
		//Motor.C.stop();
		//Motor.C.backward();
		//Delay.msDelay(500);
		//Motor.C.stop();
		
		//Delay.msDelay(500);
		
		Motor.A.stop();
		Motor.B.stop();
		
		
		
//		FollowPath test = new FollowPath(null);
//		// Sensortest
//		boolean duration = true;
//		while (duration) {
//			
//			int sensorval = test.getColorSensorValue();
//			LCD.drawInt(sensorval, 4, 4);
//			
//			Delay.msDelay(500);
//			duration = Button.readButtons() == 0;
//		}
	}
	

}

