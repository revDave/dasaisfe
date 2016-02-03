package test;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.utility.Delay;

public class TestLightSensor {
	
	public static void main(String[] args) {
		EV3ColorSensor color = new EV3ColorSensor(SensorPort.S3);

		color.setCurrentMode(color.getRedMode().getName());
		float samples[] = new float[1];
		

		boolean goOn = true;
		while(goOn) {
			color.getRedMode().fetchSample(samples, 0);
			LCD.drawString(Float.toString(samples[0]), 4, 4);
			Delay.msDelay(500);
			goOn = ! Button.ESCAPE.isDown();
		}
	}
	
}
