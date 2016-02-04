package tasks;

import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;
import main.Main;

public class Seesaw extends DriveThrough {
	private double offset = 0.51;
	private double Kp = 250;
	private double Ki = .5;
	private double Kd = 10;
	private double error = 0;
	private double lastError = 0;
	private double turn = 0;
	private double integral = 0;
	private double derivative = 0;
	
	public Seesaw (Main main) {
		super(main);
		movement.setSpeeds(7, 180);
		// Set sensor mode
	}

	@Override
	protected boolean distanceSensorNeeded() {
		
		
		
		
		return true;
	}
	
	@Override
	protected void specificExecute() {
		// Get read from sensor
		float setpoint = colorSensor.getRedSensorValue();
		
		
		error = setpoint - offset;
		integral = integral + error;
		derivative = lastError - error;
		turn = Kp * error + Ki * integral + Kd * derivative;
		
		if(turn < -200){
			turn = -200;
		} else if (turn > 200) {
			turn = 200;
		}
		
		LCD.drawString("Sensor: " + Double.toString(setpoint), 0, 2);
		LCD.drawString("Error: " + Double.toString(error), 0, 3);
		LCD.drawString("Turn: " + Double.toString(turn), 0, 4);

		movement.steer(turn);
		Delay.msDelay(100);
		lastError = error;
	}
}
