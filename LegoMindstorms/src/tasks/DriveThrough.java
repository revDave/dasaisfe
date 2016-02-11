package tasks;

import lejos.utility.Delay;
import sensors.ColorSensor;
import sensors.DistanceSensor;

public class DriveThrough extends RegulatedTask {
	// max threshold
	private float farAwayThres = 0.16f;	
	//distance to wall
	private float wallThres = 0.11f;
	private float offsetVal = 0.15f;
	boolean pink = true;
	//first time for detecting pink line
	float val = 0.0f;
	//second time for detecting pink line
	float val2 = 0.0f;
	boolean rosa = false;
	boolean endboss = false;
	float valval = 0.0f;
	
	public DriveThrough() {
		movement.setSpeeds(20, 180);
		movement.rotateRight(45);
		movement.travel(20);
		movement.rotateLeft(60);
		movement.setSpeeds(20, 180);
		movement.travel(150);
		movement.setSpeeds(4.5, 180);
	}
	
	
	public TaskState specificExecute() {	
		if (!rosa && !endboss) {
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
//			if(continueCurrentTask()){
//				return TaskState.CONTINUE;
//			}
//			else{
//				movement.travel(-4);
//				return TaskState.END;
//			}
			
			// todo �bergang
			
			val = colorSensor.getColorSensorValue();
		
			if (val == 2.0) {
				rosa = true;
				movement.travel(5);
			} 
			return TaskState.CONTINUE;
			
		} else if (rosa && !endboss){
				movement.rotateRight(20);
				movement.setSpeeds(15, 180);
				movement.travel(17);
				movement.quickStop();
				movement.travel(-17);
				movement.quickStop();
				Delay.msDelay(4000);
				movement.travel(50);
				movement.rotateRight(45);
				movement.travel(20);
				movement.rotateLeft(45);
				movement.travel(60);
				movement.setSpeeds(4.5, 180);
				movement.quickStop();
				
				endboss = true;
				farAwayThres = 0.70f;
				wallThres = 0.55f;
				offsetVal = 0.65f;
				
				return TaskState.CONTINUE;
		} else {

				if (tactileSensor.frontIsPressed()) {
					movement.travel(-3);
					movement.stop();
					Delay.msDelay(3000);
				} else {
					super.specificExecute();

				}
				
				valval = colorSensor.getColorSensorValue();
				if (valval == 2.0) {
					movement.quickStop();
					movement.setSpeeds(20, 180);
					movement.travel(7);
					return TaskState.KILL;
				} else {
					return TaskState.CONTINUE;
				}
		}
	
			
	}
			
			
			
//			//drives for the first time over a pink line
//			pink = false;
//			movement.setSpeeds(9, 180);
//			movement.driveForward();
//			// If the front sensor is pressed, the final boss was hit, 
//			// wait 3 seconds and drives again straight forward
//			if (tactileSensor.frontIsPressed()) {
//				movement.travel(-3);
//				movement.stop();
//				Delay.msDelay(3000);
//			} else {
//				movement.setSpeeds(9, 180);
//				movement.driveForward();
//			}
//			
//			//funktioniert noch nicht einwandfrei
//			val2 = colorSensor.getColorSensorValue();
//			if (val2 == 2.0) {
//				movement.quickStop();
//				return TaskState.KILL;
//			} else {
//				return TaskState.CONTINUE;
//			}
	


	protected float getSensorValue() {
		float result = DistanceSensor.getInstance().getDistance();
		
		// Fit the sensor value, so we don�t get an infinity value
		if(result > farAwayThres) {
			result = offsetVal;
		}
		
		return result;
	}

	//Distance to wall in meters
	protected float getOffset() {
		return wallThres;
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














//package tasks;
//
//import lejos.utility.Delay;
//import sensors.ColorSensor;
//import sensors.DistanceSensor;
//
//public class DriveThrough extends RegulatedTask {
//		// max threshold
//		private final float FAR_AWAY_THRESHOLD = 0.16f;	
//		//distance to wall
//		private final float WALL_THRESHOLD = 0.11f;
//		boolean pink = true;
//		//first time for detecting pink line
//		float val = 0.0f;
//		//second time for detecting pink line
//		float val2 = 0.0f;
//		boolean rosa = false;
//		
//		public DriveThrough() {
//			movement.setSpeeds(9, 180);
//			movement.rotateRight(45);
//			movement.travel(20);
//			movement.rotateLeft(75);
//			movement.setSpeeds(4.5, 180);
//		}
//		
//		
//		public TaskState specificExecute() {	
//			if (!rosa) {
//				// If the front sensor is pressed, a wall was hit, rotate to the left to
//				// dodge the wall and drive further
//				if (tactileSensor.frontIsPressed()) {
//					movement.travel(-3);
//					movement.rotateLeft(80);
//					Delay.msDelay(200);
//					movement.stop();
//				} else {
//					super.specificExecute();
//				}
//	//			if(continueCurrentTask()){
//	//				return TaskState.CONTINUE;
//	//			}
//	//			else{
//	//				movement.travel(-4);
//	//				return TaskState.END;
//	//			}
//				
//				// todo �bergang
//				
//				val = colorSensor.getColorSensorValue();
//			
//				if (val == 2.0) {
//					rosa = true;
//					movement.travel(5);
//				} 
//				return TaskState.CONTINUE;
//				
//			} else {
//				//drives for the first time over a pink line
//				pink = false;
//				movement.setSpeeds(9, 180);
//				movement.driveForward();
//				// If the front sensor is pressed, the final boss was hit, 
//				// wait 3 seconds and drives again straight forward
//				if (tactileSensor.frontIsPressed()) {
//					movement.travel(-3);
//					movement.stop();
//					Delay.msDelay(3000);
//				} else {
//					movement.setSpeeds(9, 180);
//					movement.driveForward();
//				}
//				
//				//funktioniert noch nicht einwandfrei
//				val2 = colorSensor.getColorSensorValue();
//				if (val2 == 2.0) {
//					movement.quickStop();
//					return TaskState.KILL;
//				} else {
//					return TaskState.CONTINUE;
//				}
//			}
//		}
//
//
//		protected float getSensorValue() {
//			float result = DistanceSensor.getInstance().getDistance();
//			
//			// Fit the sensor value, so we don�t get an infinity value
//			if(result > FAR_AWAY_THRESHOLD) {
//				result = 0.15f;
//			}
//			
//			return result;
//		}
//
//		//Distance to wall in meters
//		protected float getOffset() {
//			return WALL_THRESHOLD;
//		}
//
//		//Turn of robot
//		protected float getKC() {
//			return 2000;
//		}
//
//		@Override
//		protected float getLostThreshold() {
//			return 0;
//		}
//
//		// Right direction
//		protected boolean invertCompensationDirection() {
//			return true;
//		}
//
//
//		@Override
//		protected boolean continueCurrentTask() {
//			ColorSensor cs = ColorSensor.getInstance();
//			float red = cs.getRedSensorValue();
//			return !detectLine();
//		}
//}
