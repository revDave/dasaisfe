package tasks;

import lejos.utility.Delay;

import sensors.ColorSensor;



public class FinalBoss extends RegulatedTask {
	boolean pink = false;
	public FinalBoss() {
		
	}
	
	@Override
	protected TaskState specificExecute() {
		
		movement.setSpeeds(9, 180);
		movement.driveForward();
		// If the front sensor is pressed, the final boss was hit, 
		// wait 3 seconds and drives again straight forward
		if (tactileSensor.frontIsPressed()) {
			movement.travel(-3);
			movement.stop();
			Delay.msDelay(3000);
		} else {
			movement.setSpeeds(9, 180);
			movement.driveForward();
		}
		if (pink == true) {
			movement.stop();
			return TaskState.KILL;
		}
		if(continueCurrentTask()){
			return TaskState.CONTINUE;
		}
		else{
			movement.travel(-4);
			return TaskState.END;
		}
	}

	protected float getSensorValue() {
		float val = colorSensor.getColorSensorValue();
		
		if (val == 2.0) {
			pink = true;
		}
		return val;
	}
	
	@Override
	protected float getOffset() {
		return 0.1f;
	}

	@Override
	protected float getKC() {
		return 999;
	}

	@Override
	protected double getIFactor() {
		return 0.05;
	}
	
	@Override
	protected double getDFactor() {
		return 50;
	}

	@Override
	protected float getLostThreshold() {
		return 0.15f;
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
