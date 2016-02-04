package tasks;

import main.Main;
import lejos.hardware.lcd.LCD;
import lejos.hardware.Button;
import lejos.utility.Delay;
import lejos.utility.Stopwatch;

public class FollowPath extends Task {
	private float offset = (float) 0.46;
	private float errorThreshold = (float) 0.20;
	private float Kc = 300;
	private float Pc = 500;
	private float Dt = 15;
	private float Kp = (float) (0.6 * Kc);
	private float Ki = (float) (2 * Kp * Dt / Pc);
	private float Kd = (float) (Kp * Pc / (8 * Dt));
	private float error = 0;
	private float lastError = 0;
	private float turn = 0;
	private float integral = 0;
	private float derivative = 0;
	private boolean iAmLost = true;
	private int lostCount = 0;
	private int maxLostCount = 1750;
	private Stopwatch watch;

	public FollowPath(Main main) {
		super(main);
		movement.setSpeeds(4.5, 120);
		watch = new Stopwatch();
		
		LCD.drawString("Press button to start", 0, 1);
		Button.waitForAnyPress();
		LCD.clear();
		findline(false);
	}
	
	@Override
	protected void specificExecute() {
		findline(true);
		
		// Get read from sensor
		float red = colorSensor.getRedSensorValue();
		error = red - offset;
		integral = (2/3) * integral + error;
		derivative = lastError - error;
		if(red < errorThreshold){
			lostCount++;
			if(lostCount > maxLostCount){
				iAmLost = true;
				integral = 0;
				lastError = 0;
				return;
			}
		}
		turn = Kp * error + Ki * integral + Kd * derivative;
		turn = turn < -200 ? -200 : turn;
		turn = turn > 200 ? 200 : turn;

		movement.steer(turn, false);
		lastError = error;
		watch.reset();
	}
	
	protected void findline(boolean reverse) {
		if(iAmLost){
			turn = 160;
			for(int i = 100; i <= 1000; i = i + 100){
				turn *= -1;
				watch.reset();
				movement.steer(turn, reverse);
				while(iAmLost && watch.elapsed() < i){
					float red = colorSensor.getRedSensorValue();
					if(red >= errorThreshold){
						iAmLost = false;
						lostCount = 0;
					}
				}
				if(! iAmLost){
					break;
				}
			}
		}
	}
}
