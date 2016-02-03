package tasks;

import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;
import main.Main;
import sensors.DistanceSensor;

public abstract class DriveThrough extends Task {
	private float BRIDGE_THRESHOLD = 0.01f;
	
	public DriveThrough(Main main) {
		super(main);
	}
	
	protected abstract boolean distanceSensorNeeded();

	@Override
	protected void specificExecute() {
		boolean leftPress = false;
		boolean rightPress = false;
		
		if(leftPress) {
			movement.rotateRight(20);
		} else if(rightPress) {
			movement.rotateLeft(20);
		}
		
		else if(!leftPress && !rightPress && distanceSensorNeeded()) {
			float distanceValue = distanceSensor.getDistance();
			
			if(BRIDGE_THRESHOLD < distanceValue) {
				escapeTakeDown();
				LCD.drawString(String.valueOf(distanceValue), 0, 1);
			}
		} else {
			movement.driveForward();
		}
		
		Delay.msDelay(300);	
		
	}
	
	// ultrasonic sensor is bowed to the right
	private void escapeTakeDown() {
		movement.rotateLeft(20);
	}
}
