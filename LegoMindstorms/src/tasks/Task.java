package tasks;

import main.Main;

public abstract class Task {
	private Main main = null;
	
	public Task(Main main) {
		this.main = main;
	}
	
	public void execute() {
		while(true) {//!Button.ESCAPE.isDown()) {
			specificExecute();			
			main.readBarcode();
		}
	}
	
	
	protected abstract void specificExecute();
	
	
	protected void driveForward() {
		
	}
	
	protected void rotateLeft(int degrees) {
		
	}
	
	protected void rotateRight(int degrees) {
		
	}

}
