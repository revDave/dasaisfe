package sensors;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;

public class TactileSensor {
	private EV3TouchSensor touchFront;
	private EV3TouchSensor touchSide;
	private boolean pressed = true;	
	
	private static TactileSensor tactileSensor = null;
	
	private TactileSensor(){
		touchFront = new EV3TouchSensor(SensorPort.S1);
		touchSide = new EV3TouchSensor(SensorPort.S2);
	}
	
	public static TactileSensor getInstance() {
		if(tactileSensor == null) {
			tactileSensor = new TactileSensor();
		}
		
		return tactileSensor;
	}
	
	// Returns a string with the info if the button is pressed
	// 0 = not pressed, 1 = pressed
	public float getTouchSensorValue(EV3TouchSensor touch) {
		float samples[] = new float[1];
		
		touch.getTouchMode().fetchSample(samples, 0);
		return samples[0];
	}
	
	// gives back if the front touch sensor is pressed
	public boolean frontIsPressed() {
		if (getTouchSensorValue(touchFront) == 1) {
			pressed = true;
			
		} else {
			pressed = false;
		}
		
		return pressed;
	}
	
	// gives back if the touch sensor on the left side of the robot is pressed
	public boolean sideIsPressed() {
		if (getTouchSensorValue(touchSide) == 1) {
			pressed = true;
			
		} else {
			pressed = false;
		}
		
		return pressed;
	}
}
