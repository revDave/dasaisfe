package test;

import sensors.DistanceSensor;
import sensors.Movement;
import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;

public class TestDistance {
	public static void main(String[] args) {
		Movement.getInstance().bowSensor();
		while(true) {
			LCD.drawString(Float.toString(DistanceSensor.getInstance().getDistance()), 0, 1);
			Delay.msDelay(100);
		}
	}

}
