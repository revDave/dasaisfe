package sensors;

import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3TouchSensor;

public class ColorSensor {
	private  EV3ColorSensor color;

	public ColorSensor(){
		
	}
	
	public int getColorSensorValue() {
		color.setFloodlight(true);
		//LCD.drawString(color.getColorIDMode().getName(), 0, 4);
		color.setCurrentMode(color.getColorIDMode().getName());
		
		//color.getFloodlight();
		//color.getRedMode();
		
		return color.getColorID();
	}
	
}
