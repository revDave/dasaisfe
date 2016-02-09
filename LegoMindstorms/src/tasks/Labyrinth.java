package tasks;

import lejos.utility.Delay;
import main.Main;
import sensors.DistanceSensor;

// funcs stabil, if we have time: optimize speed
public class Labyrinth extends RegulatedTask {
	// max threshold
	private final float FAR_AWAY_THRESHOLD = 0.16f;
	
	//private final float UTURN_THRESHOLD = 0.12f;
	//private final float AWAY_THRESHOLD = 0.1f;
	//private final float NEAR_THRESHOLD = 0.02f;
	
	//distance to wall
	private final float WALL_THRESHOLD = 0.11f;
	
	public Labyrinth() {
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
		return TaskState.CONTINUE;
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

	//TODO Test and fit
//	private final float UTURN_THRESHOLD = 0.12f;
//	private final float AWAY_THRESHOLD = 0.1f;
//	private final float NEAR_THRESHOLD = 0.02f;
//	
//	public Labyrinth(Main main) {
//		super(main);
//	}
//
//	@Override
//	protected void specificExecute() {
//		movement.setSpeeds(3, 20);
//		//checks if distanceSensor is up or down
//		//movement.unbowSensor();
//
//		// if robot touches the wall in front oh it 
//		if (tactileSensor.frontIsPressed()) {
//			movement.travel(-3);
//			movement.rotateRight(90);
//		}
//		// check if there is a wall on the left side by using DistanceSensor
//		// if there is a wall, drive straight forward, if not, rotate about 90 degrees 
//		// to the left
//		else {
//			float distanceValue = distanceSensor.getDistance();
//			LCD.drawString(String.valueOf(distanceValue), 0, 1);
//			
//			if(UTURN_THRESHOLD < distanceValue) {
//				movement.travel(15);
//				movement.rotateLeft(90);
//				movement.travel(10);
//			}
//			// Robot is too near to the wall
//			else if (NEAR_THRESHOLD > distanceValue){
//				movement.rotateRight(5);
//			}
//			// Robot is too far away from the wall
//			else if (AWAY_THRESHOLD < distanceValue) {
//				movement.rotateLeft(5);
//			// drive through the labyrinth
//			} else {
//				movement.driveForward();
//			}
//		}
//		Delay.msDelay(200); 
//	}

}
