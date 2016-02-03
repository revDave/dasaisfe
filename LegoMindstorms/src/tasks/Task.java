package tasks;

import lejos.hardware.motor.Motor;
import lejos.hardware.Button;
import lejos.utility.Delay;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
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

		movement = new Movement();
		colorSensor = new ColorSensor();
		tactileSensor = new TactileSensor();
		distanceSensor = new DistanceSensor();

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
