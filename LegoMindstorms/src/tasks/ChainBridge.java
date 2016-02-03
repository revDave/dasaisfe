package tasks;

import sensors.DistanceSensor;
import main.Main;

public class ChainBridge extends Task {
	private DistanceSensor sensor = null;
	
	public ChainBridge(Main main) {
		super(main);
		sensor = new DistanceSensor();
	}

	@Override
	protected void specificExecute() {
		//float distanceValue = sensor.getDistance(distance);
		//LCD.drawString(String.valueOf(distanceValue), 0, 1);
		
	}
}
