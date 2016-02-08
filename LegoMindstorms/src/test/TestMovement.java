package test;

import lejos.hardware.Button;
import sensors.Movement;
import lejos.utility.Delay;
import lejos.hardware.motor.Motor;
import lejos.hardware.lcd.LCD;

public class TestMovement {

	public static void main(String[] args) {
		Movement move = Movement.getInstance(); 

		move.setSpeeds(1, 10);
		
		boolean goOn = true;
		while(goOn) {
			for(int i = -200; i <= 200; i++){
				LCD.clear();
				LCD.drawInt(i, 1, 3);
				LCD.drawInt(Motor.B.getSpeed(), 1, 4);
				LCD.drawInt(Motor.A.getSpeed(), 1, 5);
				move.steer(i,false);
				Button.waitForAnyEvent();
				if(Button.ESCAPE.isDown()){
					break;
				}
			}
			goOn = ! Button.ESCAPE.isDown();
		}
	}
}
