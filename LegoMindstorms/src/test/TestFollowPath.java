package test;

import tasks.FollowPath;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.LCD;
import lejos.hardware.Button;
import lejos.hardware.Key;
import lejos.hardware.KeyListener;

public class TestFollowPath extends TestBase{
	public static void main(String[] args) {
		LCD.drawString("CYKA BLYAT!", 0, 1);
		LCD.drawString("CYKA BLYAT!", 2, 2);
		LCD.drawString("CYKA BLYAT!", 4, 3);
		LCD.drawString("CYKA BLYAT!", 6, 4);
		
		LCD.drawString("Press button to start", 0, 1);
		Button.waitForAnyPress();
		LCD.clear();
		
		
		FollowPath fp = new FollowPath();
		
		fp.execute();
	}
}
