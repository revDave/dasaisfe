package tasks;

import lejos.utility.Delay;
import lejos.utility.Stopwatch;
import main.Main;
import sensors.Movement;

public class Seesaw extends FollowPath{
	public Seesaw(){
		maxWheelSpeed = 5.f;
		currentWheelSpeed = maxWheelSpeed;
	}

	@Override
	protected TaskState specificExecute() {
		Movement.getInstance().travel(15);
		Movement.getInstance().rotateLeft(5);
		return super.specificExecute();
		
	}

	@Override
	protected float getKC() {
		return 699;
	}
}

