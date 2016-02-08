package tasks;

import sensors.DistanceSensor;
import main.Main;

public class Swamp extends RegulatedTask {
	public Swamp(Main main) {
		super(main);
		movement.unbowSensor();
		movement.setSpeeds(8.5, 180);
		// first drive a little bit backwards to have enough space and 
		// power to go up on the roles
		movement.travel(-6);
		// then travel 10cm straight forwards; now we can use se sensor
		movement.travel(14);
		movement.setSpeeds(6.5, 180);
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
