package tasks;

import lejos.utility.Delay;
import sensors.ColorSensor;
import sensors.DistanceSensor;

public class FinalBossV2 extends RegulatedTask {
	// max threshold
	private final float FAR_AWAY_THRESHOLD = 0.70f;	
	//distance to wall
	private final float WALL_THRESHOLD = 0.50f;
	boolean pink = false;
	float valval = 0.0f;
	
	public FinalBossV2() {
		movement.setSpeeds(9, 180);
		movement.travel(10);
		movement.rotateRight(45);
		movement.travel(20);
		movement.rotateLeft(60);
		movement.setSpeeds(4.5, 180);
	}
	
	@Override
	protected TaskState specificExecute() {
		// If the front sensor is pressed, the final boss was hit, 
		// wait 3 seconds and drives again straight forward
		if (tactileSensor.frontIsPressed()) {
			movement.travel(-3);
			movement.stop();
			Delay.msDelay(3000);
		} else {
			super.specificExecute();

		}
		valval = colorSensor.getColorSensorValue();
		if (valval == 2.0) {
			movement.quickStop();
			movement.travel(5);
			return TaskState.KILL;
		} else {
			return TaskState.CONTINUE;
		}

	}

	protected float getSensorValue() {
		float result = DistanceSensor.getInstance().getDistance();
		
		// Fit the sensor value, so we don´t get an infinity value
		if(result > FAR_AWAY_THRESHOLD) {
			result = 0.65f;
		}
		
		return result;
	}
	
	//Distance to wall in meters
	protected float getOffset() {
		return WALL_THRESHOLD;
	}

	//Turn of robot
	protected float getKC() {
		return 10;
	}

	@Override
	protected float getLostThreshold() {
		return 0;
	}



	// Right direction
	protected boolean invertCompensationDirection() {
		return true;
	}


	@Override
	protected boolean continueCurrentTask() {
		ColorSensor cs = ColorSensor.getInstance();
		float red = cs.getRedSensorValue();
		return false;
	}
}
