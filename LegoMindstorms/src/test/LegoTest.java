package test;

import lejos.hardware.motor.Motor;
import lejos.robotics.navigation.DifferentialPilot;

public class LegoTest {
	public static void main(String[] args) {
		//LCD.drawString("Lego Test", 0, 4);
		
//		
//		// Fahrtest
//		Motor.A.setSpeed(720);
//		Motor.B.setSpeed(720);
//		//Motor.C.setSpeed(360);
//		
//		Motor.A.backward();
//		Motor.B.backward();
//		
//		//Motor.C.forward();
//		Delay.msDelay(20000);
//		//Motor.C.stop();
//		//Motor.C.backward();
//		//Delay.msDelay(500);
//		//Motor.C.stop();
//		
//		//Delay.msDelay(500);
//		
//		Motor.A.stop();
//		Motor.B.stop();
		
		
		
//		FollowPath test = new FollowPath(null);
//		test.execute();
		// Sensortest
//		boolean duration = true;
//		while (duration) {
//			
//			int sensorval = test.getColorSensorValue();
//			LCD.drawInt(sensorval, 4, 4);
//			
//			Delay.msDelay(500);
//			duration = Button.readButtons() == 0;
//		}
		
		 DifferentialPilot pilot = new DifferentialPilot(2.42f, 12.3f, Motor.B, Motor.A, true);  // parameters in cm
		 pilot.setRotateSpeed(30);  // cm per second
//		 pilot.travel(5);         // cm
//		 pilot.rotate(-90);        // degree clockwise
//		 pilot.travel(5);         // cm
//		 pilot.rotate(-90);        // degree clockwise
//		 pilot.travel(5);         // cm
//		 pilot.rotate(90);        // degree counterclockwise
//		 pilot.travel(-5);         // cm
//		 pilot.rotate(90);        // degree counterclockwise
		 pilot.arc(2, 180);
	}
	

}

