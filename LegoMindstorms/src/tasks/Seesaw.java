package tasks;

import lejos.utility.Delay;
import lejos.utility.Stopwatch;
import main.Main;
import sensors.Movement;

public class Seesaw extends FollowPath{
	public Seesaw(){
		maxWheelSpeed = 4.f;
		currentWheelSpeed = maxWheelSpeed;
		Movement.getInstance().travel(15);
		Movement.getInstance().rotateLeft(15);
	}


	@Override
	protected float getKC() {
		return 400;
	}
}

