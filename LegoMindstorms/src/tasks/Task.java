package tasks;

import lejos.hardware.motor.Motor;
import main.Main;

public abstract class Task {
	private Main main = null;
	
	public Task(Main main) {
		this.main = main;
	}
	
	public void execute() {
		while(true) {//!Button.ESCAPE.isDown()) {
			specificExecute();			
			main.readBarcode();
		}
	}
	
	
	protected abstract void specificExecute();
	
	
	// Roboter f�hrt mit 720 degrees per second vorw�rts. 
	// Dauer muss selber festgelegt werden
	protected void driveForward() {
		Motor.A.setSpeed(720);
		Motor.B.setSpeed(720);
		Motor.A.forward();
		Motor.B.forward();
	}
	
	// Roboter f�hrt mit 720 degrees per second r�ckw�rts. 
	// Dauer muss selber festgelegt werden
	protected void driveBackward() {
		Motor.A.setSpeed(720);
		Motor.B.setSpeed(720);
		Motor.A.backward();
		Motor.B.backward();
	}
	
	
	// Roboter dreht sich um degrees nach rechts. 
	// TODO Testen
	protected void rotateLeft(int degrees) {
		Motor.A.setSpeed(720);
		Motor.B.setSpeed(720);
		Motor.A.forward();
		Motor.B.backward();
	}
	
	// Roboter dreht sich um degrees nach rechts. 
	// TODO Testen
	protected void rotateRight(int degrees) {
		Motor.A.setSpeed(720);
		Motor.B.setSpeed(720);
		Motor.A.backward();
		Motor.B.forward();
	}

}
