package sensors;

import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3TouchSensor;

public class ColorSensor {
	private  EV3ColorSensor color;

	public ColorSensor(){
		
	}
	
	public int getColorSensorValue() {
		color.setFloodlight(true);
		color.setCurrentMode(color.getColorIDMode().getName());
		
		return color.getColorID();
	}
	
	public float getRedSensorValue() {
		color.setCurrentMode(color.getRedMode().getName());
		float samples[] = new float[1];
		
		color.getRedMode().fetchSample(samples, 0);
		
		return samples[0];
	}
	
}
