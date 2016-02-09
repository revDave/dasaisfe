package tasks;

import main.Main;

public class Elevator extends Task {
	
	@Override
	protected TaskState specificExecute() {
		// TODO Auto-generated method stub
		return TaskState.CONTINUE;
	}

	@Override
	protected boolean continueCurrentTask() {
		// TODO Auto-generated method stub
		return false;
	}

}
