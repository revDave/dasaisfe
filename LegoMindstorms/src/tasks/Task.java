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
import sensors.Movement;
import sensors.TactileSensor;

public abstract class Task {
	private Main main = null;
	private  EV3ColorSensor color;
	private EV3TouchSensor touchLeft;
	private EV3TouchSensor touchRight;
	private EV3UltrasonicSensor distance;
    protected Movement movement = null;
    protected ColorSensor colorSensor = null;
    protected TactileSensor tactileSensor = null;
    
	public Task(Main main) {
		this.main = main;
		color = new EV3ColorSensor(SensorPort.S4);
		distance = new EV3UltrasonicSensor(SensorPort.S3);
		touchLeft = new EV3TouchSensor(SensorPort.S1);
		touchRight = new EV3TouchSensor(SensorPort.S2);
		movement = new Movement();
		colorSensor = new ColorSensor();
		tactileSensor = new TactileSensor();
		Motor.A.setSpeed(20);
		Motor.B.setSpeed(20);
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
