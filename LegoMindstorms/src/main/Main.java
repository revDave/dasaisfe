package main;

import tasks.ChainBridge;
import tasks.FinalBoss;
import tasks.Labyrinth;
import tasks.Task;

public class Main {
	
	public static void main(String[] args) {
		new Main();	
	}
	
	public Main() {
		readBarcode();		
	}
	
	
	public void readBarcode() {
		String readCode = "1";
		
		Task task = null;
		
		switch(readCode) {
			case "1" : task = new FinalBoss(this);
		
		}
		
		if(task != null)
			task.execute();
	}

}
