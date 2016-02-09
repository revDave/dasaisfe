package sensors;

import lejos.hardware.lcd.LCD;
import lejos.utility.Stopwatch;
import main.ParkourStage;

public class BarcodeScanner {
	protected static final double lowThreshold = .2;
	protected static final double highThreshold = .5;
	private ColorSensor cs;
	private boolean changed;
	private int lineCount;
	private Movement mov;

	public BarcodeScanner(Movement m) {
		mov = m;
		cs = ColorSensor.getInstance();
		changed = false;
		lineCount = 0;
	}

	public ParkourStage read() {
		mov.setSpeeds(1, 1337);
		mov.driveForward(); 
		Stopwatch timer = new Stopwatch(); 
		timer.reset(); 
		while (timer.elapsed() < 3500) { 
			float color = cs.getRedSensorValue(); 
			LCD.drawString("Timer: " + timer.elapsed(), 0, 4);
			LCD.drawString("color: " + color, 0, 6);
			LCD.drawString("Changed: " + changed, 1, 3);
			LCD.drawString("lineCount: " + lineCount, 0, 5);
			if (color > highThreshold && changed) {
				changed = false;
				timer.reset();
				lineCount++;
			} else if (color < lowThreshold && !changed) {
				// do stuff
				changed = true;
			}
		}
		mov.stop();
		
		return mapLineCountToStage(lineCount);
	}

	public void reset() {
		lineCount = 0;
		changed = false;
	}
	
	private ParkourStage mapLineCountToStage(int lineCount){
		switch (lineCount) {
		case 1:
			return ParkourStage.IDLE;
		case 2:
			return ParkourStage.FOLLOWPATH;
		case 3:
			return ParkourStage.BRIDGE;
		case 4:
			return ParkourStage.SEESAW;
		case 5:
			return ParkourStage.CHAINBRIDGE;
		case 6:
			return ParkourStage.BOSS;
		default:
			return ParkourStage.IDLE;
		}
	}

}
