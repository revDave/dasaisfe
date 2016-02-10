package tasks;

import sensors.DistanceSensor;
import main.Main;

public class Bridge extends RegulatedTask {
	public Bridge() {
		movement.setSpeeds(6, 180);
		movement.bowSensor();
		movement.rotateLeft(2);
	}
	
	@Override
	public TaskState specificExecute() {
		super.specificExecute();
		if(continueCurrentTask()){
			return TaskState.CONTINUE;
		} else {
			movement.quickStop();
			movement.unbowSensor();
			Elevator e = new Elevator();
			return e.specificExecute();
		}
	}

	@Override
	protected float getSensorValue() {
		float result = DistanceSensor.getInstance().getDistance();
		
		if(result > 0.25) {
			result = 0.25f;
		}
		
		return result;
	}

	@Override
	protected float getOffset() {
		return 0.07f;
	}

	@Override
	protected float getKC() {
		return 650;
	}

	@Override
	protected float getLostThreshold() {
		return 0;
	}

	@Override
	protected boolean continueCurrentTask() {
		return !detectLine();
	}

}
