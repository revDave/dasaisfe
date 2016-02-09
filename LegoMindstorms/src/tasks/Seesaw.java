package tasks;

import lejos.utility.Delay;
import lejos.utility.Stopwatch;
import main.Main;

public class Seesaw extends FollowPath{
	public Seesaw(){
		maxWheelSpeed = 4.f;
		currentWheelSpeed = maxWheelSpeed;
	}


	@Override
	protected float getKC() {
		return 400;
	}
}

