package tasks;

import main.Main;
import sun.management.Sensor;

import lejos.robotics.Color;

import java.sql.DriverPropertyInfo;

import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.utility.Delay;


public class FollowPath extends Task {
	private  EV3ColorSensor color;
	private  EV3ColorSensor col;
	// TODO find Threshold
	private int threshold = 100;
	
	private int sleepDuration = 10;
	
	
	public FollowPath(Main main) {
		super(main);
		color = new EV3ColorSensor(SensorPort.S3);
		color.setCurrentMode(color.getRedMode().getName());
		color.setFloodlight(Color.RED);
	}

	
	@Override
	protected void specificExecute() {

		// fahre gerade aus
		int col = getColorSensorValue();
		LCD.drawInt(col, 4, 4);
		if (col == Color.RED) {
			driveForward();
			Delay.msDelay(300);
			stop();
			
		//fahre nach rechts
		} else {
			/**
			// On black, turn left
			LCD.drawString(left, 0, 1);				
			MotorPort.B.controlMotor(power, forward);
			MotorPort.C.controlMotor(0,stop);		**/
			// grad noch einstellen
			rotateRight(10);

		}

		try {
			Thread.sleep(sleepDuration);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public int getColorSensorValue() {
		//LCD.drawString(color.getColorIDMode().getName(), 0, 4);
		
		//color.getFloodlight();
		//color.getRedMode();
		
		return color.getColorID();
	}
	
	
	

}
