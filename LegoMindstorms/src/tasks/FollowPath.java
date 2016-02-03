package tasks;

import main.Main;
import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;


public class FollowPath extends Task {
	// TODO find Threshold
	private float offset = (float) 0.45;
	private float Kp = 250;
	private float Ki = 0;
	private float Kd = 0;
	private float error = 0;
	private float lastError = 0;
	private float turn = 0;
	private float integral = 0;
	private float derivative = 0;

	public FollowPath(Main main) {
		super(main);
		movement.setSpeeds(10, 120);
		// Set sensor mode
		color.setCurrentMode(color.getRedMode().getName());
	}
	
	@Override
	protected void specificExecute() {
		// Get read from sensor
		float samples[] = new float[1];
		color.getRedMode().fetchSample(samples, 0);
		float red = samples[0];
		error = red - offset;
		integral = (2/3) * integral + error;
		derivative = lastError - error;
		turn = Kp * error + Ki * integral + Kd * derivative;
		turn = turn < -200 ? -200 : turn;
		turn = turn > 200 ? 200 : turn;

//		LCD.drawString("Sensor: " + Float.toString(red), 0, 2);
//		LCD.drawString("Error: " + Float.toString(error), 0, 3);
//		LCD.drawString("Turn: " + Float.toString(turn), 0, 4);

		Delay.msDelay(111);
		movement.steer(turn);
		lastError = error;
	}
}
