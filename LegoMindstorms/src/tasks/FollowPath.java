package tasks;

import sensors.DistanceSensor;
import main.Main;
import lejos.hardware.lcd.LCD;
import control.modules.PID_Control;
import lejos.hardware.Button;
import lejos.utility.Stopwatch;

public class FollowPath extends RegulatedTask {
	private double turn = 0;
	private boolean iAmLost = true;
	private int maxLostTime = 300;
	private Stopwatch finderWatch;
	private Stopwatch lostWatch;

	public FollowPath(Main main) {
		super(main);

		finderWatch = new Stopwatch();
		lostWatch = new Stopwatch();
		
		findline(false);
	}

	@Override
	protected void specificExecute() {
		findline(true);

		// Get read from sensor
		double sensorValue = getSensorValue();
		if (sensorValue < getLostThreshold()) {
			if (lostWatch.elapsed() > maxLostTime) {
				iAmLost = true;

				// reset pid control
				pid.reset();
				return;
			}
		} else {
			lostWatch.reset();
		}

		super.specificExecute();
	}

	protected void findline(boolean reverse) {
		if (iAmLost) {
			turn = 160;
			for (int i = 200; i <= 1000; i = i + 100) {
				turn *= -1;
				finderWatch.reset();
				movement.steer(turn, reverse);
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
		return 0.5f;
	}

	@Override
	protected float getKC() {
		return 300;
	}

	@Override
	protected float getLostThreshold() {
		return 0.15f;
	}
}
