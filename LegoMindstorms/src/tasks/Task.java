package tasks;

import lejos.hardware.Button;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import main.Main;
import sensors.Movement;

public abstract class Task {
	private Main main = null;
	private  EV3ColorSensor color;
	private EV3TouchSensor touch;
    protected Movement movement = null;
	
	public Task(Main main) {
		this.main = main;
		color = new EV3ColorSensor(SensorPort.S4);
		touch = new EV3TouchSensor(SensorPort.S2);
		movement = new Movement();
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
