package test;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import tasks.FinalBossV2;

public class TestV2 {
	public static void main(String[] args) {

	
		LCD.drawString("Press button to start", 0, 1);
		Button.waitForAnyPress();
		LCD.clear();
		
		FinalBossV2 bo = new FinalBossV2();
		
		bo.execute();
	}
}
