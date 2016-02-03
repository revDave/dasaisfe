package sensors;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;

public class DistanceSensor {
	private EV3UltrasonicSensor distanceSensor;
	private final String DISTANCE_STRING = "Distance";
	
	
	public DistanceSensor(){
		distanceSensor = new EV3UltrasonicSensor(SensorPort.S3);
	}
	
	public float getDistance() {
		SampleProvider distance= distanceSensor.getMode(DISTANCE_STRING);

		float[] sample = new float[distance.sampleSize()];

		distance.fetchSample(sample, 0);
		distanceSensor.getDistanceMode();
		
		float result = 0;
		
		for(int i = 0; i < sample.length; i++) {
			result += sample[i];
		}
		
		return result / sample.length;
		
		
	}
}
