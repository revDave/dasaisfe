package sensors;

import lejos.hardware.motor.Motor;
import lejos.utility.Delay;

public class Movement {

	public Movement(){
		
	}
	
	// robot drives forward with 720 degrees per second.
	public void driveForward() {
		Motor.A.setSpeed(720);
		Motor.B.setSpeed(720);
		Motor.A.forward();
		Motor.B.forward();
	}
		
	// robot drives backward with 720 degrees per second.
	public void driveBackward() {
		Motor.A.setSpeed(720);
		Motor.B.setSpeed(720);
		Motor.A.backward();
		Motor.B.backward();
	}

	// robot stops
	public void stop() {
		Motor.A.stop();
		Motor.B.stop();
	}
	
	// robots turns counterclockwise by given degrees 
	// TODO Test
	public void rotateLeft(int degrees) {
		Motor.A.setSpeed(720);
		Motor.B.setSpeed(720);
		Motor.A.forward();
		Motor.B.backward();
		Delay.msDelay(degrees * 20);
		stop();
	}
	
	// robots turns clockwise by given degrees 
	// TODO Test
	public void rotateRight(int degrees) {
		Motor.A.setSpeed(720);
		Motor.B.setSpeed(720);
		Motor.A.backward();
		Motor.B.forward();
		Delay.msDelay(degrees * 20);
		stop();
	}

}
