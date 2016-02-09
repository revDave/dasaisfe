package main;

import lejos.hardware.Key;
import lejos.hardware.KeyListener;
import lejos.hardware.ev3.LocalEV3;
import sensors.BarcodeScanner;
import sensors.Movement;
import tasks.Bridge;
import tasks.ChainBridge;
import tasks.Elevator;
import tasks.FinalBoss;
import tasks.FollowPath;
import tasks.Labyrinth;
import tasks.Seesaw;
import tasks.Swamp;
import tasks.Task;

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
		
		scanner = new BarcodeScanner(Movement.getInstance());
		startNewTask(getTask());
	}

	public Task getTask() {
		Task task = null;
		stage = scanner.read();
		switch (stage) {
		case LABYRINTH:
			task = new Labyrinth();
		case SEESAW:
			task = new Seesaw();
		case FOLLOWPATH:
			task = new FollowPath();
		case BRIDGE:
			task = new Bridge();
		case CHAINBRIDGE:
			task = new ChainBridge();
		case SWAMP:
			task = new Swamp();
		case BOSS:
			;
		case ELEVATOR:
			task = new Elevator();

		}
		return task;
	}

	private void startNewTask(Task task) {
		if (task != null) {
			if (runningTask != null) {
				runningTask.stopTask();
				runningTask = null;
			}
			runningTask = task;
			runningTask.execute();
		}
	}
	
}
