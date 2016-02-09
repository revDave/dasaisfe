package tasks;

import sensors.DistanceSensor;
import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;
import main.Main;

public class ChainBridge extends RegulatedTask {
	private boolean driveSlope = true;

	private final float OFFSET_SLOPE = 0.15f;
	private final float OFFSET_BRIDGE = 0.04f;

	private float offset = OFFSET_SLOPE;

	private float MAX_SENSOR = 0.28f;
	
	private TaskState state = TaskState.CONTINUE;

	public void specificExecute1() {
		if (tactileSensor.frontIsPressed()) {
			movement.bowSensor();

			offset = OFFSET_BRIDGE;
			driveSlope = false;

		} else if (getSensorValue() < 0.08 && !driveSlope) {
			offset = OFFSET_SLOPE;
			driveSlope = true;
		} else {
			super.specificExecute();
		}
	}
	
	public TaskState specificExecute() {
		if (getCorrectSensorValue() > MAX_SENSOR) {
			LCD.clearDisplay();
			LCD.drawString(Float.toString(getCorrectSensorValue()), 0,1);
			movement.stop();
			movement.bowSensor();
			movement.rotateLeft(10);
			Delay.msDelay(500);
			movement.driveForward();
			Delay.msDelay(6000);
			movement.stop();
			offset = OFFSET_BRIDGE;
			driveSlope = false;

		} else if (getCorrectSensorValue() < 0.06 && !driveSlope) {
			offset = OFFSET_SLOPE;
			driveSlope = true;

			movement.stop();
			movement.unbowSensor();
			movement.driveForward();
			Delay.msDelay(4000);
			movement.stop();
		} else {
			super.specificExecute();
		}
		
		return state;

	}
	
	private float getCorrectSensorValue() {
		return DistanceSensor.getInstance().getDistance();
	}

	@Override
	protected float getSensorValue() {
		float result = getCorrectSensorValue();

		if (result > MAX_SENSOR)
			result = MAX_SENSOR;

		return result;
	}

	@Override
	protected float getOffset() {
		return offset;
	}

	@Override
	protected float getKC() {
		return 650;
	}

	@Override
	protected float getLostThreshold() {
		return 0;
	}
	
	// Right direction
	protected boolean invertCompensationDirection() {
		return driveSlope;
	}

	@Override
	protected boolean continueCurrentTask() {
		// TODO Auto-generated method stub
		return false;
	}

}