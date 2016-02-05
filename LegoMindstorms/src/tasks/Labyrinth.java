package tasks;

import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;
import main.Main;


public class Labyrinth extends Task {

	//TODO Test and fit
	private final float UTURN_THRESHOLD = 0.15f;
	private final float AWAY_THRESHOLD = 0.1f;
	private final float NEAR_THRESHOLD = 0.02f;
	
	public Labyrinth(Main main) {
		super(main);
	}

	@Override
	protected void specificExecute() {
		movement.setSpeeds(3, 20);
		//checks if distanceSensor is up or down
		//movement.unbowSensor();

		// if robot touches the wall in front oh it 
		if (tactileSensor.frontIsPressed()) {
			movement.travel(-3);
			movement.rotateRight(90);
		}
		// check if there is a wall on the left side by using DistanceSensor
		// if there is a wall, drive straight forward, if not, rotate about 90 degrees 
		// to the left
		else {
			float distanceValue = distanceSensor.getDistance();
			LCD.drawString(String.valueOf(distanceValue), 0, 1);
			
			if(UTURN_THRESHOLD < distanceValue) {
				movement.travel(15);
				movement.rotateLeft(90);
				movement.travel(10);
			}
			// Robot is too near to the wall
			else if (NEAR_THRESHOLD > distanceValue){
				movement.rotateRight(5);
			}
			// Robot is too far away from the wall
			else if (AWAY_THRESHOLD < distanceValue) {
				movement.rotateLeft(5);
			// drive through the labyrinth
			} else {
				movement.driveForward();
			}
		}
		Delay.msDelay(200); 
	}

}
