package test;

import tasks.FollowPath;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.LCD;
import sensors.BarcodeScanner;
import sensors.Movement;
import lejos.hardware.Button;
import lejos.hardware.Key;
import lejos.hardware.KeyListener;

public class TestFollowPath extends TestBase{
	public static void main(String[] args) {
		
		LCD.drawString("Press button to start", 0, 1);
		Button.waitForAnyPress();
		LCD.clear();
		
		
		FollowPath fp = new FollowPath();
		
		fp.execute();

		BarcodeScanner scanner = new BarcodeScanner(Movement.getInstance());
		scanner.read();
	}
}
