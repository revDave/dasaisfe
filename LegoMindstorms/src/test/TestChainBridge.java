package test;



import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import sensors.BarcodeScanner;
import sensors.Movement;
import tasks.Bridge;
import tasks.ChainBridge;
import tasks.Task;

public class TestChainBridge {
	
	public static void main(String[] args) {
		
		
		Task bri = new ChainBridge();
		
		LCD.drawString("Press button to start", 0, 1);
		Button.waitForAnyPress();
		LCD.clear();
		
		//bri.execute();
		//BarcodeScanner scanner = new BarcodeScanner(Movement.getInstance());
		//scanner.read();
	}

}
