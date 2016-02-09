package tasks;

import lejos.utility.Delay;
import main.Main;

public class FinalBoss extends Task {

	@Override
	protected TaskState specificExecute() {
		movement.bowSensor();
		Delay.msDelay(200);
		movement.unbowSensor();
		return TaskState.CONTINUE;
	}

	@Override
	protected boolean continueCurrentTask() {
		// TODO Auto-generated method stub
		return false;
	}
}
