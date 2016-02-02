package tasks;

import main.Main;
import sun.management.Sensor;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.utility.Delay;


public class FollowPath extends Task {
	private  EV3ColorSensor color;
	// TODO find Threshold
	private int threshold = 100;
	
	private int sleepDuration = 10;
	
	
	public FollowPath(Main main) {
		super(main);
	}

	
	@Override
	protected void specificExecute() {

		if (getLightSensorValue() > threshold) {
			/**	
			// On white, turn right
			LCD.drawString(right, 0, 1);
			MotorPort.B.controlMotor(0,stop);
			MotorPort.C.controlMotor(power, forward);**/
		} else {
			/**
			// On black, turn left
			LCD.drawString(left, 0, 1);				
			MotorPort.B.controlMotor(power, forward);
			MotorPort.C.controlMotor(0,stop);		**/
		}

		try {
			Thread.sleep(sleepDuration);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public int getLightSensorValue() {
		// TODO IMPLEMENT
		//SensorPort.S1.
		
		return 100;
	}
	
	
	

}
