package tasks;

import lejos.utility.Delay;
import main.Main;

public class FinalBoss extends Task {
	public FinalBoss(Main main) {
		super(main);
	}

	@Override
	protected void specificExecute() {
		
		movement.bowSensor();
		Delay.msDelay(200);
		movement.unbowSensor();
		
	}
}
