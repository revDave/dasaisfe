package tasks;

import sensors.DistanceSensor;
import main.Main;

public class Swamp extends RegulatedTask {
	public Swamp(Main main) {
		super(main);
		movement.unbowSensor();
		
		movement.travel(10);
	}

	@Override
	public void specificExecute() {
		super.specificExecute();
	}

	@Override
	protected float getSensorValue() {
		float result = DistanceSensor.getInstance().getDistance();
		
		if(result > 0.2) {
			result = getOffset();
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
