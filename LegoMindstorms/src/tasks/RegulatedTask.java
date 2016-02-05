package tasks;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.utility.Stopwatch;
import main.Main;
import control.modules.PID_Control;

public abstract class RegulatedTask extends Task {
	//protected double OFFSET = (double) 0.5;
	//protected float ERROR_TRESHOLD = (float) 0.15;
	//protected float KC = 300;
	protected float PC = 500;
	protected float DT = 15;
	protected double P_FACTOR = 0.6;
	protected double I_FACTOR = 2.;
	protected double I_CONSTANT = 2 / 3;
	protected double D_FACTOR = 8;
	protected double error = 0;
	protected double turn = 0;
	protected boolean iAmLost = true;
	protected int maxLostTime = 300;
	protected float wheelSpeed = (float) 4;
	protected Stopwatch finderWatch;
	protected Stopwatch lostWatch;

	protected PID_Control pid;
	
	public RegulatedTask(Main main) {
		super(main);

		pid = new PID_Control(getKC(), PC, DT);
		pid.init(P_FACTOR, I_FACTOR, I_CONSTANT, D_FACTOR);
		pid.setOffset(getOffset());

		movement.setSpeeds(wheelSpeed, 120);

		LCD.drawString("Press button to start", 0, 1);
		Button.waitForAnyPress();
		LCD.clear();
	}
	
	protected abstract float getOffset();
	protected abstract float getKC();
	protected abstract float getLostThreshold();
	protected abstract float getSensorValue();
	
	@Override
	protected void specificExecute() {

		// Get read from sensor
		double value = getValueAndPrint();
		error = value - getOffset();
		
		// calc pid output with the given error
		turn = pid.calcOutput(error);

		turn = turn < -200 ? -200 : turn;
		turn = turn > 200 ? 200 : turn;

		movement.steer(-turn, false);
		
		


	}
	
	private float getValueAndPrint() {
		float value = getSensorValue();
		LCD.drawString(Float.toString(value), 0, 1);
		return value;
	}
	
}