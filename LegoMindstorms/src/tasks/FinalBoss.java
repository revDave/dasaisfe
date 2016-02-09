package tasks;

import lejos.utility.Delay;

import sensors.ColorSensor;


public class FinalBoss extends Task {
	
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
		if(continueCurrentTask()){
			return TaskState.CONTINUE;
		}
		else{
			movement.travel(-4);
			return TaskState.END;
		}
	}




	// Right direction
	protected boolean invertCompensationDirection() {
		return true;
	}


	@Override
	protected boolean continueCurrentTask() {
		ColorSensor cs = ColorSensor.getInstance();
		float red = cs.getRedSensorValue();
		return !detectLine();
	}
}
