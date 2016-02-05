package tasks;

import lejos.hardware.Button;
import main.Main;
import sensors.ColorSensor;
import sensors.DistanceSensor;
import sensors.Movement;
import sensors.TactileSensor;

public abstract class Task {
	private Main main = null;

    protected Movement movement = null;
    protected ColorSensor colorSensor = null;
    protected TactileSensor tactileSensor = null;
    protected DistanceSensor distanceSensor = null;
    
	public Task(Main main) {
		this.main = main;

		movement = Movement.getInstance();
		colorSensor = ColorSensor.getInstance();
		//tactileSensor = TactileSensor.getInstance();
		distanceSensor = DistanceSensor.getInstance();

	}
	
	public void execute() {
		boolean goOn = true;
		while(goOn) {
			specificExecute();			
			//main.readBarcode();
			goOn = ! Button.ESCAPE.isDown();
		}
	}
	
	
	protected abstract void specificExecute();
	
	
	

}
