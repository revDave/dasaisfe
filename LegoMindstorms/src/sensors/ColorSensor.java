package sensors;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;

public class ColorSensor {
	private  EV3ColorSensor colorSensor;
	
	private static ColorSensor sensor = null;

	public ColorSensor(){
		colorSensor = new EV3ColorSensor(SensorPort.S4);
		colorSensor.setCurrentMode(colorSensor.getRedMode().getName());
	}
	
	public ColorSensor getInstance() {
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
		float samples[] = new float[intensity.sampleSize()];
		
		intensity.fetchSample(samples, 0);
		
		float result = 0;
		
		for(int i = 0; i < samples.length; i++) {
			result += samples[i];
		}
		
		return result / samples.length;
	}
	
}
