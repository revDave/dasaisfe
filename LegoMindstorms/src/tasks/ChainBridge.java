package tasks;

import sensors.DistanceSensor;
import lejos.utility.Delay;
import main.Main;

public class ChainBridge extends RegulatedTask {

	public ChainBridge(Main main) {
		super(main);
		movement.bowSensor();

	}

	@Override
	public void specificExecute() {

		if (tactileSensor.sideIsPressed()) {
			movement.rotateRight(30);
			movement.driveForward();
			Delay.msDelay(300);
			movement.stop();
		} else {
			super.specificExecute();
		}
	}

	@Override
	protected float getSensorValue() {
		float result = DistanceSensor.getInstance().getDistance();

		if (result > 0.6) {
			result = 0.6f;
		}

		return result;
	}

	@Override
	protected float getOffset() {
		return 0.08f;
	}

	@Override
	protected float getKC() {
		return 650;
	}

	@Override
	protected float getLostThreshold() {
		return 0;
	}

}