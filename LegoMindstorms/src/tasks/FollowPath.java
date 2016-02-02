package tasks;

import main.Main;
import sun.management.Sensor;

import lejos.robotics.Color;
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
	}

	
	@Override
	protected void specificExecute() {

		// fahre gerade aus
		if (getLightSensorValue() == Color.GRAY ) {
			//LCD.drawString(right, 0, 1);
			Motor.A.setSpeed(720);
			Motor.B.setSpeed(720);
			Motor.A.forward();
			Motor.B.forward();
		//fahre nach rechts
		} else {
			/**
			// On black, turn left
			LCD.drawString(left, 0, 1);				
			MotorPort.B.controlMotor(power, forward);
			MotorPort.C.controlMotor(0,stop);		**/
			Motor.A.setSpeed(100);
			Motor.B.setSpeed(200);
			Motor.A.forward();
			Motor.B.forward();
		}

		try {
			Thread.sleep(sleepDuration);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public int getLightSensorValue() {
		color = new EV3ColorSensor(SensorPort.S4);
		//color.getFloodlight();
		//color.getRedMode();
		
		return color.getColorID();
	}
	
	
	

}
