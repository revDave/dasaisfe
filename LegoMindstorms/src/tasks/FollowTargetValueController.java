package tasks;

import sensors.Movement;
import sensors.SensorValueReceiver;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.utility.Stopwatch;
import main.Main;

public class FollowTargetValueController {
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
	private int maxLostCount = 1000;
	private Stopwatch watch;
	private SensorValueReceiver valueReceiver = null;
	
	private Movement movement = Movement.getInstance();

	public FollowTargetValueController(float offset, float errorThreshold, float Kc, float Pc, float Dt, int maxLostCount, SensorValueReceiver valueReceiver) {
		this.valueReceiver = valueReceiver;
		this.offset = offset;
		this.errorThreshold = errorThreshold;
		this.Kc = Kc;
		this.Pc = Pc;
		this.Dt = Dt;
		this.maxLostCount = maxLostCount;
				
		
		movement.setSpeeds(4.5, 120);
		watch = new Stopwatch();
		
	}
	
	public void execute() {
	
	}
	

}

