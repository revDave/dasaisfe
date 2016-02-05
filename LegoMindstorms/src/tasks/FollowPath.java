package tasks;

import main.Main;
import lejos.hardware.lcd.LCD;
import control.modules.PID_Control;
import lejos.hardware.Button;
import lejos.utility.Stopwatch;

public class FollowPath extends Task {
	private double OFFSET = (double) 0.5;
	private float ERROR_TRESHOLD = (float) 0.15;
	private float KC = 300;
	private float PC = 500;
	private float DT = 15;
	private double P_FACTOR = 0.6;
	private double I_FACTOR = 2.;
	private double I_CONSTANT = 2 / 3;
	private double D_FACTOR = 8;
	private double error = 0;
	private double turn = 0;
	private boolean iAmLost = true;
	private int maxLostTime = 1000;
	private float wheelSpeed = (float) 4;
	private Stopwatch finderWatch;
	private Stopwatch lostWatch;

	private PID_Control pid;

	public FollowPath(Main main) {
		super(main);

		/**
		 * Example of the pid controller define the kc, pc and dt values,
		 * preferably as private attributes run pid.init() with the following
		 * arguments, same as above
		 * 
		 * @param p_factor
		 * @param i_factor
		 * @param i_CONSTANT
		 * @param d_factor
		 * 
		 */
		pid = new PID_Control(KC, PC, DT);
		pid.init(P_FACTOR, I_FACTOR, I_CONSTANT, D_FACTOR);
		pid.setOffset(OFFSET);

		movement.setSpeeds(wheelSpeed, 120);
		finderWatch = new Stopwatch();
		lostWatch = new Stopwatch();

		LCD.drawString("Press button to start", 0, 1);
		Button.waitForAnyPress();
		LCD.clear();
		findline(false);
	}

	@Override
	protected void specificExecute() {
		findline(true);

		// Get read from sensor
		double red = colorSensor.getRedSensorValue();
		error = red - OFFSET;
		if (red < ERROR_TRESHOLD) {
			if (lostWatch.elapsed() > maxLostTime) {
				iAmLost = true;

				// reset pid control
				pid.reset();
				return;
			}
		}

		// calc pid output with the given error
		turn = pid.calcOutput(error);

		turn = turn < -200 ? -200 : turn;
		turn = turn > 200 ? 200 : turn;

		movement.steer(turn, false);
		finderWatch.reset();
	}

	protected void findline(boolean reverse) {
		if (iAmLost) {
			turn = 160;
			for (int i = 200; i <= 1000; i = i + 100) {
				turn *= -1;
				finderWatch.reset();
				movement.steer(turn, reverse);
				while (iAmLost && finderWatch.elapsed() < i) {
					float red = colorSensor.getRedSensorValue();
					if (red >= ERROR_TRESHOLD) {
						iAmLost = false;
						lostWatch.reset();
					}
				}
				if (!iAmLost) {
					break;
				}
			}
		}
	}
}
