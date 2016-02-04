package tasks;

import main.Main;


//This class behaves as DriveThrough, the robot drives through the swamp by using
//its tactile sensors
public class Swamp extends DriveThrough {
	public Swamp(Main main) {
		super(main);
	}

	// TODO: after testing DriveThrough, the robot should behaves as in DriveThrough
	@Override
	protected boolean distanceSensorNeeded() {
		return true;
	}
}
