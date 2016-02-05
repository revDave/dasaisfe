package tasks;

import lejos.hardware.lcd.LCD;
import main.Main;


public class Labyrinth extends Task {

	//TODO Test and fit
	private final float LAB_THRESHOLD = 0.1f;
	
	public Labyrinth(Main main) {
		super(main);
	}

	@Override
	protected void specificExecute() {
		movement.setSpeeds(15, 20);
		//checks if distanceSensor is up or down
		movement.unbowSensor();

		// if robot touches the wall in front oh it 
		if (tactileSensor.frontIsPressed()) {
			movement.travel(-2);
			movement.rotateRight(80);
		}
		// check if there is a wall on the left side by using DistanceSensor
		// if there is a wall, drive straight forward, if not, rotate about 90 degrees 
		// to the left
		else {
			float distanceValue = distanceSensor.getDistance();
			LCD.drawString(String.valueOf(distanceValue), 0, 1);
			
			if(LAB_THRESHOLD < distanceValue) {
				movement.travel(5);
				movement.rotateLeft(90);
				movement.travel(5);
				
			// drive through the labyrinth
			} else {
				movement.driveForward();
			}
		}
	}

}
