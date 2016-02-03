package tasks;

import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;
import main.Main;
import sensors.DistanceSensor;

public abstract class DriveThrough extends Task {
	private final float BRIDGE_THRESHOLD = 0.01f;
	private final int ROTATION_DEGREES = 20;
	
	public DriveThrough(Main main) {
		super(main);
	}
	
	protected abstract boolean distanceSensorNeeded();

	@Override
	protected void specificExecute() {
		boolean leftPress = tactileSensor.leftIsPressed();
		boolean rightPress = tactileSensor.rightIsPressed();
		
		if(leftPress) {
			movement.rotateRight(ROTATION_DEGREES);
		} else if(rightPress) {
			movement.rotateLeft(ROTATION_DEGREES);
		} else if(!leftPress && !rightPress && distanceSensorNeeded()) {
			float distanceValue = distanceSensor.getDistance();
			
			if(BRIDGE_THRESHOLD < distanceValue) {
				escapeTakeDown();
				LCD.drawString(String.valueOf(distanceValue), 0, 1);
			} else {
				antiEscape();
			}
		} else {
			movement.driveForward();
		}
		
		Delay.msDelay(50);	
		
	}
	
	
	// ultrasonic sensor is bowed to the right
	private void escapeTakeDown() {
		movement.rotateLeft(ROTATION_DEGREES);
		movement.driveForward();
	}
	
	private void antiEscape() {
		movement.rotateRight(ROTATION_DEGREES / 2);
		movement.driveForward();
	}
}
