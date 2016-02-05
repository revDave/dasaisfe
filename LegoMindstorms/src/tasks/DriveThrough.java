package tasks;

import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;
import main.Main;
import sensors.DistanceSensor;

public abstract class DriveThrough extends Task {
	private final float BRIDGE_THRESHOLD = 0.05f;
	private final int ROTATION_DEGREES = 20;
	
	public DriveThrough(Main main) {
		super(main);
		movement.setSpeeds(10, 10);
	}
	
	protected abstract boolean distanceSensorNeeded();

	@Override
	protected void specificExecute() {
		boolean leftPress = tactileSensor.sideIsPressed();
		//boolean rightPress = tactileSensor.rightIsPressed();
		
		float distanceValue = distanceSensor.getDistance();
		
//		if(leftPress) {
//			movement.rotateRight(ROTATION_DEGREES);
//		} else if(rightPress) {
//			movement.rotateLeft(ROTATION_DEGREES);
//		} else if(!leftPress && !rightPress && distanceSensorNeeded()) {			
//			if(BRIDGE_THRESHOLD < distanceValue) {
//				escapeTakeDown();
//			} else {
//				antiEscape();
//			}
//		} else {
//			movement.driveForward();
//		}
		LCD.drawString(String.valueOf(distanceValue), 0, 1);
		Delay.msDelay(500);	
		
	}
	
	
	// ultrasonic sensor is bowed to the right
	private void escapeTakeDown() {
		movement.rotateRight(ROTATION_DEGREES);
		movement.driveForward();
	}
	
	private void antiEscape() {
		movement.rotateLeft(ROTATION_DEGREES / 2);
		movement.driveForward();
	}
}
