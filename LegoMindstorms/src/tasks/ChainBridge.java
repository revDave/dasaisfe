package tasks;

import main.Main;

public class ChainBridge extends DriveThrough {

	public ChainBridge(Main main) {
		super(main);
		
	}

	@Override
	protected boolean distanceSensorNeeded() {
		// TODO Auto-generated method stub
		return true;
	}
	
}