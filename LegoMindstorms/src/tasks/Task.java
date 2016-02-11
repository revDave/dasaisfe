package tasks;

import java.io.File;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import main.Main;
import sensors.ColorSensor;
import sensors.DistanceSensor;
import sensors.Movement;
import sensors.TactileSensor;

public abstract class Task {
	private boolean finish = false;
	protected Movement movement = null;
	protected ColorSensor colorSensor = null;
	protected TactileSensor tactileSensor = null;
	protected DistanceSensor distanceSensor = null;

	public Task() {
		movement = Movement.getInstance();
		colorSensor = ColorSensor.getInstance();
		tactileSensor = TactileSensor.getInstance();
		distanceSensor = DistanceSensor.getInstance();

		Sound.setVolume(100);

		Thread sound = new Thread() {
			
			@Override
			public void run() {
				File file = getSoundFile();

				if (file != null)
					Sound.playSample(file);
			}
			
	
		};
		sound.start();

	}

	public TaskState execute() {
		TaskState specEx = specificExecute();
		while (specEx == TaskState.CONTINUE) {
			// main.readBarcode();
			specEx = specificExecute();
			if (Button.ESCAPE.isDown())
				return TaskState.KILL;
		}
		movement.quickStop();

		if (specEx == TaskState.KILL) {
			movement.stop();
		}

		return specEx;
	}

	public void stopTask() {
		finish = true;
	}

	protected abstract TaskState specificExecute();

	protected abstract boolean continueCurrentTask();

	protected boolean detectLine() {
		return colorSensor.getRedSensorValue() > .4;
	}

	private File getSoundFile() {
		final int COUNT = 5;

		int random = (int) (Math.random() * (double) COUNT);

		String fileName = null;

		switch (random) {
		case 0:
			fileName = "elephant.wav";
			break;
		case 1:
			fileName = "godfather.wav";
			break;

		case 2:
			fileName = "inception.wav";
			break;

		case 3:
			fileName = "mario.wav";
			break;

		case 4:
			fileName = "trollolol.wav";
			break;
		}
		File soundFile = null;

		if (fileName != null) {
			soundFile = new File(fileName);
		}

		return soundFile;
	}

}
