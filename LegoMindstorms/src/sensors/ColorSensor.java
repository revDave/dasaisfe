package sensors;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3TouchSensor;

public class ColorSensor {
	private  EV3ColorSensor color;

	public ColorSensor(){
		color = new EV3ColorSensor(SensorPort.S4);
		color.setCurrentMode(color.getRedMode().getName());
	}
	
	public int getColorSensorValue() {
		color.setFloodlight(true);
		color.setCurrentMode(color.getColorIDMode().getName());
		
		return color.getColorID();
	}
	
	public float getRedSensorValue() {
		float samples[] = new float[1];
		
		color.getRedMode().fetchSample(samples, 0);
		
		return samples[0];
		
	}
	
}
