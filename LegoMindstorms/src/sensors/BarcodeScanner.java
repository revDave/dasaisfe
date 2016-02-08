package sensors;

import lejos.hardware.lcd.LCD;
import lejos.utility.Stopwatch;

public class BarcodeScanner {
	private ColorSensor cs;
	private boolean changed;
	private int lineCount;
	private Movement mov;

	public BarcodeScanner(Movement m) {
		mov = m;
		cs = new ColorSensor();
		changed = false;
		lineCount = 0;
	}

	public int read() {
		mov.setSpeeds(1, 1337);
		mov.driveForward(); 
		Stopwatch timer = new Stopwatch(); 
		timer.reset(); 
		while (timer.elapsed() < 10000) { 
			float color = cs.getRedSensorValue(); 
			LCD.drawString("Timer: " + timer.elapsed(), 0, 4);
			LCD.drawString("color: " + color, 0, 6);
			LCD.drawString("Changed: " + changed, 1, 3);
			LCD.drawString("lineCount: " + lineCount, 0, 5);
			if (color > .7 && changed) {
				changed = false;
				timer.reset();
				lineCount++;
			} else if (color < .2 && !changed) {
				// do stuff
				changed = true;
			}
		}
		mov.stop();
		return lineCount;
	}

	public void reset() {
		lineCount = 0;
		changed = false;
	}

}
