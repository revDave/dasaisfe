package test;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import tasks.Bridge;

public class TestBridge {
	public static void main(String[] args) {
		
		Bridge bri = new Bridge();
		
		LCD.drawString("Press button to start", 0, 1);
		Button.waitForAnyPress();
		LCD.clear();
		
		bri.execute();


		
		

	}
}