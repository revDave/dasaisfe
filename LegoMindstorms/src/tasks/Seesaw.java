package tasks;

import main.Main;

public class Seesaw extends DriveThrough {
	public Seesaw (Main main) {
		super(main);
	}

	@Override
	protected boolean distanceSensorNeeded() {
		return true;
	}
}
