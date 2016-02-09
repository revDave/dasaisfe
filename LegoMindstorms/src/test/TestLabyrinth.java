package test;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import tasks.Labyrinth;

public class TestLabyrinth {
	public static void main(String[] args) {
		
		Labyrinth lab = new Labyrinth();
		
		LCD.drawString("Press button to start", 0, 1);
		Button.waitForAnyPress();
		LCD.clear();
		
		lab.execute();


		
		

	}
}
