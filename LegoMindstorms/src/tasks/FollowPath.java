package tasks;

import main.Main;
import sensors.Movement;
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
	private float threshold = (float) 0.1;
	
	private int sleepDuration = 10;
	

	public FollowPath(Main main) {
		super(main);
		color = new EV3ColorSensor(SensorPort.S3);
		color.setCurrentMode(color.getColorIDMode().getName());
		color.setFloodlight(Color.RED);
	}

	
	@Override
	protected void specificExecute() {

		// drive straight forward
		int col = getColorSensorValue();
		LCD.drawInt(col, 4, 4);
		if (col == Color.RED) {
			movement.driveForward();
			Delay.msDelay(300);
			movement.stop();
			
		//fahre nach rechts
		} else {
			/**
			// On black, turn left
			LCD.drawString(left, 0, 1);				
			MotorPort.B.controlMotor(power, forward);
			MotorPort.C.controlMotor(0,stop);		**/
			// grad noch einstellen
			rotateRight(5);
			red = getRedSensorValue();
			if(red < threshold){
				rotateLeft(13);
			}

		}

		try {
			Thread.sleep(sleepDuration);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public int getColorSensorValue() {
		color.setCurrentMode(color.getColorIDMode().getName());
		return color.getColorID();
	}
	
	public float getRedSensorValue() {
		color.setCurrentMode(color.getRedMode().getName());
		float samples[] = new float[1];
		
		color.getRedMode().fetchSample(samples, 0);
		
		return samples[0];
	}
	
	
	

}
