package test;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import tasks.DriveThroughV2;
import tasks.FinalBossV2;

public class TestDriveTroughV2 {
	public static void main(String[] args) {

		
		LCD.drawString("Press button to start", 0, 1);
		Button.waitForAnyPress();
		LCD.clear();
		
		DriveThroughV2 dt = new DriveThroughV2();
		
		dt.execute();
	}
}
