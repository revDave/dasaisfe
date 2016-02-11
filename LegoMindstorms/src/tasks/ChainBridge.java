package tasks;

import sensors.DistanceSensor;
import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;
import main.Main;

public class ChainBridge extends RegulatedTask {
	private boolean driveSlope = true;
	
	
	private float OFFSET_1 = 0.12f;
	private float OFFSET_2 = 0.12f;

	private float offset = OFFSET_1;
	

	private float MAX_SENSOR = 0.20f;

	private ChainBridgeState bridgeState = ChainBridgeState.FOLLOW1;

	private boolean driveForward = false;

	public ChainBridge() {
	}

	@Override
	protected TaskState specificExecute() {
		LCD.drawString("State: " + bridgeState.toString(), 0, 0);
		if (bridgeState.equals(ChainBridgeState.FOLLOW1)) {
			if(getSensorValue() < MAX_SENSOR) {
				bridgeState = ChainBridgeState.SLOPE1;
				movement.stop();
				
				return TaskState.CONTINUE;
			}
			if(!tactileSensor.frontIsPressed()) {
				movement.driveForward();
			} else {
				movement.driveBackward();
				Delay.msDelay(1000);
				movement.stop();
				movement.rotateRight(90);
				movement.driveForward();
			}
			return follow1Behaviour();
		}

		if (bridgeState.equals(ChainBridgeState.SLOPE1)) {
			return slope1Behaviour();
		}

		if (bridgeState.equals(ChainBridgeState.BRIDGE)) {
			return bridgeBehaviour();
		}

		if (bridgeState.equals(ChainBridgeState.SLOPE2)) {
			return slope2Behaviour();
		}

		return TaskState.CONTINUE;

	}

	private TaskState follow1Behaviour() {
		if (getSensorValue() == MAX_SENSOR) {
			//if (followPath != null)
				//followPath.specificExecute();
			
		} else {
			bridgeState = ChainBridgeState.SLOPE1;
		}
		return TaskState.CONTINUE;
	}

	private TaskState slope1Behaviour() {
		if (getSensorValue() == MAX_SENSOR) {
			bridgeState = ChainBridgeState.BRIDGE;
			driveForward = true;
		} else {
			super.specificExecute();
		}
		return TaskState.CONTINUE;
	}

	private TaskState slope2Behaviour() {
		boolean detected = detectLine();
		Delay.msDelay(50);
		
		if (detected) {
			movement.stop();
			movement.travel(-4);
			movement.stop();
			return TaskState.END;

		} else {
			super.specificExecute();
			return TaskState.CONTINUE;
		}
	}

	private TaskState bridgeBehaviour() {
		if (getSensorValue() == MAX_SENSOR) {
			if (driveForward) {
				movement.rotateLeft(10);
				Delay.msDelay(100);
				movement.setSpeeds(5, 100);
				movement.driveForward();
				driveForward = false;
			}
		} else {
			offset = OFFSET_2;
			bridgeState = ChainBridgeState.SLOPE2;
		}

		return TaskState.CONTINUE;
	}

	private float getCorrectSensorValue() {
		return DistanceSensor.getInstance().getDistance();
	}

	@Override
	protected float getSensorValue() {
		float result = getCorrectSensorValue();

		if (result > MAX_SENSOR)
			result = MAX_SENSOR;

		return result;
	}

	@Override
	protected float getOffset() {
		return offset;
	}

	@Override
	protected float getKC() {
		return 650;
	}

	@Override
	protected float getLostThreshold() {
		return 0;
	}

	// Right direction
	protected boolean invertCompensationDirection() {
		return driveSlope;
	}

	@Override
	protected boolean continueCurrentTask() {
		// TODO Auto-generated method stub
		return false;
	}

}