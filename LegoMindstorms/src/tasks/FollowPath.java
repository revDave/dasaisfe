package tasks;

import lejos.utility.Delay;
import lejos.utility.Stopwatch;

public class FollowPath extends RegulatedTask {
	protected double turn = 0;
	protected boolean iAmLost = true;
	protected int minLostTime = 5000;
	protected int currentLostTime = 0;
	protected Stopwatch finderWatch;
	protected Stopwatch lostWatch;
	protected float currentWheelSpeed = 0;
	protected float maxWheelSpeed = 2.5f;

	public FollowPath() {
		finderWatch = new Stopwatch();
		lostWatch = new Stopwatch();
		currentWheelSpeed = maxWheelSpeed;
		currentLostTime = minLostTime;

		findline(false);
	}

	@Override
	protected TaskState specificExecute() {
		findline(true);

		// Get read from sensor
		double sensorValue = getSensorValue();
		if (sensorValue < getLostThreshold()) {
			if (lostWatch.elapsed() > currentLostTime) {
				iAmLost = true;

				// reset pid control
				pid.reset();
				return TaskState.CONTINUE;
			}
		} else {
			lostWatch.reset();
		}

		movement.setSpeeds(currentWheelSpeed, 120);
		currentWheelSpeed = Math.min(maxWheelSpeed, currentWheelSpeed + 0.005f);
		currentLostTime = Math.max(minLostTime, currentLostTime - 10);
		return super.specificExecuteReset();
	}

	protected void findline(boolean reverse) {
		if (iAmLost) {
			if (reverse) {
				movement.quickStop();
				movement.steer(-turn, true);
				Delay.msDelay(currentLostTime * 10 / 15);
				movement.quickStop();
				currentWheelSpeed = wheelSpeed - 2.f;
				currentLostTime = minLostTime + 1500;
			}
			turn = 140;
			movement.setSpeeds(wheelSpeed, 120);
			for (int i = 500; i <= 1000; i = i + 100) {
				turn *= -1;
				finderWatch.reset();
				movement.steer(turn, false);
				while (iAmLost && finderWatch.elapsed() < i) {
					float sensorValue = getSensorValue();
					if (sensorValue >= getLostThreshold()) {
						iAmLost = false;
						lostWatch.reset();
					}
				}
				if (!iAmLost) {
					break;
				}
			}
		}
	}

	@Override
	protected float getSensorValue() {
		return colorSensor.getRedSensorValue();
	}

	@Override
	protected float getOffset() {
		return 0.4f;
	}

	@Override
	protected float getKC() {
		return 666;
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
}
