package tasks;

import sensors.DistanceSensor;
import lejos.hardware.lcd.LCD;
import main.Main;

public class ChainBridge extends Task {
	private DistanceSensor sensor = null;
	private float BRIDGE_THRESHOLD = 0.01f;
	
	public ChainBridge(Main main) {
		super(main);
		sensor = new DistanceSensor();
	}

	@Override
	protected void specificExecute() {
		boolean leftPress = false;
		boolean rightPress = false;
		
		if(leftPress) {
			movement.rotateRight(20);
		}
		
		if(rightPress) {
			movement.rotateLeft(20);
		}
		
		// TODO getting distance correctly
		if(!leftPress && !rightPress) {
			float distanceValue = 0.0f;//sensor.getDistance(distance)
			
			if(BRIDGE_THRESHOLD < distanceValue) {
				escapeTakeDown();
				LCD.drawString(String.valueOf(distanceValue), 0, 1);
			}
		}
		
	}
	
	// ultrasonic sensor is bowed to the right
	private void escapeTakeDown() {
		movement.rotateLeft(20);
	}
}
