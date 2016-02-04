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
		movement.setSpeeds(10, 20);

		// if robot touches wall on the right side 
		if (tactileSensor.rightIsPressed()) {
			movement.travel(-2);
			movement.rotateLeft(40);
		}
		// if robot touches wall on the left side 
		else if (tactileSensor.leftIsPressed()) {
			movement.travel(-2);
			movement.rotateRight(20);
		}
		// check if there is a wall on the right side by using DistanceSensor
		// if not, drive straight forward
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
