package test;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import tasks.DriveThrough;
import tasks.Labyrinth;

public class TestDriveThrough {
	public static void main(String[] args) {
		
		
		
		LCD.drawString("Press button to start", 0, 1);
		Button.waitForAnyPress();
		LCD.clear();
		
		DriveThrough drive = new DriveThrough();
		
		drive.execute();


		
		

	}
}
