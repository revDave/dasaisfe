package tasks;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.utility.Stopwatch;
import main.Main;
import control.modules.PID_Control;

/**
 * This class represents the general regulation task.
 * @author david
 *
 */
public abstract class RegulatedTask extends Task {
	//protected double OFFSET = (double) 0.5;
	//protected float ERROR_TRESHOLD = (float) 0.15;
	//protected float KC = 300;
	protected float PC = 500;
	protected float DT = 15;
	protected double P_FACTOR = 0.6;
	//protected double I_FACTOR = 2.;
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
		pid.init(P_FACTOR, getIFactor(), I_CONSTANT, D_FACTOR);
		pid.setOffset(getOffset());

		movement.setSpeeds(wheelSpeed, 120);

		LCD.drawString("Press button to start", 0, 1);
		Button.waitForAnyPress();
		LCD.clear();
	}
	
	// classifying value for the sensor 
	protected abstract float getOffset();
	
	// magnitude of deviation
	protected abstract float getKC();

	// get I factor
	protected double getIFactor(){
		return 2.;
	};
	
	// threshold if target is lost. Used in path following
	protected abstract float getLostThreshold();
	
	// returns the specific sensor value that is needed, for example light or distance sensor
	protected abstract float getSensorValue();
	
	protected boolean invertCompensationDirection() {
		return false;
	}
	
	
	// does the regulation
	@Override
	protected void specificExecute() {

		// Get read from sensor
		double value = getValueAndPrint();
		error = value - getOffset();
		
		// calc pid output with the given error
		turn = pid.calcOutputDefault(error);

		turn = turn < -200 ? -200 : turn;
		turn = turn > 200 ? 200 : turn;

		turn = (invertCompensationDirection()) ? -turn : turn;
		movement.steer(turn, false);
		
	}
	
	protected void specificExecuteReset() {

		// Get read from sensor
		double value = getValueAndPrint();
		error = value - getOffset();
		
		// calc pid output with the given error
		turn = pid.calcOutputReset(error);

		turn = turn < -200 ? -200 : turn;
		turn = turn > 200 ? 200 : turn;

		turn = (invertCompensationDirection()) ? -turn : turn;
		movement.steer(turn, false);
		
	}
	
	private float getValueAndPrint() {
		float value = getSensorValue();
		LCD.drawString(Float.toString(value), 0, 1);
		return value;
	}
	
}