package test;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import sensors.BarcodeScanner;
import sensors.Movement;
import tasks.Bridge;

public class TestBridge {
	public static void main(String[] args) {

		LCD.drawString("Press button to start", 0, 1);
		Button.waitForAnyPress();
		LCD.clear();
		
		Bridge bri = new Bridge();
		
		
		bri.execute();
		BarcodeScanner scanner = new BarcodeScanner(Movement.getInstance());
		scanner.read();
	}
}
