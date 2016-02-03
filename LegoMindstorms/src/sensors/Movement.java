package sensors;

import lejos.hardware.motor.Motor;
import lejos.robotics.navigation.DifferentialPilot;

public class Movement {
	protected DifferentialPilot pilot;
	
	public Movement(){
	    // Parameters in cm
		// Wheel diameter, track width, left motor, right motor, drives in reverse
		pilot = new DifferentialPilot(2.42f, 12.3f, Motor.B, Motor.A, true);
		setSpeeds(1, 15);
	}
	
	// Travel speed in wheel diameters, rotate speed in degrees
	public void setSpeeds(double travelSpeed, double rotateSpeed){
		pilot.setTravelSpeed(travelSpeed);
		pilot.setRotateSpeed(rotateSpeed);
	}
	
	// robot drives forward with 720 degrees per second.
	public void driveForward() {
		pilot.forward();
	}
		
	// robot drives backward with 720 degrees per second.
	public void driveBackward() {
		pilot.backward();
	}

	// robot stops
	public void stop() {
		pilot.stop();
	}
	
	// robot breaks
	public void quickStop() {
		pilot.quickStop();
	}
	
	// robot travels distance in cm 
	public void travel(double distance){
		pilot.travel(distance);
	}
	
	// robots turns counterclockwise by given degrees 
	// TODO Test
	public void rotateLeft(double degrees) {
		pilot.rotate(degrees);
	}
	
	// robots turns clockwise by given degrees 
	// TODO Test
	public void rotateRight(double degrees) {
		pilot.rotate(-degrees);
	}

	public void steer(double turnRate){
		pilot.steer(turnRate);
	}
}
