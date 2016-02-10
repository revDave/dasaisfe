package sensors;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;

public class ColorSensor {
	private  EV3ColorSensor colorSensor;
	
	private static ColorSensor sensor = null;

	private float[] samples = new float[1];

	private ColorSensor(){
		colorSensor = new EV3ColorSensor(SensorPort.S4);
		colorSensor.setCurrentMode(colorSensor.getRedMode().getName());
	}
	
	public static ColorSensor getInstance() {
		if(sensor == null) {
			sensor = new ColorSensor();
		}
		
		return sensor;
		
	}
	
	public int getColorSensorValue() {
		colorSensor.setFloodlight(true);
		colorSensor.setCurrentMode(colorSensor.getColorIDMode().getName());
		
		return colorSensor.getColorID();
	}
	
	public float getRedSensorValue() {
		SampleProvider intensity = colorSensor.getRedMode();
		
		intensity.fetchSample(samples, 0);
		
		return samples[0];
	}
	
}
