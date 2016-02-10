package test;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import tasks.Swamp;

public class TestSwamp {

	public static void main(String[] args) {
	
	
	
	LCD.drawString("Press button to start", 0, 1);
	Button.waitForAnyPress();
	LCD.clear();
	Swamp swa = new Swamp();
	swa.execute();
	}
}
