package main;

import lejos.hardware.Button;
import lejos.hardware.Key;
import lejos.hardware.KeyListener;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.LCD;
import sensors.BarcodeScanner;
import sensors.Movement;
import tasks.Bridge;
import tasks.ChainBridge;
import tasks.DriveThrough;
import tasks.Elevator;
import tasks.FinalBoss;
import tasks.FollowPath;
import tasks.Labyrinth;
import tasks.Seesaw;
import tasks.Swamp;
import tasks.Task;
import tasks.TaskState;

public class Main {
	private ParkourStage stage;
	private BarcodeScanner scanner;
	private Task runningTask = null;

	public static void main(String[] args) {
		new Main();
	}

	public Main() {
		LocalEV3.get().getKey("Escape").addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(Key k) {
			}

			@Override
			public void keyReleased(Key k) {
				System.exit(0);
			}
		});
		
		LCD.drawString("Press button to start", 0, 1);
		Button.waitForAnyPress();
		LCD.clear();
		
		//if robot should not start in the labyrinth, commit out
		//this line
		startNewTask(new Labyrinth());
		
		boolean continueMain = true;
		while (continueMain) {
			scanner = new BarcodeScanner(Movement.getInstance());
			continueMain = startNewTask(getTask()) != TaskState.KILL;
		}
	}

	public Task getTask() {
		Task task = null;
		stage = scanner.read();
		switch (stage) {
		case LABYRINTH:
			task = new Labyrinth();
			break;
		case SEESAW:
			task = new Seesaw();
			break;
		case FOLLOWPATH:
			task = new FollowPath();
			break;
		case BRIDGE:
			task = new Bridge();
			break;
		case CHAINBRIDGE:
			task = new ChainBridge();
			break;
		case SWAMP:
			task = new Swamp();
			break;
		case BOSS:
			;
		case ELEVATOR:
			task = new Elevator();
			break;
		case DRIVETHORUGH:
			task = new DriveThrough();
			break;
		default:
			break;
		}
		return task;
	}

	private TaskState startNewTask(Task task) {
		if (task != null) {
			if (runningTask != null) {
				runningTask.stopTask();
				runningTask = null;
			}
			runningTask = task;
			return runningTask.execute();
		}
		return TaskState.END;
	}

}
