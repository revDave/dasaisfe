package test;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import tasks.Swamp;

public class TestSwamp {

	public static void main(String[] args) {
	
	Swamp swa = new Swamp();
	
	LCD.drawString("Press button to start", 0, 1);
	Button.waitForAnyPress();
	LCD.clear();
	
	swa.execute();
	}
}
