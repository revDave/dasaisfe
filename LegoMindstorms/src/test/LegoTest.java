package test;

import lejos.hardware.motor.Motor;
import lejos.utility.Delay;

public class LegoTest {
	public static void main(String[] args) {
		//LCD.drawString("Lego Test", 0, 4);
		//Motor.A.setSpeed(720);
		//Motor.B.setSpeed(720);
		Motor.C.setSpeed(360);
		
		//Motor.A.backward();
		//Motor.B.backward();
		
		Motor.C.forward();
		Delay.msDelay(3000);
		Motor.C.stop();
		//Motor.C.backward();
		//Delay.msDelay(500);
		//Motor.C.stop();
		
		//Delay.msDelay(500);
		
		//Motor.A.stop();
		//Motor.B.stop();
	}
	

}

