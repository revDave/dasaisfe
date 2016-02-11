package tasks;

import lejos.utility.Delay;

import sensors.ColorSensor;
import sensors.DistanceSensor;



public class FinalBoss extends RegulatedTask {

		// max threshold
		private float farAwayThres = 0.70f;	
		//distance to wall
		private float wallThres = 0.55f;
		private float offsetVal = 0.65f;
		boolean pink = true;
		//first time for detecting pink line
		float val = 0.0f;
		//second time for detecting pink line
		float val2 = 0.0f;
		boolean rosa = false;
		boolean endboss = false;
		float valval = 0.0f;
		
		public FinalBoss() {
			movement.setSpeeds(15, 180);
			movement.travel(22);
			movement.quickStop();
			movement.travel(-17);
			movement.quickStop();
			Delay.msDelay(4000);
			movement.travel(50);
			movement.rotateRight(45);
			movement.travel(35);
			movement.rotateLeft(45);
			movement.travel(80);
			movement.setSpeeds(4.5, 180);
			movement.quickStop();
		}
		
		
		public TaskState specificExecute() {	
				
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
		


		protected float getSensorValue() {
			float result = DistanceSensor.getInstance().getDistance();
			
			// Fit the sensor value, so we don´t get an infinity value
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

//	boolean pink = false;
//	public FinalBoss() {
//		
//	}
//	
//	@Override
//	protected TaskState specificExecute() {
//		
//		movement.setSpeeds(9, 180);
//		movement.driveForward();
//		// If the front sensor is pressed, the final boss was hit, 
//		// wait 3 seconds and drives again straight forward
//		if (tactileSensor.frontIsPressed()) {
//			movement.travel(-3);
//			movement.stop();
//			Delay.msDelay(3000);
//		} else {
//			movement.setSpeeds(9, 180);
//			movement.driveForward();
//		}
//		getSensorValue();
//		if (pink == true) {
//			movement.quickStop();
//			return TaskState.KILL;
//		} else {
//			return TaskState.CONTINUE;
//		}
//
//	}
//
//	protected float getSensorValue() {
//		float val = colorSensor.getColorSensorValue();
//		
//		if (val == 2.0) {
//			pink = true;
//		}
//		return val;
//	}
//	
//	@Override
//	protected float getOffset() {
//		return 0.1f;
//	}
//
//	@Override
//	protected float getKC() {
//		return 999;
//	}
//
//	@Override
//	protected double getIFactor() {
//		return 0.05;
//	}
//	
//	@Override
//	protected double getDFactor() {
//		return 50;
//	}
//
//	@Override
//	protected float getLostThreshold() {
//		return 0.15f;
//	}
//
//
//	// Right direction
//	protected boolean invertCompensationDirection() {
//		return true;
//	}
//
//
//	@Override
//	protected boolean continueCurrentTask() {
//		ColorSensor cs = ColorSensor.getInstance();
//		float red = cs.getRedSensorValue();
//		return false;
//	}

