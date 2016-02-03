package sensors;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;

public class TactileSensor {
	private EV3TouchSensor touchLeft;
	private EV3TouchSensor touchRight;
	private boolean pressed = true;	
	
	public TactileSensor(){
		touchLeft = new EV3TouchSensor(SensorPort.S1);
		touchRight = new EV3TouchSensor(SensorPort.S2);
	}
	
	// Returns a string with the info if the button is pressed
	// 0 = not pressed, 1 = pressed
	public float getTouchSensorValue(EV3TouchSensor touch) {
		float samples[] = new float[1];
		
		touch.getTouchMode().fetchSample(samples, 0);
		return samples[0];
	}
	
	// gives back if the left touch sensor is pressed
	public boolean leftIsPressed() {
		if (getTouchSensorValue(touchLeft) == 1) {
			pressed = true;
			
		} else {
			pressed = false;
		}
		
		return pressed;
	}
	
	// gives back if the right touch sensor is pressed
	public boolean rightIsPressed() {
		if (getTouchSensorValue(touchRight) == 1) {
			pressed = true;
			
		} else {
			pressed = false;
		}
		
		return pressed;
	}
}
