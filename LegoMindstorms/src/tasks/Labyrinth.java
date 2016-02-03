package tasks;

import lejos.utility.Delay;
import main.Main;
import sensors.Movement;

public class Labyrinth extends Task {
	
	public Labyrinth(Main main) {
		super(main);
	}

	@Override
	protected void specificExecute() {
		movement.setSpeeds(80, 20);
		//movement.driveForward();
		// if robot touches wall on the right side 
		if (tactileSensor.rightIsPressed()) {
			movement.travel(-2);
			movement.rotateLeft(5);
		}
		// if robot touches wall on the left side 
		else if (tactileSensor.leftIsPressed()) {
			movement.travel(-2);
			movement.rotateRight(20);
		}
//		// if robot touches wall on both sides 
//		else if (tactileSensor.leftIsPressed() && tactileSensor.rightIsPressed()) {
//			movement.travel(-2);
//			movement.rotateRight(10);
//		} 
		// found through the labyrinth
		else {
			movement.driveForward();
		}
		Delay.msDelay(10);
	}

}
