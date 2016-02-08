package sensors;

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
		float color = cs.getRedSensorValue();
		mov.setSpeeds(3, 1337);
		mov.driveForward();
		Stopwatch timer = new Stopwatch();
		timer.reset();
		
		while (timer.elapsed() < 1000) {
			if (color > .7 && changed) {
				changed = false;
				timer.reset();
				lineCount++;
			} else if (color < .2 && changed) {
				// do stuff
				changed = true;
			}
		}
		return lineCount;
	}
	
	public void reset(){
		lineCount = 0;
		changed = false;
	}

}
