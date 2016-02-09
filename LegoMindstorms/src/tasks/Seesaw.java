package tasks;

import lejos.utility.Delay;
import lejos.utility.Stopwatch;
import main.Main;

public class Seesaw extends FollowPath{
	private double turn = 0;
	private boolean iAmLost = true;
	private int minLostTime = 1300;
	private int currentLostTime = 0;
	private Stopwatch finderWatch;
	private Stopwatch lostWatch;
	private float currentWheelSpeed = 0;

//	@Override
//	public void specificExecute() {
//		super.specificExecute();
//	}
//
//	@Override
//	protected float getSensorValue() {
//		float result = DistanceSensor.getInstance().getDistance();
//		
//		if(result > 0.6) {
//			result = 0.6f;
//		}
//		
//		return result;
//	}
//
//	@Override
//	protected float getOffset() {
//		return 0.08f;
//	}
//
//	@Override
//	protected float getKC() {
//		return 650;
//	}
//
//	@Override
//	protected float getLostThreshold() {
//		return 0;
//	}

}

