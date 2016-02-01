package main;

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
		String readCode = "something";
		
		Task task = null;
		
		switch(readCode) {
			case "1" : task = new Labyrinth(this);
		
		}
		
		if(task != null)
			task.execute();
	}

}
