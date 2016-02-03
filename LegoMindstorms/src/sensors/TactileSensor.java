package sensors;

import lejos.hardware.sensor.EV3TouchSensor;

public class TactileSensor {
	private EV3TouchSensor touchL;
	private EV3TouchSensor touchR;
	private boolean pressed = true;	
	public TactileSensor(){
		
	}
	
	// Returns a string with the info if the button is pressed
	// 0 = not pressed, 1 = pressed
	public String getTouchSensorValue(EV3TouchSensor touch) {
	
	return touch.getTouchMode().getName();
	}
	
	// gives back if the left touch sensor is pressed
	public boolean leftIsPressed() {
		if (getTouchSensorValue(touchL) == "1") {
			pressed = true;
			
		} else {
			pressed = false;
		}
		
		return pressed;
	}
	
	// gives back if the right touch sensor is pressed
	public boolean rightIsPressed() {
		if (getTouchSensorValue(touchR) == "1") {
			pressed = true;
			
		} else {
			pressed = false;
		}
		
		return pressed;
	}
}
