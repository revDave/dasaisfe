package tasks;

import main.Main;

public class Bridge extends DriveThrough {
	public Bridge(Main main) {
		super(main);
	}

	@Override
	protected boolean distanceSensorNeeded() {
		return true;
	}
}
