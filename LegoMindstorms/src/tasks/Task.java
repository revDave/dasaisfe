package tasks;

import lejos.hardware.motor.Motor;
import lejos.hardware.Button;
import lejos.utility.Delay;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import main.Main;

public abstract class Task {
	private Main main = null;
	private  EV3ColorSensor color;
	private EV3TouchSensor touch;

	
	public Task(Main main) {
		this.main = main;
		color = new EV3ColorSensor(SensorPort.S4);
		touch = new EV3TouchSensor(SensorPort.S2);
	}
	
	public void execute() {
		boolean goOn = true;
		while(goOn) {
			specificExecute();			
			//main.readBarcode();
			goOn = ! Button.ESCAPE.isDown();
		}
	}
	
	
	protected abstract void specificExecute();
	
	
	// Roboter fährt mit 720 degrees per second vorwärts. 
	// Dauer muss selber festgelegt werden
	protected void driveForward() {
		Motor.A.setSpeed(720);
		Motor.B.setSpeed(720);
		Motor.A.forward();
		Motor.B.forward();
	}
	
	// Roboter fährt mit 720 degrees per second rückwärts. 
	// Dauer muss selber festgelegt werden
	protected void driveBackward() {
		Motor.A.setSpeed(720);
		Motor.B.setSpeed(720);
		Motor.A.backward();
		Motor.B.backward();
	}

	// Roboter f�hrt mit 720 degrees per second r�ckw�rts. 
	// Dauer muss selber festgelegt werden
	protected void stop() {
		Motor.A.stop();
		Motor.B.stop();
	}
	
	// Roboter dreht sich um degrees nach rechts. 
	// TODO Testen
	protected void rotateLeft(int degrees) {
		Motor.A.setSpeed(720);
		Motor.B.setSpeed(720);
		Motor.A.forward();
		Motor.B.backward();
		Delay.msDelay(degrees * 20);
		stop();
	}
	
	// Roboter dreht sich um degrees nach rechts. 
	// TODO Testen
	protected void rotateRight(int degrees) {
		Motor.A.setSpeed(720);
		Motor.B.setSpeed(720);
		Motor.A.backward();
		Motor.B.forward();
		Delay.msDelay(degrees * 20);
		stop();
	}
	
	public int getColorSensorValue() {
		color.setFloodlight(true);
		//LCD.drawString(color.getColorIDMode().getName(), 0, 4);
		color.setCurrentMode(color.getColorIDMode().getName());
		
		//color.getFloodlight();
		//color.getRedMode();
		
		return color.getColorID();
	}
	
//	public int getTouchSensorValue() {
//		touch.
//		return touch.getTouchMode().;
//	}

}
