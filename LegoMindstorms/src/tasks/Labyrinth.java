package tasks;

import main.Main;


public class Labyrinth extends Task {
	int counter = 10;
	public Labyrinth(Main main) {
		super(main);
	}

	@Override
	protected void specificExecute() {
		movement.setSpeeds(8, 20);
		//movement.driveForward();
		// if robot touches wall on the right side 
		if (tactileSensor.rightIsPressed()) {
			movement.travel(-2);
			movement.rotateLeft(20);
		}
		// if robot touches wall on the left side 
		else if (tactileSensor.leftIsPressed()) {
			movement.travel(-2);
			movement.rotateRight(40);
		}
		// found through the labyrinth
		else if(counter == 0) {
			movement.rotateRight(90);
		}
		// find through the labyrinth
		else {
			movement.driveForward();
			counter--;
		}
		//Delay.msDelay(10);
	}

}
