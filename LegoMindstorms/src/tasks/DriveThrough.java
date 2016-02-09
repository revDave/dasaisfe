package tasks;

import lejos.utility.Delay;
import sensors.ColorSensor;
import sensors.DistanceSensor;

public class DriveThrough extends RegulatedTask {
		// max threshold
		private final float FAR_AWAY_THRESHOLD = 0.16f;	
		//distance to wall
		private final float WALL_THRESHOLD = 0.11f;
		
		public DriveThrough() {
			movement.setSpeeds(9, 180);
			movement.rotateRight(45);
			movement.travel(20);
			movement.rotateLeft(45);
			movement.setSpeeds(4.5, 180);
		}
		
		
		public TaskState specificExecute() {		
			// If the front sensor is pressed, a wall was hit, rotate to the left to
			// dodge the wall and drive further
			if (tactileSensor.frontIsPressed()) {
				movement.travel(-3);
				movement.rotateLeft(80);
				Delay.msDelay(200);
				movement.stop();
			} else {
				super.specificExecute();
			}
			if(continueCurrentTask()){
				return TaskState.CONTINUE;
			}
			else{
				movement.travel(-4);
				return TaskState.END;
			}
		}


		protected float getSensorValue() {
			float result = DistanceSensor.getInstance().getDistance();
			
			// Fit the sensor value, so we don´t get an infinity value
			if(result > FAR_AWAY_THRESHOLD) {
				result = 0.15f;
			}
			
			return result;
		}

		//Distance to wall in meters
		protected float getOffset() {
			return WALL_THRESHOLD;
		}

		//Turn of robot
		protected float getKC() {
			return 2000;
		}

		@Override
		protected float getLostThreshold() {
			return 0;
		}

		// Right direction
		protected boolean invertCompensationDirection() {
			return true;
		}


		@Override
		protected boolean continueCurrentTask() {
			ColorSensor cs = ColorSensor.getInstance();
			float red = cs.getRedSensorValue();
			return !detectLine();
		}
}
