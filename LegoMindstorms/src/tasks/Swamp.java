package tasks;

import sensors.DistanceSensor;
import main.Main;

//funcs stabil, if we have time: optimize speed and distance
public class Swamp extends RegulatedTask {
	private final float WALL_THRESHOLD = 0.08f;
	private final float FAR_AWAY_THRESHOLD = 0.16f;
	
	public Swamp() {
		movement.unbowSensor();
		movement.setSpeeds(9, 180);
		// first drive backwards to have enough space and 
		// power to go up on the roles
		//movement.travel(-9);
		// then travel 10cm straight forwards; now we can use se sensor
		movement.travel(11);
		movement.stop();
		movement.setSpeeds(6.5, 180);
	}

	@Override
	public TaskState specificExecute() {
		return super.specificExecute();
	}

	@Override
	protected float getSensorValue() {
		float result = DistanceSensor.getInstance().getDistance();
		
		if(result > FAR_AWAY_THRESHOLD) {
			result = 0.15f;
		}
		
		return result;
	}

	@Override
	protected float getOffset() {
		return WALL_THRESHOLD;
	}

	@Override
	protected float getKC() {
		return 2000;
	}

	@Override
	protected float getLostThreshold() {
		return 0;
	}

	@Override
	protected boolean continueCurrentTask() {
		// TODO Auto-generated method stub
		return false;
	}

	// Right direction
	protected boolean invertCompensationDirection() {
		return true;
	}

}
