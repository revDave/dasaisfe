package tasks;

import main.Main;

public class TargetValueControllerTask extends Task {
    protected FollowTargetValueController targetValueController = null;
    
	public TargetValueControllerTask(Main main) {
		super(main);
	}

	@Override
	protected void specificExecute() {
		targetValueController.execute();
		
	}

}
