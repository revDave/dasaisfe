package test;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import tasks.FinalBoss;

public class TestFinalBoss {
	public static void main(String[] args) {
		
				
		LCD.drawString("Press button to start", 0, 1);
		Button.waitForAnyPress();
		LCD.clear();
		FinalBoss boss = new FinalBoss();
		boss.execute();


		
		

	}
}
