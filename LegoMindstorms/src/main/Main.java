package main;

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

	public static void main(String[] args) {
		new Main();
	}

	public Main() {
		scanner = new BarcodeScanner(Movement.getInstance());
		Task t = getTask();
		if (t != null) {
			t.execute();
		}
	}

	public Task getTask() {
		Task task = null;
		stage = scanner.read();
		switch (stage) {
		case LABYRINTH:
			task = new Labyrinth(this);
		case SEESAW:
			task = new Seesaw(this);
		case FOLLOWPATH:
			task = new FollowPath(this);
		case BRIDGE:
			task = new Bridge(this);
		case CHAINBRIDGE:
			task = new ChainBridge(this);
		case SWAMP:
			task = new Swamp(this);
		case BOSS:
			;
		case ELEVATOR:
			task = new Elevator(this);

		}
		return task;
	}

}
