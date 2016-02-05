package tasks;

import sensors.DistanceSensor;
import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;
import main.Main;

public class Seesaw extends RegulatedTask {
	
	public Seesaw(Main main) {
		super(main);
	}
	
	@Override
	public void specificExecute() {
		super.specificExecute();
	}

	@Override
	protected float getSensorValue() {
		float result = DistanceSensor.getInstance().getDistance();
		
		if(result > 0.6) {
			result = 0.6f;
		}
		
		return result;
	}

	@Override
	protected float getOffset() {
		return 0.08f;
	}

	@Override
	protected float getKC() {
		return 650;
	}

	@Override
	protected float getLostThreshold() {
		return 0;
	}

}

