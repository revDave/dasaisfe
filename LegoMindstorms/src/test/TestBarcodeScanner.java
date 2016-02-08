package test;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import sensors.BarcodeScanner;
import sensors.Movement;

public class TestBarcodeScanner {
	public static void main(String[] args) {
		BarcodeScanner bc = new BarcodeScanner(Movement.getInstance());
		LCD.drawInt(bc.read(), 0, 2);
		Button.waitForAnyPress();
	}
}
