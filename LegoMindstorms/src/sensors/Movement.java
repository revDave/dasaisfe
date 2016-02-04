package sensors;

import lejos.hardware.motor.Motor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.hardware.motor.NXTRegulatedMotor;

public class Movement {
	protected DifferentialPilot pilot;
	protected float speed;
	protected NXTRegulatedMotor leftMotor;
	protected NXTRegulatedMotor rightMotor;
	
	public Movement(){
	    // Parameters in cm
		// Wheel diameter, track width, left motor, right motor, drives in reverse
		pilot = new DifferentialPilot(2.42f, 12.3f, Motor.B, Motor.A, true);
		leftMotor = Motor.B;
		rightMotor = Motor.A;
		setSpeeds(1, 15);
	}
	
	// Travel speed in wheel diameters, rotate speed in degrees
	public void setSpeeds(double travelSpeed, double rotateSpeed){
		pilot.setTravelSpeed(travelSpeed);
		pilot.setRotateSpeed(rotateSpeed);
		speed = (float) (travelSpeed * 360 / Math.PI);
	}
	
	// robot drives forward with 720 degrees per second.
	public void driveForward() {
		//pilot.forward();
		NXTRegulatedMotor syncList[] = {leftMotor};
		rightMotor.synchronizeWith(syncList);
		
		rightMotor.startSynchronization();
		rightMotor.backward();
		leftMotor.backward();
		rightMotor.endSynchronization();
	}
		
	// robot drives backward with 720 degrees per second.
	public void driveBackward() {
		//pilot.backward();
		NXTRegulatedMotor syncList[] = {leftMotor};
		rightMotor.synchronizeWith(syncList);
		rightMotor.startSynchronization();
		rightMotor.forward();
		leftMotor.forward();
		rightMotor.endSynchronization();
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
		//pilot.steer(turnRate);
		double ratio = 100 - Math.abs(turnRate);
		NXTRegulatedMotor fastMotor;
		NXTRegulatedMotor slowMotor;
		
		// Turn rate defines which side to turn to
		if(turnRate < 0){
			// Negative rate turns right (
			fastMotor = leftMotor;
			slowMotor = rightMotor;
		} else {
			// Positive rate turns left )
			fastMotor = rightMotor;
			slowMotor = leftMotor;
		}
		
		// Perform movement
		NXTRegulatedMotor syncList[] = {slowMotor};
		fastMotor.synchronizeWith(syncList);
		fastMotor.startSynchronization();
		// Set speeds according to ratio
		fastMotor.setSpeed(speed);
		slowMotor.setSpeed((float) (Math.abs(ratio) / 100 * speed));
		if(ratio < 0){
			fastMotor.backward();
			slowMotor.forward();
		} else {
			fastMotor.backward();
			slowMotor.backward();
		}
		fastMotor.endSynchronization();
	}
}
