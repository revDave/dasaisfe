package tasks;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;
import lejos.utility.Stopwatch;
import sensors.DistanceSensor;

public class FollowPath extends RegulatedTask {
	protected double turn = 0;
	protected boolean iAmLost = true;
	protected int minLostTime = 1800;
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
				movement.quickStop();
				iAmLost = true;
				LCD.drawString("IAM FUCKING LOST!", 0, 2);
				LCD.drawString("ON A FUCKING ISLAND!", 0, 3);
				LCD.drawString("LIKE IN THIS TV SHOW!", 0, 4);
				
				LCD.drawString("Press button to start", 0, 1);
				Button.waitForAnyPress();
				LCD.clear();
				
				movement.steer(200,false);
				lostWatch.reset();
				while(lostWatch.elapsed() < 3400 && iAmLost){
					if(detectLine()){
						iAmLost = false;
					}
				}
				if(iAmLost){
					movement.rotateLeft(90);
					return TaskState.END;
				}
				movement.quickStop();
				
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
		DistanceSensor ds = DistanceSensor.getInstance();
		if (iAmLost) {
			if (ds.getDistance() < 0.17) {
				movement.rotateLeft(40);
				movement.travel(4);
			}
			if (reverse) {
				movement.quickStop();
				movement.steer(-turn, true);
				Delay.msDelay(currentLostTime * 10 / 15);
				movement.quickStop();
				currentWheelSpeed = wheelSpeed - 2.f;
				currentLostTime = minLostTime + 1500;
			}
			turn = 100;
			movement.setSpeeds(wheelSpeed, 120);
			for (int i = 1000; i <= 2000; i = i + 200) {
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
		return 0.25f;
	}

	@Override
	protected float getKC() {
		return 699;
	}

	@Override
	protected double getIFactor() {
		return 0.2;
	}

	@Override
	protected double getDFactor() {
		return 30;
	}

	@Override
	protected float getLostThreshold() {
		return 0.15f;
	}

	@Override
	protected boolean continueCurrentTask() {
		// TODO Auto-generated method stub
		return false;
	}
}
