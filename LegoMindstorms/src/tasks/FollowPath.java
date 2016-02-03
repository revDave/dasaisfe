package tasks;

import main.Main;
import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;


public class FollowPath extends Task {
	// TODO find Threshold
	private float offset = (float) 0.51;
	private float Kp = 250;
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
		// Set sensor mode

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
		
		LCD.drawString("Sensor: " + Float.toString(red), 0, 2);
		LCD.drawString("Error: " + Float.toString(error), 0, 3);
		LCD.drawString("Turn: " + Float.toString(turn), 0, 4);

		movement.steer(turn);
		Delay.msDelay(100);
		lastError = error;
	}
}
