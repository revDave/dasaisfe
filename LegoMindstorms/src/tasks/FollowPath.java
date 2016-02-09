package tasks;

import sensors.DistanceSensor;
import main.Main;
import lejos.hardware.lcd.LCD;
import control.modules.PID_Control;
import lejos.hardware.Button;
import lejos.utility.Delay;
import lejos.utility.Stopwatch;

public class FollowPath extends RegulatedTask {
	private double turn = 0;
	private boolean iAmLost = true;
	private int minLostTime = 5000;
	private int currentLostTime = 0;
	private Stopwatch finderWatch;
	private Stopwatch lostWatch;
	private float currentWheelSpeed = 0;

	public FollowPath() {
		finderWatch = new Stopwatch();
		lostWatch = new Stopwatch();
		currentWheelSpeed = wheelSpeed;
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
		currentWheelSpeed = Math.min(wheelSpeed, currentWheelSpeed + 0.005f);
		currentLostTime = Math.max(minLostTime, currentLostTime - 10);
		return super.specificExecute();
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
		int numSamples = 3;
		float result = 0;
		for (int i = 0; i < numSamples; i++) {
			result += colorSensor.getRedSensorValue();
		}

		return result / numSamples;
	}

	@Override
	protected float getOffset() {
		return 0.55f;
	}

	@Override
	protected float getKC() {
		return 666;
	}

	@Override
	protected double getIFactor() {
		return 0.;
	}
	
	@Override
	protected double getDFactor() {
		return 10;
	}

	@Override
	protected float getLostThreshold() {
		return 0.15f;
	}
}
