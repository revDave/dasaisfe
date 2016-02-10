package test;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import tasks.FollowPath;

public class TestSeesaw {
	public static void main(String[] args) {
		
		
		LCD.drawString("Press button to start", 0, 1);
		Button.waitForAnyPress();
		LCD.clear();
		FollowPath ss = new FollowPath();
		
		ss.execute();
	}
}
