package tasks;

import lejos.hardware.motor.Motor;
import lejos.hardware.Button;
import lejos.utility.Delay;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import main.Main;
import sensors.Movement;

public abstract class Task {
	private Main main = null;
	private  EV3ColorSensor color;
	private EV3TouchSensor touch;
    protected Movement movement = null;
	
	public Task(Main main) {
		this.main = main;
		color = new EV3ColorSensor(SensorPort.S4);
		touch = new EV3TouchSensor(SensorPort.S2);
		Motor.A.setSpeed(20);
		Motor.B.setSpeed(20);
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
		Motor.A.forward();
		Motor.B.forward();
	}
	
	// Roboter faehrt mit 720 degrees per second rueckwaerts. 
	// Dauer muss selber festgelegt werden
	protected void driveBackward() {
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
		Motor.A.backward();
		Delay.msDelay(degrees * 20);
		stop();
	}
	
	// Roboter dreht sich um degrees nach rechts. 
	// TODO Testen
	protected void rotateRight(int degrees) {
		Motor.B.backward();
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

}
