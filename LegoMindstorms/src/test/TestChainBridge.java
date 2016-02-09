package test;



import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import tasks.ChainBridge;
import tasks.Task;

public class TestChainBridge {
	
	public static void main(String[] args) {
		
		LCD.drawString("Press button to start", 0, 1);
		Button.waitForAnyPress();
		LCD.clear();
		Task task = new ChainBridge();
		task.execute();
	}

}
