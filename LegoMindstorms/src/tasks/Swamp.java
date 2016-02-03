package tasks;

import main.Main;

public class Swamp extends DriveThrough {
	public Swamp(Main main) {
		super(main);
	}

	@Override
	protected boolean distanceSensorNeeded() {
		return true;
	}
}
