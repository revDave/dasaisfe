package tasks;

import sensors.DistanceSensor;
import main.Main;

//funcs stabil, if we have time: optimize speed and distance
public class Swamp extends RegulatedTask {
	public Swamp() {
		movement.unbowSensor();
		movement.setSpeeds(9, 180);
		// first drive backwards to have enough space and 
		// power to go up on the roles
		movement.travel(-9);
		// then travel 10cm straight forwards; now we can use se sensor
		movement.travel(20);
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
