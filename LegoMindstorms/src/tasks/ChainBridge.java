package tasks;

import sensors.DistanceSensor;
import lejos.utility.Delay;
import main.Main;

public class ChainBridge extends RegulatedTask {

	private int counter = 0;

	private boolean driveSlope = true;

	private final float OFFSET_SLOPE = 0.08f;
	private final float OFFSET_BRIDGE = 0.04f;

	private float offset = OFFSET_SLOPE;

	private float MAX_SENSOR = 0.7f;

	public ChainBridge(Main main) {
		super(main);

	}

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
	
	public void specificExecute() {
		if (getSensorValue() >= MAX_SENSOR) {
			movement.bowSensor();

			offset = OFFSET_BRIDGE;
			driveSlope = false;

		} else if (getSensorValue() < 0.04 && !driveSlope) {
			offset = OFFSET_SLOPE;
			driveSlope = true;
		} else {
			super.specificExecute();
		}

	}

	@Override
	protected float getSensorValue() {
		float result = DistanceSensor.getInstance().getDistance();

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
		return 800;
	}

	@Override
	protected float getLostThreshold() {
		return 0;
	}
	// Right direction
	protected boolean invertCompensationDirection() {
		return !driveSlope;
	}

}