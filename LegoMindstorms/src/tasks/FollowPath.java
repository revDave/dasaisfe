package tasks;

import main.Main;
import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;
import lejos.hardware.Button;


public class FollowPath extends Task {
	// TODO find Threshold
	private float offset = (float) 0.6;
	private float Kp = 175;
	private float Ki = 10;
	private float Kd = 10;
	private float error = 0;
	private float lastError = 0;
	private float turn = 0;
	private float integral = 0;
	private float derivative = 0;

	public FollowPath(Main main) {
		super(main);
		movement.setSpeeds(15, 360);
		
		LCD.drawString("Press button to start", 0, 1);
		Button.waitForAnyPress();
		LCD.clear();
	}
	
	@Override
	protected void specificExecute() {
		// Get read from sensor
		float red = colorSensor.getRedSensorValue();
		error = red - offset;
		integral = (2/3) * integral + error;
		derivative = lastError - error;
		turn = Kp * error + Ki * integral + Kd * derivative;
		turn = turn < -200 ? -200 : turn;
		turn = turn > 200 ? 200 : turn;
		
		LCD.drawString("Sensor: " + Float.toString(red), 0, 1);
		LCD.drawString("Error: " + Float.toString(error), 0, 2);
		LCD.drawString("Integral: " + Float.toString(integral), 0, 3);
		LCD.drawString("Derivative: " + Float.toString(derivative), 0, 4);
		LCD.drawString("Turn: " + Float.toString(turn), 0, 5);

		movement.steer(turn);
		int delay = 175;
		//double delay = (double) (-2.11 * Math.abs(turn) + 440);
		//double delay = (0.0001 * turn * turn - 0.04 * Math.abs(turn) + 6.3);
		//delay = Math.exp(delay);
		//LCD.drawString("Delay: " + Double.toString(delay), 0, 6);
		Delay.msDelay((int) delay);
		lastError = error;
	}
}
