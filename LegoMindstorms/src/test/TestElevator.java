package test;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import tasks.Elevator;

public class TestElevator {
	public static void main(String[] args) {
		
		Elevator ele = new Elevator();
		
		LCD.drawString("Press button to start", 0, 1);
		Button.waitForAnyPress();
		LCD.clear();
		
		ele.execute();
	}
}
