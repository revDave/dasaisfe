package tasks;

import java.io.IOException;

import edu.kit.mindstorms.communication.ComModule;
import edu.kit.mindstorms.communication.Communication;
import lejos.hardware.Button;
import lejos.utility.Delay;
import main.Main;

public class Elevator extends Task {
	
	@Override
	protected TaskState specificExecute() {
		movement.quickStop();
		try {
			ComModule com = Communication.getModule();
			
			while(! com.requestStatus())
				Delay.msDelay(50);
			
			while(! com.requestElevator())
				Delay.msDelay(50);

			movement.rotateLeft(10);
			movement.setSpeeds(1.75, 120);
			movement.driveForward();
			Delay.msDelay(5000);
			movement.rotateRight(15);
			Delay.msDelay(2000);
			
			movement.setSpeeds(3.5, 120);
			movement.driveForward();
			while(!tactileSensor.frontIsPressed());
			movement.quickStop();
			movement.travel(-2);
			
			while(!com.moveElevatorDown())
				Delay.msDelay(50);
			
			Delay.msDelay(7000);
			movement.travel(7);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Button.waitForAnyPress();
			return TaskState.KILL;
		}
		return TaskState.END;
	}

	@Override
	protected boolean continueCurrentTask() {
		return false;
	}

}
